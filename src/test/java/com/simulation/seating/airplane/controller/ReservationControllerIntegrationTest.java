package com.simulation.seating.airplane.controller;

import com.simulation.seating.airplane.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationControllerIntegrationTest extends BaseTest {

    @Autowired
    private ReservationController reservationController;

    @Test
    public void test_find_seat_allocation_probablilty(){
        setup();
        float probability = reservationController.findSeatAllocationProbablilty(100, true, airplaneBoarding);
        assertTrue(probability <=1 && probability >=0);
    }
}