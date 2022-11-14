/*
 * File: ScreeningStation.java
 * Author:  Denis Gichana
 * Desc: Screening Station object (at airports). Stores passengers in a queue,
 *       and scans their bags (if needed) when doStep() is called
 *       
 */
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class ScreeningStation {

  //Instance Variables
  private Queue<Passenger> lineup;
  private int stationID;

  //Constructor
  public ScreeningStation(int stationID) {
    this.stationID = stationID;
    lineup = new LinkedList<Passenger>();
  }

  //Methods

  //Gets/returns how many passengers are waiting here
  public int getLineUpSize() {
    return lineup.size();
  }

  //Gets/returns station number
  public int getID() {
    return stationID;
  }

  //Adds passenger to this lineup
  public void addPasenger(Passenger passenger) {
    lineup.add(passenger);
  }

  //Progresses queue and returns leaving passenger
  public Passenger doStep() {
    if (lineup.size() > 0) {
      Passenger passenger = lineup.peek();
      ArrayList<Bag> passInv = passenger.getBags();
      int NO_OF_BAGS = passInv.size();

      //Check for any unscreened bags
      boolean screened = true;
      for (int i = 0; i < NO_OF_BAGS; i++) {
        Bag currentBag = passInv.get(i);
        if (currentBag.isNeedScreening()) {
          screened = false;
          break;
        }
      }

      //Screen one bag (if needed)
      if (!screened) {
        for (int i = 0; i < NO_OF_BAGS; i++) {
          Bag currentBag = passInv.get(i);
          if (currentBag.isNeedScreening()) {
            currentBag.screen();
            break;
            /*
            Break as soon as one bag is screened
            => Only screen one bag at a time
             */
          }
        }
      }

      //Handle returns
      if (!screened) {
        //Case 1: passenger at front of lineup unfinished
        return null;
      } else {
        //Case 2: passenger at front has no (more) bags requiring screening
        return lineup.remove();
      }

    } else {
      //Case 3: no passengers in lineup
      return null;
    }
  }
  
}
