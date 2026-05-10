package ebay.flightbooking.repository;

import jakarta.annotation.PostConstruct;
import ebay.flightbooking.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FlightRepository {

    private final Map<String, Flight> flights = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        flights.put("AI101", new Flight("AI101", 2, 2));
        flights.put("AI202", new Flight("AI202", 5, 5));
        flights.put("AI303", new Flight("AI303", 3, 3));
    }

    public Flight getFlight(String flightNumber) {
        return flights.get(flightNumber);
    }
}