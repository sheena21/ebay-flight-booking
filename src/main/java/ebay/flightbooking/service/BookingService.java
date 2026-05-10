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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service

public class BookingService {

    private final FlightRepository flightRepository;

    private final BookingRepository bookingRepository;

    /*

     Separate lock per flight to reduce

     unnecessary contention across bookings

     for different flights.

     */

    private final ConcurrentMap<String, Object> flightLocks =

            new ConcurrentHashMap<>();

    public BookingService(

            FlightRepository flightRepository,

            BookingRepository bookingRepository) {

        this.flightRepository = flightRepository;

        this.bookingRepository = bookingRepository;

    }

    public BookingResponse bookTicket(

            BookingRequest request) {

        Flight flight = getFlightOrThrow(

                request.getFlightNumber());

        Object flightLock = flightLocks.computeIfAbsent(

                flight.getFlightNumber(),

                key -> new Object());

        synchronized (flightLock) {

            validateSeatAvailability(flight);

            updateAvailableSeats(flight);

            Booking booking = createBooking(request);

            bookingRepository.save(booking);

            return mapToResponse(booking);

        }

    }

    private Flight getFlightOrThrow(

            String flightNumber) {

        Flight flight = flightRepository

                .getFlight(flightNumber);

        if (flight == null) {

            throw new FlightNotFoundException(

                    "Flight not found");

        }

        return flight;

    }

    private void validateSeatAvailability(

            Flight flight) {

        if (flight.getAvailableSeats() <= 0) {

            throw new NoSeatsAvailableException(

                    "No seats available");

        }

    }

    private void updateAvailableSeats(

            Flight flight) {

        flight.setAvailableSeats(

                flight.getAvailableSeats() - 1);

    }

    private Booking createBooking(

            BookingRequest request) {

        return Booking.builder()

                .bookingId(UUID.randomUUID().toString())

                .flightNumber(request.getFlightNumber())

                .passengerName(request.getPassengerName())

                .status("CONFIRMED")

                .build();

    }

    private BookingResponse mapToResponse(

            Booking booking) {

        return BookingResponse.builder()

                .bookingId(booking.getBookingId())

                .flightNumber(booking.getFlightNumber())

                .passengerName(booking.getPassengerName())

                .status(booking.getStatus())

                .build();

    }

}