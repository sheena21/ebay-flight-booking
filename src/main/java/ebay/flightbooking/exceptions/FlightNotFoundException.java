package ebay.flightbooking.exceptions;

public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(String message) {
        super(message);
    }
}