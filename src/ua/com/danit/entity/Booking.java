package ua.com.danit.entity;

import java.io.Serializable;

public class Booking implements Serializable {
    private final String name;
    private final String surname;
    private final Flight flight;
    private String id;
    private static final long serialVersionUID = 72856348767356L;

    public Booking(String name, String surname, Flight flight, String id) {
        this.name = name;
        this.surname = surname;
        this.flight = flight;
        this.id = id;
    }
    public Booking(String name, String surname, Flight flight) {
        this.name = name;
        this.surname = surname;
        this.flight = flight;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;

        Booking booking = (Booking) o;

        if (!name.equals(booking.name)) return false;
        if (!surname.equals(booking.surname)) return false;
        if (!flight.equals(booking.flight)) return false;
        return id.equals(booking.id);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + flight.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", flight=" + flight +
                ", id='" + id + '\'' +
                '}';
    }
}
