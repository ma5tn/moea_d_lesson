package moea_d_lesson;

import java.util.Comparator;

public class WeightIndivisualComparator implements Comparator<WeightIndivisual> {

  @Override
  public int compare(WeightIndivisual o1, WeightIndivisual o2) {
    double d1 = o1.getWeightFitness();
    double d2 = o2.getWeightFitness();

    if(d1 < d2){
      return 1;
    }else if(d1 == d2)
      return 0;
    else{
     return -1;
    }
  }
}
