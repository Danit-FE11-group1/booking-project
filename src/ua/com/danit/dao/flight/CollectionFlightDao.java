package ua.com.danit.dao.flight;


import ua.com.danit.entity.Flight;
import ua.com.danit.service.LoggerService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CollectionFlightDao implements FlightDao {

    private List<Flight> flights = new ArrayList<>();

    @Override
    public List<Flight> getAllFlights() {
        LoggerService.info("Get all flights");
        return this.flights;
    }

    @Override
    public Flight getFlightById(long id) {
        for (Flight flight : this.flights) {
            if (flight.getId() == id) {
                LoggerService.info("Get flight " + id);
                return flight;
            }
        }
        LoggerService.error("Flight " + id + " not found");
        return null;
    }

    @Override
    public boolean deleteFlightById(long id) {
        for (Flight flight : flights) {
            if (flight.getId() == id) {
                LoggerService.info("Delete flight " + id);
                return this.flights.remove(flight);
            }
        }
        LoggerService.error("Flight " + id + " not found");
        return false;
    }

    @Override
    public boolean deleteFlight(Flight flight) {
        boolean result = this.flights.remove(flight);
        if (result) {
            LoggerService.info("Delete flight " + flight.getId());
        } else {
            LoggerService.error("Flight " + flight.getId() + " not found");
        }
        return result;
    }

    @Override
    public boolean saveFlight(Flight flight) {
        boolean result = this.flights.add(flight);
        if (result) {
            LoggerService.info("Save flight " + flight.getId());
        } else {
            LoggerService.error("Flight " + flight.getId() + " not found");
        }
        return result;
    }

    @Override
    public boolean loadData() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("flights.data"));
        this.flights.forEach(flight -> {
            try {
                objectOutputStream.writeObject(flight);
            } catch (Exception e) {
                LoggerService.error(e.getMessage());
                e.printStackTrace();
            }
        });
        LoggerService.info("Load data");
        objectOutputStream.close();
        return true;
    }
}
