package ua.com.danit;

import ua.com.danit.dao.flight.CollectionFlightDao;
import ua.com.danit.entity.Flight;
import ua.com.danit.service.FlightService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        CollectionFlightDao collectionFlightDao = new CollectionFlightDao();
        FlightService flightService = new FlightService(collectionFlightDao);
        flightService.loadFlights();
        List<Flight> flights = flightService.getAllFlights();
    }
}
