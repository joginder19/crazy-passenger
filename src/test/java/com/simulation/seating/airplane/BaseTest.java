package com.simulation.seating.airplane;

import com.simulation.seating.airplane.model.AirplaneBoarding;
import com.simulation.seating.airplane.model.Passenger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    public AirplaneBoarding airplaneBoarding = new AirplaneBoarding();
    public java.util.List<Passenger> passengers = new ArrayList<>();
    public java.util.List<Integer> airplaneSeats = new ArrayList<>();
    public Map<Integer, Passenger> onBoardSeating = new HashMap<>();

    public void setup() {

        for (int i = 1; i <= 100; i++) {
            passengers.add(new Passenger(i, "firstName_" + i, "lastName_" + i,
                    LocalDate.of(1970, 01, 01), i));
            airplaneSeats.add(i - 1, i);
            onBoardSeating.put(i, null);
        }
        airplaneBoarding.setPassengers(passengers);
        airplaneBoarding.setAirplaneSeats(airplaneSeats);
    }

}
