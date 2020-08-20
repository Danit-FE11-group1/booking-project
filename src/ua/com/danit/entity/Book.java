package ua.com.danit.entity;

import java.io.Serializable;

public class Book implements Serializable {
    private final String passport;
    private final Flight flight;


    //here: Customer (or passport) and AirFlight
    public Book(String passport, Flight flight) {
        this.passport = passport;
        this.flight = flight;
    }

    public String getPassport() {
        return passport;
    }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!passport.equals(book.passport)) return false;
        return flight.equals(book.flight);
    }

    @Override
    public int hashCode() {
        int result = passport.hashCode();
        result = 31 * result + flight.hashCode();
        return result;
    }

}
