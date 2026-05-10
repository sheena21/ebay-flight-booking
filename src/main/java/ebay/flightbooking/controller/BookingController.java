package ebay.flightbooking.controller;

import ebay.flightbooking.dto.BookingRequest;
import ebay.flightbooking.dto.BookingResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ebay.flightbooking.service.BookingService;

@RestController

@RequestMapping("/bookings")

public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> bookTicket(
            @Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.bookTicket(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

}