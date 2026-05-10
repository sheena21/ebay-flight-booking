package ebay.flightbooking.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Booking {

    private String bookingId;
    private String flightNumber;
    private String passengerName;
    private String status;
}