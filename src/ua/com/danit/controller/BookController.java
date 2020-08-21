package ua.com.danit.controller;

import ua.com.danit.entity.Booking;
import ua.com.danit.entity.Flight;
import ua.com.danit.exception.FlightException;
import ua.com.danit.service.BookingService;
import ua.com.danit.service.LoggerService;

import java.util.List;

public class BookController {

    private final BookingService BookingService;
    private final FlightController FlightController;

    public BookController(BookingService bookingService, FlightController flightController) {
        BookingService = bookingService;
        FlightController = flightController;
    }

    public Booking registerPassengerForFlight(String name, String surname, Flight flight) throws FlightException {
        if (FlightController.bookingFlight(flight.getId())){
            Booking newBooking = this.BookingService.registerPassengerForFlight(name, surname, flight);
            LoggerService.info("Creating Booking for \"" + name + " " + surname + "\" with id: "+ newBooking.getId());
            return newBooking;
        }else {
            LoggerService.warn("Can`t create Booking to Flight #"+ flight.getId());
            return null;
        }
    }

    public boolean cancelRegistrationForFlight(String bookId) {
        boolean deleted = this.BookingService.cancelRegistrationForFlight(bookId);
        if(deleted){
            LoggerService.info("Safe deleted Booking with id: "+ bookId);
        }else {
            LoggerService.warn("Can`t delete booking with id: " + bookId);
        }
        return deleted;
    }

    public List<Booking> getRegistrationsByPassanger(String name, String surname) {
        LoggerService.info("Take Booking entries for \"" + name + " " + surname + "\"");
        return this.BookingService.getRegistrationsByPassanger(name, surname);
    }

}
