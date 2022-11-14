/*
 * File: Passenger.java
 * Author: Denis Gichana
 * Desc: (Airplane) Passenger object with the ability to carry/store bags
 */
import java.util.ArrayList;

public class Passenger {

  //Instance Variables
  private String name;
  private ArrayList<Bag> inventory;

  //Constructor
  public Passenger(String name) {
    this.name = name;
    inventory = new ArrayList<Bag>();
  }

  //Methods

  //Returns output-friendly String
  public String toString() {
    return (name + "(" + inventory.size() + ")");
  }
  
  //Adds a bag to passenger's inventory => passenger bringing this bag along
  public void addBag(Bag bag) {
    inventory.add(bag);
  }

  //Get passenger's bag inventory
  public ArrayList<Bag> getBags() {
    return inventory;
  }

}
