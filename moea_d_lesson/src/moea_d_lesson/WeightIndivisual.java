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
    super(knapsacs);
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
