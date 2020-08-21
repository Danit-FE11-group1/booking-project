package ua.com.danit.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.danit.controller.BookController;
import ua.com.danit.controller.FlightController;
import ua.com.danit.dao.booking.CollectionBookingDao;
import ua.com.danit.dao.flight.CollectionFlightDao;
import ua.com.danit.entity.Booking;
import ua.com.danit.exception.FlightException;
import ua.com.danit.service.BookingService;
import ua.com.danit.service.FlightService;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {

    static BookController BookController;
    static FlightController FlightController;
    static BookingService BookingService;

    @BeforeEach
    void setUp() throws IOException {
        FlightService flightService = new FlightService((new CollectionFlightDao()));
        flightService.uploadFlights();
        FlightController = new FlightController(flightService);

        BookingService = new BookingService(new CollectionBookingDao());
        BookingService.loadData();
        BookController = new BookController(BookingService, FlightController);
    }

    @Test
    void registerPassengerForFlight() throws FlightException {
        Booking booking = BookController.registerPassengerForFlight("ms1", "sr1", FlightController.getAllFlight().get(0));
        assertNotNull(booking);
    }

    @Test
    void cancelRegistrationForFlight() throws FlightException {
        Booking booking = BookController.registerPassengerForFlight("ms1", "sr1", FlightController.getAllFlight().get(0));
        boolean deleted = BookController.cancelRegistrationForFlight(booking.getId());
        assertTrue(deleted);
    }

    @Test
    void getRegistrationsByPassanger() throws FlightException {
        Booking booking = BookController.registerPassengerForFlight("ms1", "sr1", FlightController.getAllFlight().get(0));
        List<Booking> bd = BookController.getRegistrationsByPassanger("ms1", "sr1");
        assertEquals(booking, bd.get(bd.indexOf(booking)));
    }

    @Test
    void writeData() throws FlightException {
        BookingService newBookingService = new BookingService(new CollectionBookingDao());
        Booking booking = newBookingService.registerPassengerForFlight("ms1", "sr1", FlightController.getAllFlight().get(0));
        newBookingService.writeData();
        newBookingService.loadData();
        System.out.println(newBookingService.getRegistrationsByPassanger("ms1", "sr1"));
        assertEquals(booking, newBookingService.getRegistrationsByPassanger("ms1", "sr1").get(newBookingService.getRegistrationsByPassanger("ms1", "sr1").indexOf(booking)));
    }

}