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


  private Random rnd = new Random();

  Indivisual(ArrayList<Knapsac> knapsacs){
    for(int k = 0; k < Knapsac.ITEM_NUM; k++){
      gene.add(rnd.nextInt(2));
    }

    //解の修復
   double weight1 = 0; double weight2 = 0;
   for (int i = 0; i < Knapsac.ITEM_NUM; i++) {
     weight1 += knapsacs.get(0).getItems().get(i).getWeight();
     weight2 += knapsacs.get(1).getItems().get(i).getWeight();
  }
   while(weight1 > knapsacs.get(0).getCapacity() || weight2 > knapsacs.get(1).getCapacity()){
     int r = rnd.nextInt(Knapsac.ITEM_NUM);
     gene.set(r, 0);
     weight1 -= knapsacs.get(0).getItems().get(r).getWeight();
     weight2 -= knapsacs.get(1).getItems().get(r).getWeight();
   }

    calcFitness(knapsacs);
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
