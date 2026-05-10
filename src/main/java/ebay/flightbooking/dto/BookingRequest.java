package ebay.flightbooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookingRequest {

    @NotBlank(message = "Flight number is required")
    private String flightNumber;

    @NotBlank(message = "Passenger name is required")
    private String passengerName;
}