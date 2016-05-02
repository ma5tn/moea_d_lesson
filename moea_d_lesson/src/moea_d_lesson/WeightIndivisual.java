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
   * 適応度
   */
  private double fitness;

  WeightIndivisual(){
    super();
  }

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

  public double getFitness() {
    return fitness;
  }

  public void setFitness(double fitness) {
    this.fitness = fitness;
  }
}
