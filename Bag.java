/*
 * File: Bag.java
 * Author: Denis Gichana
 * Desc: Bag object carried by (airplane) passengers. May need to be screened
 */
public class Bag {

  //Instance Variables
  private boolean needScreening;

  //Constructor
  public Bag(boolean needScreening) {
    this.needScreening = needScreening;
  }

  //Methods
  //Screen the bag, so it no longer needs to be screened
  public void screen() {
    needScreening = false;
  }

  //Gets/returns whether bag requires screening
  public boolean isNeedScreening() {
    return needScreening;
  }
  
}
