package com.simulation.seating.airplane.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class Passenger {

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Integer allocatedSeat;

    @JsonIgnore
    private Integer onBoardSeat;

    public Passenger(Integer id, String firstName, String lastName, LocalDate dob, Integer allocatedSeat){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.allocatedSeat = allocatedSeat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAllocatedSeat() {
        return allocatedSeat;
    }

    public void setAllocatedSeat(Integer allocatedSeat) {
        this.allocatedSeat = allocatedSeat;
    }

    public Integer getOnBoardSeat() {
        return onBoardSeat;
    }

    public void setOnBoardSeat(Integer onBoardSeat) {
        this.onBoardSeat = onBoardSeat;
    }

}
