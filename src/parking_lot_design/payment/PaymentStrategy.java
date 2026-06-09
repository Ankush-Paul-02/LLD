package parking_lot_design.payment;

import parking_lot_design.ticket.Ticket;

public interface PaymentStrategy {
    double calculateFair(Ticket ticket);
}
