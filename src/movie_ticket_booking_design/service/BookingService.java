package movie_ticket_booking_design.service;

import movie_ticket_booking_design.enums.BookingStatus;
import movie_ticket_booking_design.enums.PaymentType;
import movie_ticket_booking_design.factory.PaymentStrategyFactory;
import movie_ticket_booking_design.model.Booking;
import movie_ticket_booking_design.model.Seat;
import movie_ticket_booking_design.model.Show;
import movie_ticket_booking_design.repository.BookingRepository;
import movie_ticket_booking_design.strategy.locking.LockProvider;
import movie_ticket_booking_design.strategy.payment.PaymentStrategy;

import java.util.List;
import java.util.Random;

public class BookingService {

    private final LockProvider lockProvider;
    private final BookingRepository bookingRepository;

    public BookingService(LockProvider lockProvider, BookingRepository bookingRepository) {
        this.lockProvider = lockProvider;
        this.bookingRepository = bookingRepository;
    }

    private static final long ttl = 5000L;

    public Booking createBooking(Integer userId, Show show, List<Integer> seatIds) {
        for (Integer seatId : seatIds) {
            String key = show.id() + "_" + seatId;
            if (!lockProvider.tryLock(key, userId, ttl)) {
                throw new RuntimeException("Seat is currently unavailable!");
            }
        }

        double totalPrice = 0;
        for (Seat seat : show.getSeats()) {
            if (seatIds.contains(seat.getId())) {
                totalPrice += seat.getPrice();
            }
        }

        Booking booking = new Booking(
                new Random().nextInt(),
                userId,
                show.id(),
                seatIds,
                BookingStatus.CREATED,
                null,
                totalPrice
        );

        bookingRepository.save(booking);
        System.out.println("Booking " + booking.getId() + " has been created!");
        return booking;
    }

    public void confirmBooking(Booking booking, PaymentType paymentType) {
        if (!booking.getBookingStatus().equals(BookingStatus.CREATED)) {
            throw new RuntimeException("Booking status is not CREATED!");
        }

        for (Integer seatId : booking.getSeatIds()) {
            String key = booking.getId() + "_" + seatId;
            if (lockProvider.isLockExpired(key) || !lockProvider.isLockBy(key, booking.getUserId())) {
                throw new RuntimeException("Seat is expired!");
            }
        }

        booking.setPaymentType(paymentType);

        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getPaymentStrategy(paymentType);

        paymentStrategy.pay(booking);

        for (Integer seatId : booking.getSeatIds()) {
            String key = booking.getId() + "_" + seatId;
            if (lockProvider.isLockBy(key, booking.getUserId())) {
                lockProvider.unlock(key);
            }
        }

        booking.setBookingStatus(BookingStatus.CONFIRMED);
        System.out.println("Booking " + booking.getId() + " has been confirmed!");
    }
}
