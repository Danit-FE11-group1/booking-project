package ua.com.danit.dao.flight;


import ua.com.danit.entity.Flight;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CollectionFlightDao implements FlightDao {

    private List<Flight> flights = new ArrayList<>();

    @Override
    public List<Flight> getAllFamilies() {
        return this.flights;
    }

    @Override
    public Flight getFlightById(long id) {
        for (Flight flight : this.flights) {
            if (flight.getId() == id) {
                return flight;
            }
        }
        return null;
    }

    @Override
    public boolean deleteFlightById(long id) {
        for (Flight flight : flights) {
            if (flight.getId() == id) {
                return this.flights.remove(flight);
            }
        }
        return false;
    }

    @Override
    public boolean deleteFlight(Flight flight) {
        return this.flights.remove(flight);
    }

    @Override
    public boolean saveFlight(Flight flight) {
        return this.flights.add(flight);
    }

    @Override
    public boolean loadData() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("flights.data"));
        this.flights.forEach(flight -> {
            try {
                objectOutputStream.writeObject(flight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        objectOutputStream.close();
        return true;
    }
}
