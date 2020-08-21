package ua.com.danit.dao.booking;

import ua.com.danit.entity.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CollectionBookingDao implements BookingDao {
    private List<Booking> bookings = new ArrayList<>();
    private long idCounter = 0;

    @Override
    public List<Booking> getAllBookins() {
        return Collections.unmodifiableList(this.bookings);
    }

    @Override
    public Booking getBookById(String id) {
        return bookings.stream()
                .filter(el -> el.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteBookById(String id) {
        return bookings.removeIf(el -> el.getId().equals(id));
    }

    @Override
    public boolean deleteBook(Booking booking) {
        return bookings.remove(booking);
    }

    @Override
    public Booking saveBook(Booking booking) {
        if (booking.getId() == null) {
            Random random = new Random();
            booking.setId(Long.toString(idCounter) + '-' + random.nextInt(15) + '-' + random.nextInt(15));
            idCounter++;
        }
        bookings.add(booking);
        return booking;
    }

    @Override
    public boolean loadData() throws IOException {
        if(new File("Books.data").exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Books.data"));
            boolean flag = true;
            while (flag) {
                try {
                    this.saveBook((Booking) objectInputStream.readObject());
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
        this.bookings.forEach(book -> {
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
