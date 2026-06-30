package movie_ticket_booking_design.model;

import java.time.LocalDateTime;
import java.util.List;

public record Show(Integer id, Movie movie, Screen screen, Theatre theatre, LocalDateTime startTime,
                   LocalDateTime endTime) {

    public List<Seat> getSeats() {
        return screen.getSeats();
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", movie=" + movie +
                ", screen=" + screen +
                ", theatre=" + theatre +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
