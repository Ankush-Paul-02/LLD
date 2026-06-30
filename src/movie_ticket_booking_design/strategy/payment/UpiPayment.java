package movie_ticket_booking_design.strategy.payment;

import movie_ticket_booking_design.model.Booking;

public class UpiPayment implements PaymentStrategy {
    @Override
    public boolean pay(Booking booking) {
        System.out.println("Paid: " + booking.getPrice() + " via UPI payment");
        return true;
    }
}
