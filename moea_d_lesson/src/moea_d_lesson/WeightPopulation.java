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
   * 1世代の集団
   */
  public ArrayList<WeightIndivisual> population = new ArrayList<WeightIndivisual>();

  Random rnd = new Random();

  public WeightPopulation(){
    for(int i = 0; i <= DIVISION_NUM; i++){
      for(int j = 0; j <= DIVISION_NUM; j++){
        System.out.println(i + ", "+ j);
        if(i + j == DIVISION_NUM){
          WeightIndivisual wp = new WeightIndivisual();
          ArrayList<Integer> w = new ArrayList<Integer>(Arrays.asList(i, j));
          wp.setWeight(w);
          //TODO geneの初期化とセット
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
}
