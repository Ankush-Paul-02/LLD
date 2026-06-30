package movie_ticket_booking_design.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Screen {
    private final Integer id;
    private final Map<Integer, Seat> seats;

    public Screen(Integer id) {
        this.id = id;
        this.seats = new ConcurrentHashMap<>();
    }

    public Integer getId() {
        return id;
    }

    public void addSeat(Seat seat) {
        seats.put(seat.getId(), seat);
    }

    public List<Seat> getSeats() {
        return new CopyOnWriteArrayList<>(seats.values());
    }
}
