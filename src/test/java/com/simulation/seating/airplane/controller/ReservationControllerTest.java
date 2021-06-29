package com.simulation.seating.airplane.controller;

import com.simulation.seating.airplane.BaseTest;
import com.simulation.seating.airplane.framework.ValidationException;
import com.simulation.seating.airplane.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ReservationControllerTest extends BaseTest {

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    public void setup() {
        super.setup();
    }

    @Test
    public void test_find_seat_allocation_probabililty_wrong_simulation_count(){
        try {
            reservationController.findSeatAllocationProbablilty(-1, false, airplaneBoarding);
        } catch (ValidationException e) {
            assertEquals("Simulation count needs to be in the range between 0 to 1 million", e.getMessage());
        }
    }

    @Test
    public void test_find_seat_allocation_probabililty_no_input_data(){
        try {
            reservationController.findSeatAllocationProbablilty(1, false, null);
        } catch (ValidationException e) {
            assertEquals("Please provide passenger and seat booking data in Json format in the request body", e.getMessage());
        }
    }

    @Test
    public void test_find_seat_allocation_probabililty_wrong_passenger_data(){
        passengers.add(new Passenger(101, "firstName_test" , "lastName_test", null, 101));
        try {
            reservationController.findSeatAllocationProbablilty(1, false, airplaneBoarding);
        } catch (ValidationException e) {
            assertEquals("Number of seats on the airplane and the number of passengers boarding should be 100", e.getMessage());
        }
    }
}
