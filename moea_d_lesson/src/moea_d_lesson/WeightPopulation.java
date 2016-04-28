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
   * 重みベクトルの設定
   */
  public WeightPopulation(){
    for(int i = 0; i <= DIVISION_NUM; i++){
      for(int j = 0; j <= DIVISION_NUM; j++){
        System.out.println(i + ", "+ j);
        if(i + j == DIVISION_NUM){
          WeightIndivisual wp = new WeightIndivisual();
          ArrayList<Double> w = new ArrayList<Double>(Arrays.asList((double)i/200, (double)j/200));
          wp.setWeight(w);
          population.add(wp);
          System.out.println("!!!");
        }
      }
    }
  }

  public void searchNeighborhoodVector(int T){
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
