package ua.com.danit.controller;

import ua.com.danit.entity.Flight;
import ua.com.danit.service.FlightService;
import ua.com.danit.service.LoggerService;

import java.util.List;

public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public List<Flight> getAllFlight() {
        return this.flightService.getAllFlights();
    }

    public List<Flight> searchFlights(String departure, String destination, int freeSeats) {
        return this.flightService.searchFlights(departure, destination, freeSeats);
    }

    public List<Flight> searchFlights(String departure, String destination, long date, int freeSeats) {
        return this.flightService.searchFlights(departure, destination, date, freeSeats);
    }

    public void uploadFlights(){
        try {
            this.flightService.uploadFlights();
        } catch (Exception e) {
            LoggerService.error(e.getMessage());
        }
    }

    public boolean bookingFlight(long id) {
        try {
            return this.flightService.bookingFlight(id);
        } catch (Exception e) {
            LoggerService.error(e.getMessage());
            return false;
        }
    }

    public List<Flight> getDayFlights() {
        return this.flightService.getDayFlights();
    }

    public boolean deleteFlightById(long id) {
        return this.flightService.deleteFlightById(id);
    }

    public boolean deleteFlight(Flight flight) {
        return this.flightService.deleteFlight(flight);
    }

    public String getFlightInfo(long id) {
        String flightInfo = "";
        Flight flight = this.flightService.getFlightById(id);
        flightInfo = "Flight from "
                + flight.getDeparture()
                + " to "
                + flight.getDestination()
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
