package moea_d_lesson;

public class ExternalIndivisual extends Indivisual {

  ExternalIndivisual(WeightIndivisual weightIndivisual){
    gene = weightIndivisual.getGene();
    fitness = weightIndivisual.getFitness();
  }

}
