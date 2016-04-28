package moea_d_lesson;

import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    ArrayList<Knapsac> knapsacs = new ArrayList<Knapsac>(Knapsac.KNAPSAC_NUM);
    DataIOUtils.readData("knapsacTestData.txt", knapsacs);

    for(Knapsac n : knapsacs){
      System.out.println("capacity " + n.getCapacity());
      n.printKnapsac();
    }

    WeightPopulation wp = new WeightPopulation();
    wp.printWeightPopulation();
    wp.searchNeighborhoodVector(WeightIndivisual.NEIGHBORHOOD);

  }

}
