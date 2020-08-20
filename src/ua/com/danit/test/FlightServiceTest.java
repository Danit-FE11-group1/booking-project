package ua.com.danit.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.com.danit.dao.flight.CollectionFlightDao;
import ua.com.danit.entity.Flight;
import ua.com.danit.service.FlightService;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {

    static FlightService flightService;

    @BeforeEach
    void setUp() throws IOException {
        CollectionFlightDao collectionFlightDao = new CollectionFlightDao();
        flightService = new FlightService(collectionFlightDao);
        flightService.loadFlights();
    }

    @Test
    @DisplayName("Should get flight by id")
    void getFlightById() {
        long id = flightService.getAllFlights().get(999).getId();
        assertEquals(flightService.getAllFlights().get(999), flightService.getFlightById(id));
    }

    @Test
    @DisplayName("Should get 24h flights")
    void getDayFlights() {
        List<Flight> flights = flightService.getDayFlights();
        assertNotNull(flights.get(0));
    }

    @Test
    @DisplayName("Should load flights")
    void loadFlights() throws IOException {
        flightService.loadFlights();
    }

    @Test
    @DisplayName("Should delete flight by id")
    void deleteFlightById() {
        int sizeBefore = flightService.getAllFlights().size();
        long id = flightService.getAllFlights().get(0).getId();
        flightService.deleteFlightById(id);
        assertEquals(sizeBefore - 1, flightService.getAllFlights().size());
    }

    @Test
    @DisplayName("Should delete flight")
    void deleteFlight() {
        int sizeBefore = flightService.getAllFlights().size();
        Flight flight = flightService.getAllFlights().get(1);
        flightService.deleteFlight(flight);
        assertEquals(sizeBefore -  1, flightService.getAllFlights().size());
    }

}