package ua.com.danit.dao.booking;

import ua.com.danit.entity.Book;
import ua.com.danit.entity.Flight;

import java.io.IOException;
import java.util.List;

public interface Booking {
    List<Book> getAllBookins();

    Flight getFlightById(long id);

    boolean deleteFlightById(long id);

    boolean deleteFlight(Flight flight);

    boolean saveFlight(Flight flight);

    boolean loadData() throws IOException;
}
