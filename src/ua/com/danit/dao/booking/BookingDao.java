package ua.com.danit.dao.booking;

import ua.com.danit.entity.Booking;

import java.io.IOException;
import java.util.List;

public interface BookingDao {
    List<Booking> getAllBookins();

    Booking getBookById(String id);

    boolean deleteBookById(String id);

    boolean deleteBook(Booking Booking);

    Booking saveBook(Booking Booking);

    boolean loadData() throws IOException;
    boolean writeData() throws IOException;
}
