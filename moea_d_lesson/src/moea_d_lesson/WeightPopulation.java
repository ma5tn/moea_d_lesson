package moea_d_lesson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WeightPopulation {

  /*
   * 分割数
   */
  public final int DIVISION_NUM = 200;

  /*
   * 近傍数T=10
   */
  public final int NEIGHBORHOOD = 10;

  /*
   * 1世代の集団
   */
  public ArrayList<WeightIndivisual> population = new ArrayList<WeightIndivisual>();

  Random rnd = new Random();

  /*
   * 重みベクトルの設定、遺伝子の初期化、近傍ベクトルの登録
   */
  public WeightPopulation(){
    for(int i = 0; i <= DIVISION_NUM; i++){
      for(int j = 0; j <= DIVISION_NUM; j++){
        System.out.println(i + ", "+ j);
        if(i + j == DIVISION_NUM){
          WeightIndivisual wp = new WeightIndivisual();
          ArrayList<Double> w = new ArrayList<Double>(Arrays.asList((double)i/200, (double)j/200));
          wp.setWeight(w);
          ArrayList<Integer> g = new ArrayList<Integer>();
          for(int k = 0; k < 10; k++){
            g.add(rnd.nextInt(2));
          }
          wp.setGene(g);
          population.add(wp);
          System.out.println("!!!");
        }
      }
    }
  }

  public void printWeightPopulation(){
    for( WeightIndivisual w : population){
      System.out.println(w.getWeight());
      System.out.println(w.getGene());
    }
  }
}
