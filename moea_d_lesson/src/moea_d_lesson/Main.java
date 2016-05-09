package moea_d_lesson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
  public final static int GENERATION_NUM = 1000;

  public static void main(String[] args) {
    // TODO 自動生成されたメソッド・スタブ
    ArrayList<Knapsac> knapsacs = new ArrayList<Knapsac>(Knapsac.KNAPSAC_NUM);
    DataIOUtils.readData("knapsacTestData.txt", knapsacs);

    WeightPopulation wp = new WeightPopulation(knapsacs);
   // wp.printWeightPopulation();

    File file = new File("result.txt");
    try{
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

    wp.printAllWeightWitness(pw);
    for(int i = 0; i < GENERATION_NUM; i++){

      wp.generation(knapsacs);
      wp.printAllWeightWitness(pw);
      pw.println("-------------");
      wp.printWeightFitnessAverage();
    }
    pw.close();
    }catch(IOException e){
      System.out.println(e);
    }
  }


}
