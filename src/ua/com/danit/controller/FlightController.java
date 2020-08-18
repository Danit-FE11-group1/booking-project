package ua.com.danit.controller;

import ua.com.danit.entity.Flight;
import ua.com.danit.service.FlightService;

import java.util.List;

public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public String getFlightInfo(long id) {
        String flightInfo = "";
        Flight flight = this.flightService.getFlightById(id);
        flightInfo = flight.getDestination()
                + ", "
                + flight.getName()
                + ", "
                + flight.showDate()
                + '\n'
                + flight.getFreeSeats()
                + "("
                + flight.getBoat().getSeats()
                + ")";
        return flightInfo;
    }

}
