package ebay.flightbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Flight {

    private String flightNumber;
    private int totalSeats;
    private int availableSeats;
}