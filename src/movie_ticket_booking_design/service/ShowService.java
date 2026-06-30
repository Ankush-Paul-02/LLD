package movie_ticket_booking_design.service;

import movie_ticket_booking_design.model.Show;
import movie_ticket_booking_design.repository.ShowRepository;

import java.util.List;

public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public void createShow(Show show) {
        showRepository.addShow(show);
    }

    public Show getShow(Integer id) {
        Show show = showRepository.getShow(id);
        if (show == null) {
            throw new RuntimeException("No show with id " + id);
        }
        return show;
    }

    public List<Show> getShowsByMovieTitle(String movieTitle) {
        return showRepository.getShows()
                .stream()
                .filter(show -> show.movie().getTitle().equalsIgnoreCase(movieTitle))
                .toList();
    }
}
