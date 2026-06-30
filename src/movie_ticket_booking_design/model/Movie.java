package movie_ticket_booking_design.model;

public class Movie {
    private final Integer id;
    private final String title;
    private final Integer durationInMinutes;

    public Movie(Integer id, String title, Integer durationInMinutes) {
        this.id = id;
        this.title = title;
        this.durationInMinutes = durationInMinutes;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }
}
