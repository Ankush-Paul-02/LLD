package movie_ticket_booking_design.model;

public abstract class Seat {
    private final Integer id;
    private final Double price;

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public Seat(Integer id, Double price) {
        this.id = id;
        this.price = price;
    }
}
