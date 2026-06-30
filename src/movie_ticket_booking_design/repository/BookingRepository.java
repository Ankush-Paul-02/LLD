package movie_ticket_booking_design.repository;

import movie_ticket_booking_design.model.Booking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookingRepository {

    private final Map<Integer, Booking> bookings = new ConcurrentHashMap<>();

    public void save(Booking booking) {
        bookings.put(booking.getId(), booking);
    }

    public Booking find(Integer id) {
        return bookings.get(id);
    }
}
