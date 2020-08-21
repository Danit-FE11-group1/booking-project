package ua.com.danit.service;

import ua.com.danit.dao.flight.CollectionFlightDao;
import ua.com.danit.entity.Boat;
import ua.com.danit.entity.City;
import ua.com.danit.entity.Flight;
import ua.com.danit.exception.FlightException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FlightService {

    private final CollectionFlightDao collectionFlightDao;

    public FlightService(CollectionFlightDao collectionFlightDao) {
        this.collectionFlightDao = collectionFlightDao;
    }

    public List<Flight> getAllFlights() {
        return this.collectionFlightDao.getAllFlights();
    }

    public List<Flight> searchFlights(String departure, String destination, int freeSeats) {
        return this.collectionFlightDao.getAllFlights().stream()
                .filter(f -> f.getDestination().equals(destination))
                .filter(f -> f.getDeparture().equals(departure))
                .filter(f -> f.getFreeSeats() >= freeSeats)
                .collect(Collectors.toList());
    }

    public List<Flight> searchFlights(String departure, String destination, long date, int freeSeats) {
        return this.collectionFlightDao.getAllFlights().stream()
                .filter(f -> f.getDestination().equals(destination))
                .filter(f -> f.getDeparture().equals(departure))
                .filter(f -> {
                    Timestamp flightTimestamp = new Timestamp(f.getDate());
                    LocalDate flightDate = flightTimestamp.toLocalDateTime().toLocalDate();
                    Timestamp currTimestamp = new Timestamp(date);
                    LocalDate currDate = currTimestamp.toLocalDateTime().toLocalDate();
                    return flightDate.compareTo(currDate) == 0;
                })
                .filter(f -> f.getFreeSeats() >= freeSeats)
                .collect(Collectors.toList());
    }

    public boolean bookingFlight(long id) throws FlightException {
        Flight flight = this.collectionFlightDao.getFlightById(id);
        if (flight.getFreeSeats() == 0) {
            throw new FlightException("Not enough seats");
        }
        flight.bookingFlight();
        return true;
    }

    public Flight getFlightById(long id) {
        return this.collectionFlightDao.getFlightById(id);
    }

    public List<Flight> getDayFlights() {
        long startDate = new Date().getTime();
        long endDate = startDate + 24 * 60 * 60 * 1000;
        return this.collectionFlightDao.getAllFlights().stream()
                .filter(f -> f.getDate() <= endDate && f.getDate() > startDate)
                .collect(Collectors.toList());
    }

    private Flight createFlight() {
        Random random = new Random();

        int depCityIndex = random.nextInt(City.values().length);
        int destCityIndex = depCityIndex;

        while (depCityIndex == destCityIndex) {
            destCityIndex = random.nextInt(City.values().length);
        }

        City depCity = City.values()[depCityIndex];
        City destCity = City.values()[destCityIndex];

        long hourInMillis = 1000 * 60 * 60;

        long today = new Date().getTime();
        long month = random.nextInt(12) * hourInMillis * 24 * 30;
        long day = random.nextInt(30) * hourInMillis * 24;
        long hour = random.nextInt(24) * hourInMillis;

        long minutes = random.nextInt(4) * 15 * 1000 * 60;
        long afterDate = month + day + hour;
        long date = (today + afterDate) / hourInMillis;
        date = date * hourInMillis + minutes;

        int boatIndex = random.nextInt(Boat.values().length);
        Boat boat = Boat.values()[boatIndex];

        double cost = 100.00 + random.nextInt(100);

        return new Flight(depCity, destCity, date, boat, cost);
    }

    public void uploadFlights() throws IOException {
        if (!(new File("flights.data").exists())) {
            for (int i = 0; i < 1000; i++) {
                Flight flight = createFlight();
                this.collectionFlightDao.saveFlight(flight);
            }
            this.collectionFlightDao.loadData();
        } else {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("flights.data"));
            boolean flag = true;
            while (flag) {
                try {
                    this.collectionFlightDao.saveFlight((Flight) objectInputStream.readObject());
                } catch (Exception e) {
                    flag = false;
                }
            }
        }
    }

    public void loadFlights() throws IOException {
        this.collectionFlightDao.loadData();
    }

    public boolean deleteFlightById(long id) {
        return this.collectionFlightDao.deleteFlightById(id);
    }

    public boolean deleteFlight(Flight flight) {
        return this.collectionFlightDao.deleteFlight(flight);
    }

}
