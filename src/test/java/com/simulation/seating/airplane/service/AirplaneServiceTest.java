package com.simulation.seating.airplane.service;

import com.simulation.seating.airplane.BaseTest;
import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class AirplaneServiceTest extends BaseTest {

    @InjectMocks
    private AirplaneService airplaneService;

    @BeforeEach
    public void setup() {
          super.setup();
    }

    @Test
    public void test_find_seat_allocation_probability() {

        assertTrue(passengers.get(0).getOnBoardSeat() == null);

        float result = airplaneService.findSeatAllocationProbablilty(airplaneBoarding, 100, false);

        assertTrue(result >= 0 && result <=1);
    }

    @Test
    public void test_allocate_seats_on_board() {

        for (int i = 0; i < 100; i++) {
            assertTrue(passengers.get(i).getOnBoardSeat() == null);
        }

        airplaneService.allocateSeatsOnBoard(passengers, airplaneSeats);

        for (int i = 0; i < 100; i++) {
            assertTrue(passengers.get(i).getOnBoardSeat() <= 100 && passengers.get(i).getOnBoardSeat() >= 1);
        }
    }

    @Test
    public void test_select_random_seat() {
        List<Integer> availableSeats = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int seatIndexSelected;
        for (int i = 0; i < 100; i++) {
            seatIndexSelected = airplaneService.selectRandomSeatIndex(availableSeats);
            assertTrue(availableSeats.get(seatIndexSelected) <= 10 && availableSeats.get(seatIndexSelected) >= 1);
        }
    }

    @Test
    public void test_assign_seat() {

        for (int i = 0; i < 100; i++) {
            assertTrue(passengers.get(i).getOnBoardSeat() == null);

            airplaneService.assignSeat(passengers.get(i), onBoardSeating, airplaneSeats);

            assertTrue(passengers.get(i).getOnBoardSeat() <= 100 && passengers.get(i).getOnBoardSeat() >= 1);
        }
    }

    @Test
    public void test_last_passenger_got_own_seat() {

        passengers.get(9).setOnBoardSeat( 10);
        assertTrue(airplaneService.lastPassangerGotOwnSeat(passengers.subList(0, 10), false));

        passengers.get(9).setOnBoardSeat( 50);
        assertFalse(airplaneService.lastPassangerGotOwnSeat(passengers.subList(0, 10), false));

    }

    @Test
    public void test_assign_seat_to_passenger() {

        assertTrue(passengers.get(0).getOnBoardSeat() == null);

        airplaneService.assignSeatToPassenger(50, passengers.get(0), onBoardSeating, airplaneSeats);

        assertTrue(passengers.get(0).getOnBoardSeat() == 51);
    }

}
