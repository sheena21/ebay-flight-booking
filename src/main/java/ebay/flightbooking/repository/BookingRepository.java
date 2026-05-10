package ebay.flightbooking.repository;

import ebay.flightbooking.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepository {

    private final List<Booking> bookings = new ArrayList<>();

    public void save(Booking booking) {
        bookings.add(booking);
    }
}