package com.simulation.seating.airplane.controller;

import com.simulation.seating.airplane.framework.ValidationException;
import com.simulation.seating.airplane.model.AirplaneBoarding;
import com.simulation.seating.airplane.service.AirplaneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class ReservationController {
    Logger log = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    AirplaneService airplaneService;

    @RequestMapping(value = "/crazy-passenger/seatAllocations/{numberOfSimulations}/simulations", method = RequestMethod.PUT)
    public float findSeatAllocationProbablilty(@PathVariable int numberOfSimulations,
                                               @RequestParam(name = "logging", required = false, defaultValue = "false") Boolean logging,
                                               @RequestBody(required = true) AirplaneBoarding airplaneBoarding){

        if (numberOfSimulations < 0 || numberOfSimulations > 1000000) {
            throw new ValidationException("Simulation count needs to be in the range between 0 to 1 million");
        }

        if (airplaneBoarding == null) {
            throw new ValidationException("Please provide passenger and seat booking data in Json format in the request body");
        }

        if (airplaneBoarding.getAirplaneSeats().size() != 100 || airplaneBoarding.getPassengers().size() != 100) {
            throw new ValidationException("Number of seats on the airplane and the number of passengers boarding should be 100");
        }

        float probability = airplaneService.findSeatAllocationProbablilty(airplaneBoarding, numberOfSimulations, logging);

        if (logging) {
            log.info("Probability last passenger got their own seat :" + probability);
        }

        return probability;

    }

}

