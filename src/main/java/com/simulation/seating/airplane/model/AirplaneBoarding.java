package com.simulation.seating.airplane.model;

import java.util.List;

public class AirplaneBoarding {
    private List<Passenger> passengers;
    private List<Integer> airplaneSeats;

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Integer> getAirplaneSeats() {
        return airplaneSeats;
    }

    public void setAirplaneSeats(List<Integer> airplaneSeats) {
        this.airplaneSeats = airplaneSeats;
    }
}
