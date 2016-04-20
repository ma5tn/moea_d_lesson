package moea_d_lesson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

/*
 * 参考 http://kanndume.blogspot.jp/2011/07/javahtml.html
 */
public class DataHttpUtils {
  public static void fileGetContents( String url, String encode ) {
    try {
        InputStream is = new URL(url).openStream();
        InputStreamReader isr = new InputStreamReader(is, encode);
        BufferedReader in = new BufferedReader(isr);
        String s = null;
        File file = new File("knapsacTestData.txt");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
//        s = in.readLine();
//        s = in.readLine();
        while ( (s = in.readLine()) != null) {
            pw.println(s);
        }
        pw.close();
    } catch ( Exception e ) {
        System.out.println( e.toString() );
    }
}

/**
 * PHPのfile_get_contentsもどき
 * urlに指定されたページのHTMLを取得します
 * エンコードはJISAutoDetectで自動判別させる（失敗する可能性あり！？）
 * @param url HTMLを取得したいページのURL
 * @return 取得したHTML
 */
  public static void fileGetContents( String url ) {
    fileGetContents( url, "JISAutoDetect" );
}
}
