package moea_d_lesson;

import java.util.ArrayList;
import java.util.Random;

public class Indivisual {

  /*
   * 遺伝子。要素は0または1、大きさ500の配列
   */
  protected ArrayList<Integer> gene = new ArrayList<Integer>(Knapsac.ITEM_NUM);

  /*
   * 適応度のArrayList
   */
  protected ArrayList<Double> fitness = new ArrayList<Double>(Knapsac.KNAPSAC_NUM);


  protected Random rnd = new Random();

  Indivisual(){
  }

  protected void calcFitness(ArrayList<Knapsac> knapsacs) {
    for(Knapsac k: knapsacs){
      double profit = 0; double weight = 0;
      for(int i = 0; i < Knapsac.ITEM_NUM; i++){
        if(gene.get(i) == 1){
          weight += k.getItems().get(i).getWeight();
          profit += k.getItems().get(i).getProfit();
          if( k.getCapacity() < weight){
            profit = 0;
            break;
          }
        }
      }
      fitness.add(profit);
    }
  }

  public void setGene(ArrayList<Integer> g){
    gene = g;
  }

  public ArrayList<Integer> getGene() {
    return gene;
  }

  public ArrayList<Double> getFitness() {
    return fitness;
  }

  public void setFitness(ArrayList<Double> fitness) {
    this.fitness = fitness;
  }

}
