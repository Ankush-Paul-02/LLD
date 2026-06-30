package movie_ticket_booking_design.model;

import movie_ticket_booking_design.enums.BookingStatus;
import movie_ticket_booking_design.enums.PaymentType;

import java.util.List;

public class Booking {
    private final Integer id;
    private final Integer userId;
    private final Integer showId;
    private final List<Integer> seatIds;
    private BookingStatus bookingStatus;
    private PaymentType paymentType;
    private final Double price;

    public Booking(Integer id, Integer userId, Integer showId, List<Integer> seatIds, BookingStatus bookingStatus, PaymentType paymentType, Double price) {
        this.id = id;
        this.userId = userId;
        this.showId = showId;
        this.seatIds = seatIds;
        this.bookingStatus = bookingStatus;
        this.paymentType = paymentType;
        this.price = price;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getShowId() {
        return showId;
    }

    public List<Integer> getSeatIds() {
        return seatIds;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", userId=" + userId +
                ", showId=" + showId +
                ", seatIds=" + seatIds +
                ", bookingStatus=" + bookingStatus +
                ", paymentType=" + paymentType +
                ", price=" + price +
                '}';
    }
}
