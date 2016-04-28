package moea_d_lesson;

import java.util.ArrayList;

public class WeightIndivisual {

  /*
   * 近傍数T=10
   */
  public final static int NEIGHBORHOOD = 10;

  /*
   * 遺伝子。要素は0または1、大きさ500の配列
   */
  private ArrayList<Integer> gene = new ArrayList<Integer>(Knapsac.ITEM_NUM);

  /*
   * 重みの値を格納した大きさ2の配列
   */
  private ArrayList<Double> weight = new ArrayList<Double>(Knapsac.KNAPSAC_NUM);

  /*
   * 近傍ベクトルのインデックスを登録した配列。大きさは近傍数T=10
   */
  private ArrayList<Integer> neighborhood = new ArrayList<Integer>(NEIGHBORHOOD);

  public void setGene(ArrayList<Integer> g){
    gene = g;
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

  public ArrayList<Integer> getGene() {
    return gene;
  }
}
