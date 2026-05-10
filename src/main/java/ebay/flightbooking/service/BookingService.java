package ebay.flightbooking.service;



import ebay.flightbooking.dto.BookingRequest;
import ebay.flightbooking.dto.BookingResponse;
import ebay.flightbooking.exceptions.FlightNotFoundException;
import ebay.flightbooking.exceptions.NoSeatsAvailableException;
import ebay.flightbooking.model.Booking;
import ebay.flightbooking.model.Flight;
import org.springframework.stereotype.Service;
import ebay.flightbooking.repository.BookingRepository;
import ebay.flightbooking.repository.FlightRepository;

import java.util.UUID;

@Service
public class BookingService {

    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    public BookingService(
            FlightRepository flightRepository,
            BookingRepository bookingRepository) {

        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }

    public synchronized BookingResponse bookTicket(BookingRequest request) {

        Flight flight = flightRepository.getFlight(request.getFlightNumber());

        if (flight == null) {
            throw new FlightNotFoundException("Flight not found");
        }

        if (flight.getAvailableSeats() <= 0) {
            throw new NoSeatsAvailableException("No seats available");
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);

        Booking booking = Booking.builder()
                .bookingId(UUID.randomUUID().toString())
                .flightNumber(request.getFlightNumber())
                .passengerName(request.getPassengerName())
                .status("CONFIRMED")
                .build();

        bookingRepository.save(booking);

        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .flightNumber(booking.getFlightNumber())
                .passengerName(booking.getPassengerName())
                .status(booking.getStatus())
                .build();
    }
}