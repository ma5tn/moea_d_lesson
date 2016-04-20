package moea_d_lesson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TestData の読み込みのためのクラス
 * http://www.tik.ee.ethz.ch/sop/download/supplementary/testProblemSuite/index.php?page=testProblem.php#testproblems
 */
public class DataIOUtils {
  public static void readData(String fileName, ArrayList<Knapsac> knapsacs){

    try {
        //ファイルを読み込む
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        for(int i = 0; i < Knapsac.KNAPSAC_NUM; i++){
          Knapsac n = new Knapsac();
          br.readLine();

          double capacity = Double.parseDouble(br.readLine().split(" ")[2].substring(1));
          n.setCapacity(capacity);

          for(int j = 0; j < Knapsac.ITEM_NUM; j++){
            Item item = null;
            int weight = 0;
            int profit = 0;
            for(int k = 0; k < 3; k++){
              String l =  br.readLine();
              if(k == 1){
               weight = Integer.parseInt(l.split(" ")[3].substring(1));
              }else if(k == 2){
                profit = Integer.parseInt(l.split(" ")[3].substring(1));
              }
              item = new Item(weight, profit);
            }
            n.getItems().add(item);
          }
          knapsacs.add(n);
        }

        //終了処理
        br.close();
        fr.close();

    } catch (IOException ex) {
        //例外発生時処理
        ex.printStackTrace();
    }
  }
}
