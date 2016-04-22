package moea_d_lesson;

import java.util.ArrayList;

public class WeightIndivisual {

  /*
   * 近傍数T=10
   */
  public final int NEIGHBORHOOD = 10;
  /*
   * 遺伝子。要素は0または1、大きさ500の配列
   */
  private ArrayList<Integer> gene = new ArrayList<Integer>(Knapsac.ITEM_NUM);

  /*
   * 重みの値を格納した大きさ2の配列
   */
  private ArrayList<Integer> weight = new ArrayList<Integer>(Knapsac.KNAPSAC_NUM);

  /*
   * 近傍ベクトルのインデックスを登録した配列。大きさは近傍数T=10
   */
  private ArrayList<Integer> neightborhood = new ArrayList<Integer>(NEIGHBORHOOD);

  public void setGene(ArrayList<Integer> g){
    gene = g;
  }

  public void setWeight(ArrayList<Integer> w){
    weight = w;
  }

  public void addNeightboehood(ArrayList<Integer> n){
    neightborhood = n;
  }
}
