package moea_d_lesson;

import java.util.ArrayList;

public class ExternalPopulation {

  /*
   * 外部集団数
   */
  public final static int EX_POPULATION_NUM = 200;

  /*
   * 1世代の集団
   */
  public ArrayList<ExternalIndivisual> population = new ArrayList<ExternalIndivisual>();

  ExternalPopulation(ArrayList<WeightIndivisual> weightPopulation){
    {

    }
  }

  //子が作られた時
  public void update(WeightIndivisual weightIndivisual){
  }
}
