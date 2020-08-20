package ua.com.danit.service;

import ua.com.danit.dao.booking.CollectionBooking;
import ua.com.danit.entity.Book;
import ua.com.danit.entity.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private final CollectionBooking CollectionBooking;

    public BookingService(CollectionBooking collectionBooking) {
        CollectionBooking = collectionBooking;
    }

    public Book registerPassengerForFlight(String name, String surname, Flight flight) {
        return CollectionBooking.saveBook(new Book(name, surname, flight));
    }

    public boolean cancelRegistrationForFlight(String bookId) {
        return CollectionBooking.deleteBookById(bookId);
    }

    public List<Book> getRegistrationsByPassanger(String name, String surname) {
        return CollectionBooking.getAllBookins().stream()
                .filter(el -> el.getName().equals(name) && el.getSurname().equals(surname))
                .collect(Collectors.toList());
    }
}
