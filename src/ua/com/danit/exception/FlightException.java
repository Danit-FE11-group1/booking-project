package ua.com.danit.exception;

public class FlightException extends Exception {

    public FlightException() {
        super("Flight exception!");
    }

    public FlightException(String message) {
        super(message);
    }
}
