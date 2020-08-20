package ua.com.danit.dao.booking;

import ua.com.danit.entity.Book;

import java.io.IOException;
import java.util.List;

public interface Booking {
    List<Book> getAllBookins();

    Book getBookById(String id);

    boolean deleteBookById(String id);

    boolean deleteBook(Book Book);

    Book saveBook(Book Book);

    boolean loadData() throws IOException;
    boolean writeData() throws IOException;
}
