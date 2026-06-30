package movie_ticket_booking_design.repository;

import movie_ticket_booking_design.model.Show;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShowRepository {
    private final Map<Integer, Show> shows = new ConcurrentHashMap<>();

    public void addShow(Show show) {
        shows.put(show.id(), show);
    }

    public Show getShow(Integer id) {
        return shows.get(id);
    }

    public List<Show> getShows() {
        return new CopyOnWriteArrayList<>(shows.values());
    }
}
