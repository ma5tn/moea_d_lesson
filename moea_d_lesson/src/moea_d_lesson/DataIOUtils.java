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
  public static void readData(String fileName, ArrayList<Item> items){

    try {
        //ファイルを読み込む
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        //読み込んだファイルを１行ずつ画面出力する
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            System.out.println(++count + "行目：" + line);
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
