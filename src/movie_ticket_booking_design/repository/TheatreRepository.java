package movie_ticket_booking_design.repository;

import movie_ticket_booking_design.model.Theatre;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TheatreRepository {
    private final Map<Integer, Theatre> theatres = new ConcurrentHashMap<>();

    public Theatre getTheatre(Integer id) {
        return theatres.get(id);
    }

    public void addTheatre(Theatre theatre) {
        theatres.put(theatre.getId(), theatre);
    }
}
