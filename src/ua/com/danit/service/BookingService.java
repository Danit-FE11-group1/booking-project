package ua.com.danit.service;

import ua.com.danit.dao.booking.CollectionBookingDao;
import ua.com.danit.entity.Booking;
import ua.com.danit.entity.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private final CollectionBookingDao CollectionBookingDao;

    public BookingService(CollectionBookingDao collectionBookingDao) {
        CollectionBookingDao = collectionBookingDao;
    }

    public Booking registerPassengerForFlight(String name, String surname, Flight flight){
        flight.bookingFlight();
        return CollectionBookingDao.saveBook(new Booking(name, surname, flight));

    }

    public boolean cancelRegistrationForFlight(String bookId) {
        return CollectionBookingDao.deleteBookById(bookId);
    }

    public List<Booking> getRegistrationsByPassanger(String name, String surname) {
        return CollectionBookingDao.getAllBookins().stream()
                .filter(el -> el.getName().equals(name) && el.getSurname().equals(surname))
                .collect(Collectors.toList());
    }
}
