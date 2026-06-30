package movie_ticket_booking_design.repository;

import movie_ticket_booking_design.model.Movie;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MovieRepository {
    private final Map<Integer, Movie> movies = new ConcurrentHashMap<>();

    public void save(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    public Movie get(Integer id) {
        return movies.get(id);
    }
}
