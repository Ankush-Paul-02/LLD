package movie_ticket_booking_design.model;

import movie_ticket_booking_design.enums.SeatType;

public class RegularSeat extends Seat {

    public RegularSeat(Integer id, Double price) {
        super(id, price);
    }

    public SeatType getSeatType() {
        return SeatType.REGULAR;
    }
}
