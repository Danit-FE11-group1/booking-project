package ua.com.danit.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Flight implements Serializable {

    private static final long serialVersionUID = 70971091051081211L;

    private final long id;
    private String name;
    private long date;
    private final String destination;
    private final String departure;
    Boat boat;
    private int freeSeats;
    private double cost;

    public Flight(City dep, City dest, long date, Boat boat, double cost) {
        String ident = ((dest.ordinal() + 1000) + "").substring(1);
        this.name = dep.getCountry() + "-" + ident;

        this.id = date + 100 * dep.ordinal() + dest.ordinal();

        this.date = date;
        this.destination = dest.getCityName();
        this.departure = dep.getCityName();
        this.boat = boat;
        this.cost = cost;

        this.freeSeats = boat.getSeats();
    }

    public String showDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm", Locale.getDefault());
        return dateFormat.format(new Date(this.date));
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public String getDeparture() {
        return departure;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return date == flight.date &&
                Double.compare(flight.cost, cost) == 0 &&
                Objects.equals(name, flight.name) &&
                Objects.equals(destination, flight.destination) &&
                Objects.equals(departure, flight.departure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, destination, departure, cost);
    }

    @Override
    public String toString() {
        return "Flight from "
                + this.departure
                + " to "
                + this.destination
                + ", "
                + this.showDate()
                + ";"
                + '\n'
                + this.freeSeats
                + "("
                + this.boat.getSeats()
                + ")"
                + '\n'
                + "price: "
                + this.cost
                + "$";
    }
}
