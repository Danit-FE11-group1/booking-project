package ua.com.danit.controller;

import ua.com.danit.service.BookingService;

public class BookController {
    private final BookingService BookingService;

    public BookController(ua.com.danit.service.BookingService bookingService) {
        BookingService = bookingService;
    }

    public ua.com.danit.service.BookingService getBookingService() {
        return BookingService;
    }
}
