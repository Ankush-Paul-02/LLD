package parking_lot_design.payment;

import parking_lot_design.ticket.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyPaymentStrategy implements PaymentStrategy {
    @Override
    public double calculateFair(Ticket ticket) {
        long hours = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toHours();

        if (hours == 0) {
            hours = 1;
        }

        return hours * ticket.getSpot().getPrice();
    }
}
