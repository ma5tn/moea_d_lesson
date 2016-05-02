package moea_d_lesson;

import java.util.ArrayList;
import java.util.Arrays;

public class WeightPopulation {

  /*
   * 分割数
   */
  public final int DIVISION_NUM = 200;

  /*
   * 1世代の集団
   */
  public ArrayList<WeightIndivisual> population = new ArrayList<WeightIndivisual>();


  /*
   * コンストラクタ
   * 重みベクトルの設定, 適応度の計算, 近傍ベクトルの登録
   */
  public WeightPopulation(ArrayList<Knapsac> knapsacs){
    for(int i = 0; i <= DIVISION_NUM; i++){
      for(int j = 0; j <= DIVISION_NUM; j++){
        System.out.println(i + ", "+ j);
        if(i + j == DIVISION_NUM){
          WeightIndivisual wp = new WeightIndivisual();
          double weight1 = (double)i/200; double weight2 = (double)j/200;
          ArrayList<Double> w = new ArrayList<Double>(Arrays.asList(weight1, weight2));
          wp.setWeight(w);
          wp.setFitness(calcFitness(knapsacs, wp, i, weight1, j, weight2));
          population.add(wp);
        }
      }
    }

    searchNeighborhoodVector(WeightIndivisual.NEIGHBORHOOD);
  }

  public void Generation(){

  }

  private double calcFitness(ArrayList<Knapsac> knapsacs, WeightIndivisual wp, int i, double weight1, int j, double weight2) {
    double profit1 = 0; double profit2 = 0;
    double w1 = 0; double w2 = 0;
    for(int k = 0; k < Knapsac.ITEM_NUM; k++){
      if(wp.getGene().get(k) == 1){
        profit1 += knapsacs.get(0).getItems().get(k).getProfit();
        w1 += knapsacs.get(0).getItems().get(k).getWeight();
        profit2 += knapsacs.get(1).getItems().get(k).getProfit();
        w2 += knapsacs.get(1).getItems().get(k).getWeight();
      }

      if(knapsacs.get(0).getCapacity() < w1){
        profit1 = 0;
      }

      if(knapsacs.get(1).getCapacity() < w2){
        profit2 = 0;
      }
    }
   double f = profit1 * weight1 + profit2 * weight2;
   return f;
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
        for(int k = 8 ; -1 <= k ; k--){
          neighborhood.add(DIVISION_NUM - k);
        }
      }
      population.get(i).setNeighborhood(neighborhood);
    }
  }

  public void printWeightPopulation(){
    for( WeightIndivisual w : population){
      System.out.println(w.getWeight());
      System.out.println(w.getGene());
    }
  }
}
