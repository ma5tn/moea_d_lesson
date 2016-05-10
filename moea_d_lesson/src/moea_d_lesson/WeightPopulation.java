package moea_d_lesson;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class WeightPopulation {

  /*
   * 分割数
   */
  public final static int DIVISION_NUM = 200;

  /*
   * 突然変異確率 %
   */
  public final static double MUTATION_RATE = 0.05;

  /*
   * 1世代の集団
   */
  public ArrayList<WeightIndivisual> population = new ArrayList<WeightIndivisual>();

  /*
   * 外部集団
   */
  public ExternalPopulation externalPopulation;

  private Random rnd = new Random();

  /*
   * コンストラクタ
   * 重みベクトルの設定, 適応度の計算, 近傍ベクトルの登録
   */
  public WeightPopulation(ArrayList<Knapsac> knapsacs){
    for(int i = 0; i <= DIVISION_NUM; i++){
      for(int j = 0; j <= DIVISION_NUM; j++){
        if(i + j == DIVISION_NUM){
          WeightIndivisual wp = new WeightIndivisual(knapsacs);
          double weight1 = (double)i/200; double weight2 = (double)j/200;
          ArrayList<Double> w = new ArrayList<Double>(Arrays.asList(weight1, weight2));
          wp.setWeight(w);
          wp.calcWeightFitness(knapsacs);
          population.add(wp);
        }
      }
    }

    searchNeighborhoodVector();

    //外部集団
    externalPopulation = new ExternalPopulation(population);
  }

  public void generation(ArrayList<Knapsac> knapsacs){
    for(WeightIndivisual wi: population){
      //近傍ベクトルからランダムに選択し子をつくる
      int index1 = wi.getNeighborhood().get(rnd.nextInt(WeightIndivisual.NEIGHBORHOOD));
      int index2 = wi.getNeighborhood().get(rnd.nextInt(WeightIndivisual.NEIGHBORHOOD));
      ArrayList<Integer> newGene = new ArrayList<Integer>(Knapsac.ITEM_NUM);
      int crossoverIndex = rnd.nextInt(Knapsac.ITEM_NUM);
      for(int k = 0; k < Knapsac.ITEM_NUM; k++){
        /*
        if(k < crossoverIndex){
          newGene.add(population.get(index1).getGene().get(k));
        }else{
          newGene.add(population.get(index2).getGene().get(k));
        }
        */
        if(rnd.nextDouble() < 0.5){
          newGene.add(population.get(index1).getGene().get(k));
        }else{
          newGene.add(population.get(index2).getGene().get(k));
        }
      }

      //子に突然変異を起こす

      for (int i = 0; i < newGene.size(); i++) {
        double r = rnd.nextDouble();
        if(r < WeightPopulation.MUTATION_RATE){
          if(newGene.get(i) == 1){
            newGene.set(i, 0);
          }else{
            newGene.set(i, 1);
          }
        }
      }
      WeightIndivisual newIndivisual = new WeightIndivisual(knapsacs);
      newIndivisual.setGene(newGene);
      newIndivisual.repairGene(knapsacs);
      newIndivisual.calcFitness(knapsacs);

      //外部集団
      externalPopulation.update(newIndivisual);


      //近傍ベクトル全てに対し，子の方が優っている場合置き換える
      for(int i : wi.getNeighborhood()){
        newIndivisual.setWeight(population.get(i).getWeight());
        newIndivisual.calcWeightFitness(knapsacs);
        if(population.get(i).getWeightFitness() < newIndivisual.getWeightFitness()){
          newIndivisual.setNeighborhood(population.get(i).getNeighborhood());
          population.set(i, newIndivisual);
//          break;
        }
      }
    }
  }

  //近傍ベクトルの登録
  private void searchNeighborhoodVector(){

    for(int j = 0; j <= DIVISION_NUM; j++){
      ArrayList<Double> w1 = population.get(j).getWeight();
      ArrayList<ArrayList<Double>> sortArray = new ArrayList<ArrayList<Double>>();

      for (int i = 0; i < DIVISION_NUM + 1; i++) {
        ArrayList<Double> index_distance_pair = new ArrayList<Double>();
        ArrayList<Double> w2 = population.get(i).getWeight();
        double distance = Math.sqrt(Math.pow(w1.get(0)-w2.get(0), 2) + Math.pow(w1.get(1) - w2.get(1), 2));
        index_distance_pair.add((double) i);
        index_distance_pair.add(distance);
        sortArray.add(index_distance_pair);
      }

      Collections.sort(sortArray, new Comparator<ArrayList<Double>>(){
        public int compare(ArrayList<Double> a1, ArrayList<Double> a2){
          if(a1.get(1) - a2.get(1) < 0) return -1;
          else if(a1.get(1) - a2.get(1) > 0) return 1;
          else return 0;
        }
      });

      ArrayList<Integer> neighborhood = new ArrayList<Integer>();
      for (int i = 0; i < WeightIndivisual.NEIGHBORHOOD; i++) {
        neighborhood.add(sortArray.get(i).get(0).intValue());
      }
      population.get(j).setNeighborhood(neighborhood);
    }
  }

  public ArrayList<WeightIndivisual> getPopulation() {
    return population;
  }

  public void setPopulation(ArrayList<WeightIndivisual> population) {
    this.population = population;
  }

  public void printWeightPopulation(){
    for( WeightIndivisual w : population){
      System.out.println(w.getWeight());
      System.out.println(w.getGene());
    }
  }

  public void printAllWeightWitness(PrintWriter pw){
    for (WeightIndivisual weightIndivisual : population) {
      pw.println(weightIndivisual.getFitness().get(0) + "\t" + weightIndivisual.getFitness().get(1));

    //  pw.println(weightIndivisual.getWeightFitness() + " ," + weightIndivisual.getFitness().get(0) + " ," + weightIndivisual.getFitness().get(1));

    }
  }
  public void printWeightFitnessAverage(){
    double sum = 0;
    for (WeightIndivisual weightIndivisual : population) {
      sum += weightIndivisual.getWeightFitness();
    }
    System.out.println(sum/population.size());
  }
}
