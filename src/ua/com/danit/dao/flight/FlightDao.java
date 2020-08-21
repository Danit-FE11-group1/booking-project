package ua.com.danit.dao.flight;

import ua.com.danit.entity.Flight;

import java.io.IOException;
import java.util.List;

public interface FlightDao {
    List<Flight> getAllFlights();

    Flight getFlightById(long id);

    boolean deleteFlightById(long id);

    boolean deleteFlight(Flight flight);

    boolean saveFlight(Flight flight);

    boolean loadData() throws IOException;
}
