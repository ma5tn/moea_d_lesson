package moea_d_lesson;

import java.util.ArrayList;

public class WeightIndivisual extends Indivisual{

  /*
   * 近傍数T=10
   */
  public final static int NEIGHBORHOOD = 10;


  /*
   * 重みの値を格納した大きさ2の配列
   */
  private ArrayList<Double> weight = new ArrayList<Double>(Knapsac.KNAPSAC_NUM);

  /*
   * 近傍ベクトルのインデックスを登録した配列。大きさは近傍数T=10
   */
  private ArrayList<Integer> neighborhood = new ArrayList<Integer>(NEIGHBORHOOD);

  /*
   * 重みを適用した適応度
   */
  private double weightFitness;

  /*
   * コンストラクタ
   */
  WeightIndivisual(ArrayList<Knapsac> knapsacs){
    super();
    for(int k = 0; k < Knapsac.ITEM_NUM; k++){
      gene.add(rnd.nextInt(2));
    }

    //解の修復
    repairGene(knapsacs);
  }

  public void repairGene(ArrayList<Knapsac> knapsacs) {
   double weight1 = 0; double weight2 = 0;
   for (int i = 0; i < Knapsac.ITEM_NUM; i++) {
     weight1 += knapsacs.get(0).getItems().get(i).getWeight();
     weight2 += knapsacs.get(1).getItems().get(i).getWeight();
   }

   ArrayList<Integer> indexes = new ArrayList<Integer>();
   for (int i = 0; i < Knapsac.ITEM_NUM; i++) {
    if(gene.get(i) == 1) indexes.add(i);
  }
   while(weight1 > knapsacs.get(0).getCapacity() && weight2 > knapsacs.get(1).getCapacity()){
     int r = rnd.nextInt(indexes.size());
     int index = indexes.get(r);
     if(gene.get(index) == 1){
       gene.set(r, 0);
       weight1 -= knapsacs.get(0).getItems().get(index).getWeight();
       weight2 -= knapsacs.get(1).getItems().get(index).getWeight();
     }
   }
  }

  /*
   * weightFitnessを計算する
   */
  public void calcWeightFitness(ArrayList<Knapsac> knapsacs){
    double f = 0;
    calcFitness(knapsacs);
    try{
      for(int i = 0; i < Knapsac.KNAPSAC_NUM; i++){
        f += fitness.get(i) * weight.get(i);
      }
    }catch(NullPointerException e){
      System.out.println("重みが設定されていません");
    }
    weightFitness = f;
  }

  //以下 setter & getter

  public void setWeight(ArrayList<Double> w){
    weight = w;
  }

  public void setNeighborhood(ArrayList<Integer> n){
    neighborhood = n;
  }

  public ArrayList<Double> getWeight(){
    return weight;
  }

  public ArrayList<Integer> getNeighborhood() {
    return neighborhood;
  }

  public double getWeightFitness() {
    return weightFitness;
  }

  public void setWeightFitness(double weightFitness) {
    this.weightFitness = weightFitness;
  }

}
