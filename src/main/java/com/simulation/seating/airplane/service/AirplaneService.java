package com.simulation.seating.airplane.service;

import com.simulation.seating.airplane.controller.ReservationController;
import com.simulation.seating.airplane.model.AirplaneBoarding;
import com.simulation.seating.airplane.model.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AirplaneService {

   Logger log = LoggerFactory.getLogger(ReservationController.class);

   public float findSeatAllocationProbablilty(AirplaneBoarding airplaneBoarding, int simulationCount, boolean logging){

       int lastPassengerGotOwnSeat =0;

       for (int i =0 ; i< simulationCount; i++) {

           if (logging){
               log.info(String.format("Running siumlation %d of %d", i+1, simulationCount));
           }

           List<Passenger> passengersForCurrentIteration = airplaneBoarding.getPassengers().stream().collect(Collectors.toList());
           List<Integer> availableSeatsForCurrentIteration = airplaneBoarding.getAirplaneSeats().stream().collect(Collectors.toList());

           allocateSeatsOnBoard( passengersForCurrentIteration, availableSeatsForCurrentIteration);

           if (lastPassangerGotOwnSeat(passengersForCurrentIteration, logging)){
               lastPassengerGotOwnSeat++;
           }
       }

       return (float) lastPassengerGotOwnSeat/simulationCount;

   }

   boolean lastPassangerGotOwnSeat(List<Passenger> passengers, boolean logging){
       Passenger lastPassenger = passengers.get(passengers.size()-1);

       if (logging) {
           passengers.stream().filter(p -> p.getAllocatedSeat() != p.getOnBoardSeat())
                   .forEach(p -> log.info(String.format("Passenger : %d, assigned seat : %d, on board seat : %d",
                           p.getId(), p.getAllocatedSeat(), p.getOnBoardSeat())));

           log.info(String.format("Last passenger : %d, assigned seat : %d, on board seat : %d",
                   lastPassenger.getId(), lastPassenger.getAllocatedSeat(), lastPassenger.getOnBoardSeat()));
       }

       return passengers.stream().filter(p -> p.getId().equals(passengers.get(passengers.size()-1).getId()))
               .map(p -> p.getOnBoardSeat() == p.getAllocatedSeat())
               .findFirst()
               .get();
   }

   void allocateSeatsOnBoard( List<Passenger> passengers, List<Integer> availableSeats){

       HashMap<Integer, Passenger> onBoardSeating = new HashMap<>();

       for (int i =0; i <availableSeats.size(); i++){
           onBoardSeating.put(i, null);
       }
       passengers.forEach( i -> assignSeat(i, onBoardSeating, availableSeats));

   }

   void assignSeat(Passenger passenger, Map<Integer, Passenger> onBoardSeating , List<Integer> availableSeats ){

       Integer seatIndex;

       if (passenger.getId().equals(1)){ //first passenger takes a random seat
           seatIndex = selectRandomSeatIndex(availableSeats);
           assignSeatToPassenger(seatIndex, passenger, onBoardSeating, availableSeats);
       }

       else if (availableSeats.contains(passenger.getAllocatedSeat())) { // assigned seat available
           seatIndex = availableSeats.indexOf(passenger.getAllocatedSeat());
           assignSeatToPassenger(seatIndex, passenger, onBoardSeating, availableSeats);
       }

       else { //assigned seat not available
           seatIndex = selectRandomSeatIndex(availableSeats);
           assignSeatToPassenger(seatIndex, passenger, onBoardSeating, availableSeats);
       }

   }

   Integer selectRandomSeatIndex(List<Integer> availableSeats){
       Random random = new Random();
       return random.nextInt(availableSeats.size());
   }

   void assignSeatToPassenger(Integer seatIndex, Passenger passenger, Map<Integer, Passenger> onBoardSeating , List<Integer> availableSeats) {
       Integer seatNumber = availableSeats.get(seatIndex);
       passenger.setOnBoardSeat(seatNumber);
       onBoardSeating.put(seatNumber, passenger);
       availableSeats.remove(seatIndex.intValue());
   }

}
