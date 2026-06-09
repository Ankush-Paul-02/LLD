package parking_lot_design.gate;

import parking_lot_design.payment.PaymentStrategy;
import parking_lot_design.ticket.Ticket;

public class ExitGate {
    private final PaymentStrategy paymentStrategy;

    public ExitGate(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public double exitVehicle(Ticket ticket) {
        double amount = paymentStrategy.calculateFair(ticket);

        ticket.getSpot().unparkVehicle();
        return amount;
    }
}