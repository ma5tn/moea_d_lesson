package moea_d_lesson;

public class Item {
  private int profit;
  private int weight;
  Item(int w, int p){
    setProfit(p);
    setWeight(w);
  }

  public void setProfit(int p){
    profit = p;
  }

  public void setWeight(int w){
    weight = w;
  }

  public int getProfit(){
    return profit;
  }

  public int getWeight(){
    return weight;
  }
}
