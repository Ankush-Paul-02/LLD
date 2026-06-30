package movie_ticket_booking_design.factory;

import movie_ticket_booking_design.enums.PaymentType;
import movie_ticket_booking_design.strategy.payment.CardPayment;
import movie_ticket_booking_design.strategy.payment.PaymentStrategy;
import movie_ticket_booking_design.strategy.payment.UpiPayment;

public class PaymentStrategyFactory {

    public static PaymentStrategy getPaymentStrategy(PaymentType paymentType) {
        return switch (paymentType) {
            case PaymentType.CARD -> new CardPayment();
            case PaymentType.UPI -> new UpiPayment();
        };
    }
}
