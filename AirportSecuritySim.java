/*
 * File: AirportSecuritytSim.java
 * Author:  Denis Gichana
 * Description: Program reads information about airport screening stations
 *              and passengers entering airport with their bags. Program then
 *              simulates passengers being sent to screening station lineups
 *              and leaving them once their bags are scanned. Program prints
 *              key events of this process
 */

import java.util.Scanner;
import java.util.ArrayList;

public class AirportSecuritySim {
  public static void main (String[] args) {

    //Initialization
    Scanner in = new Scanner(System.in);
    ArrayList<ScreeningStation> stations = new ArrayList<ScreeningStation>();
    ArrayList<Passenger> players = new ArrayList<Passenger>();
    boolean complete = false; //Tracks if the simulation is complete

    //INPUT
    //Screening Stations
    final int NO_OF_STATIONS = in.nextInt();
    for (int i = 0; i < NO_OF_STATIONS; i++) {
      ScreeningStation station = new ScreeningStation(in.nextInt());
      stations.add(station);
    }

    //Loop rounds
    final int NO_OF_PASS_ROUNDS = in.nextInt();
    int round = 1;
    while ((!complete) || (round <= NO_OF_PASS_ROUNDS)) {

      if (round <= NO_OF_PASS_ROUNDS) {
        //INPUT
        //Passengers
        int incPassengers = in.nextInt();
        for (int i = 0; i < incPassengers; i++) {
          String name = in.next();
          Passenger passenger = new Passenger(name);

          //INPUT
          //Passengers' bags
          int incBags = in.nextInt();
          for (int j = 0; j < incBags; j++) {
            passenger.addBag(new Bag(in.nextBoolean()));
          }

          players.add(passenger);

          //Send passenger to shortest Screening Station queue
          ScreeningStation minStation = stations.get(0);
          for (int j = 1; j < NO_OF_STATIONS; j++) {
            ScreeningStation currentStation = stations.get(j);
            if (currentStation.getLineUpSize() < minStation.getLineUpSize()) {
              minStation = currentStation;
            }
          }
          minStation.addPasenger(passenger);

          //OUTPUT
          //Passenger entering Screening Station
          System.out.println(passenger.toString() + " enters station "
                  + minStation.getID() + " in round " + round);
        }
      }

      //Screening Stations Processing
      complete = true;
      for (int i = 0; i < NO_OF_STATIONS; i++) {
        ScreeningStation station = stations.get(i);
        Passenger passengerOut = station.doStep();
        if (passengerOut != null) {

          //OUTPUT
          //Passenger exiting Screening Station
          System.out.println(passengerOut.toString() + " leaves station "
                  + station.getID() + " in round " + round);
        }
        if (station.getLineUpSize() > 0) {
          complete = false;
        }
      }
      round++;
    }

    in.close();
  }
}