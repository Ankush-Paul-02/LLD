package movie_ticket_booking_design.service;

import movie_ticket_booking_design.model.Theatre;
import movie_ticket_booking_design.repository.TheatreRepository;

public class TheatreService {
    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public void addTheatre(Theatre theatre) {
        theatreRepository.addTheatre(theatre);
    }

    public Theatre getTheatre(Integer theatreId) {
        Theatre theatre = theatreRepository.getTheatre(theatreId);
        if (theatre == null) {
            throw new RuntimeException("No theatre found with id: " + theatreId);
        }

        return theatre;
    }
}
