package ebay.flightbooking.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Booking {


    private String bookingId;

    @NotBlank(message = "Flight number is required")
    private String flightNumber;
    @NotBlank(message = "Passenger name is required")
    private String passengerName;
    private String status;
}