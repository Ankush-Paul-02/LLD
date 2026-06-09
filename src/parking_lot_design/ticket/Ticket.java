package parking_lot_design.ticket;

import parking_lot_design.parking_spot.ParkingSpot;
import parking_lot_design.vehicle.Vehicle;

import java.time.LocalDateTime;

public class Ticket {
    private final Integer id;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;

    public Ticket(int id, Vehicle vehicle, ParkingSpot spot) {
        this.id = id;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
