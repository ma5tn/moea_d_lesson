package moea_d_lesson;

import java.util.ArrayList;

public class Knapsac {
  public static int ITEM_NUM = 500;
  public static int KNAPSAC_NUM = 2;
  private ArrayList<Item> items = new ArrayList<Item>(ITEM_NUM);
  private double capacity;

  public void setCapacity(double d){
    capacity = d;
  }

  public ArrayList<Item> getItems(){
    return items;
  }

  public double getCapacity(){
    return capacity;
  }

  public void printKnapsac(){
    for(Item item : items){
      System.out.println("profit " + item.getProfit());
      System.out.println("weight " + item.getWeight());
    }
  }

}
