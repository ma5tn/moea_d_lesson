package moea_d_lesson;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class WeightPopulation {

  /*
   * 分割数
   */
  public final static int DIVISION_NUM = 200;

  /*
   * 突然変異確率 %
   */
  public final static int MUTATION_RATE = 5;

  /*
   * 1世代の集団
   */
  public ArrayList<WeightIndivisual> population = new ArrayList<WeightIndivisual>();

  private Random rnd = new Random();

  /*
   * コンストラクタ
   * 重みベクトルの設定, 適応度の計算, 近傍ベクトルの登録
   */
  public WeightPopulation(ArrayList<Knapsac> knapsacs){
    for(int i = 0; i <= DIVISION_NUM; i++){
      for(int j = 0; j <= DIVISION_NUM; j++){
//        System.out.println(i + ", "+ j);
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

    searchNeighborhoodVector(WeightIndivisual.NEIGHBORHOOD);

  System.out.println("test");
  }

  public ArrayList<WeightIndivisual> generation(ArrayList<Knapsac> knapsacs){
    ArrayList<WeightIndivisual> newPopulation = new ArrayList<WeightIndivisual>();
    for(WeightIndivisual wi: population){
      //近傍ベクトルからランダムに選択し子をつくる。突然変異も
      int index1 = wi.getNeighborhood().get(rnd.nextInt(WeightIndivisual.NEIGHBORHOOD));
      int index2 = wi.getNeighborhood().get(rnd.nextInt(WeightIndivisual.NEIGHBORHOOD));
      ArrayList<Integer> newGene = new ArrayList<Integer>(Knapsac.ITEM_NUM);
      int crossoverIndex = rnd.nextInt(Knapsac.ITEM_NUM);
      for(int k = 0; k < Knapsac.ITEM_NUM; k++){
        if(k < crossoverIndex){
          newGene.add(population.get(index1).getGene().get(k));
        }else{
          newGene.add(population.get(index2).getGene().get(k));
        }
      }

//      System.out.println(newGene);
      for (Integer integer : newGene) {
        int r = rnd.nextInt(100);
        if(r < WeightPopulation.MUTATION_RATE){
          if(integer == 1){
            integer = 0;
          }else{
            integer = 1;
          }
//          System.out.println(newGene);
        }
      }
      WeightIndivisual newIndivisual = new WeightIndivisual(knapsacs);
      newIndivisual.setGene(newGene);
      //候補のArrayListにadd
      ArrayList<WeightIndivisual> candidateWI = new ArrayList<WeightIndivisual>();
      for (int i : wi.getNeighborhood()) {
        candidateWI.add(population.get(i));
      }
      candidateWI.add(newIndivisual);
      for (WeightIndivisual weightIndivisual : candidateWI) {
        weightIndivisual.setWeight(wi.getWeight());
        weightIndivisual.setNeighborhood(wi.getNeighborhood());
        weightIndivisual.calcWeightFitness(knapsacs);
      }
      //優秀な候補を選択しnewPopulationにadd
      Collections.sort(candidateWI, new WeightIndivisualComparator());
      newPopulation.add(candidateWI.get(0));
    }
    return newPopulation;
  }

  private void searchNeighborhoodVector(int T){
    for(int i = 0; i <= DIVISION_NUM; i++){
      ArrayList<Integer> neighborhood = new ArrayList<Integer>(T);
      if(i < (double)T/2){
        for(int k = 0; k < 10; k++){
          neighborhood.add(k);
        }
      }else if(i < DIVISION_NUM - ((double)T/2 - 1)){
        for(int k = 4; 0 < k; k--){
          neighborhood.add(i - k);
        }
        for(int k = 0; k < 6; k++){
          neighborhood.add(i + k);
        }
      }else{
        for(int k = 9 ; 0 <= k ; k--){
          neighborhood.add(DIVISION_NUM - k);
        }
      }
      population.get(i).setNeighborhood(neighborhood);
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
      pw.println(weightIndivisual.getWeightFitness());
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
