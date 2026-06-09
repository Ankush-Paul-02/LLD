package parking_lot_design.payment;

import parking_lot_design.ticket.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

public class MinutePaymentStrategy implements PaymentStrategy {
    @Override
    public double calculateFair(Ticket ticket) {
        long minutes = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toMinutes();

        return minutes * ticket.getSpot().getPrice();
    }
}
