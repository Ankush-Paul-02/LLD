package movie_ticket_booking_design.model;

import movie_ticket_booking_design.enums.SeatType;

public class ReclinerSeat extends Seat {
    public ReclinerSeat(Integer id, Double price) {
        super(id, price);
    }

    public SeatType getSeatType() {
        return SeatType.REGULAR;
    }
}
