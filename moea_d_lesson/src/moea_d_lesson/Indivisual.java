package moea_d_lesson;

import java.util.ArrayList;
import java.util.Random;

public class Indivisual {

  /*
   * 遺伝子。要素は0または1、大きさ500の配列
   */
  private ArrayList<Integer> gene = new ArrayList<Integer>(Knapsac.ITEM_NUM);

  Random rnd = new Random();

  Indivisual(){
    for(int k = 0; k < Knapsac.ITEM_NUM; k++){
      gene.add(rnd.nextInt(2));
    }
  }

  public void setGene(ArrayList<Integer> g){
    gene = g;
  }

  public ArrayList<Integer> getGene() {
    return gene;
  }

}
