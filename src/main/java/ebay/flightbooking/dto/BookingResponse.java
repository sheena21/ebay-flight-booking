package ebay.flightbooking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {

    private String bookingId;
    private String flightNumber;
    private String passengerName;
    private String status;
}