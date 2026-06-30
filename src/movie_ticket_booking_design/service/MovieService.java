package movie_ticket_booking_design.service;

import movie_ticket_booking_design.model.Movie;
import movie_ticket_booking_design.repository.MovieRepository;

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie getMovie(Integer id) {
        Movie movie = movieRepository.get(id);
        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }
        return movie;
    }
}
