package movie_ticket_booking_design.strategy.payment;

import movie_ticket_booking_design.model.Booking;

public interface PaymentStrategy {
    boolean pay(Booking booking);
}
