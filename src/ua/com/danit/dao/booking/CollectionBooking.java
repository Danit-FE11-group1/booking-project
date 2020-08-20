package ua.com.danit.dao.booking;

import ua.com.danit.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CollectionBooking implements Booking {
    private List<Book> books = new ArrayList<>();
    private long idCounter = 0;

    @Override
    public List<Book> getAllBookins() {
        return Collections.unmodifiableList(this.books);
    }

    @Override
    public Book getBookById(String id) {
        return books.stream()
                .filter(el -> el.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteBookById(String id) {
        return books.removeIf(el -> el.getId().equals(id));
    }

    @Override
    public boolean deleteBook(Book book) {
        return books.remove(book);
    }

    @Override
    public Book saveBook(Book book) {
        if (book.getId() == null) {
            Random random = new Random();
            book.setId(Long.toString(idCounter) + '-' + random.nextInt(15) + '-' + random.nextInt(15));
            idCounter++;
        }
        books.add(book);
        return book;
    }

    @Override
    public boolean loadData() throws IOException {
        if(new File("Books.data").exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Books.data"));
            boolean flag = true;
            while (flag) {
                try {
                    this.saveBook((Book) objectInputStream.readObject());
                } catch (Exception e) {
                    flag = false;
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public boolean writeData() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Books.data"));
        this.books.forEach(book -> {
            try {
                objectOutputStream.writeObject(book);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        objectOutputStream.close();
        return true;
    }

}
