package moea_d_lesson;

import java.util.ArrayList;

public class ExternalPopulation {

  /*
   * 1世代の集団
   */
  public ArrayList<ExternalIndivisual> population = new ArrayList<ExternalIndivisual>();

  ExternalPopulation(){
    for(int i = 0; i < ExternalIndivisual.EX_POPULATION_NUM; i++){
      population.add(new ExternalIndivisual());
    }
  }
}
