package parking_lot_design.gate;

import parking_lot_design.parking_spot.ParkingManager;
import parking_lot_design.parking_spot.ParkingSpot;
import parking_lot_design.ticket.Ticket;
import parking_lot_design.vehicle.Vehicle;

import java.util.Random;

public class EntranceGate {

    private final ParkingManager parkingManager;

    public EntranceGate(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = parkingManager.findSpot(vehicle);
        if (parkingSpot == null) {
            throw new RuntimeException("Parking Spot Not Found");
        }
        parkingSpot.parkVehicle(vehicle);

        return new Ticket(
                new Random().nextInt(1000),
                vehicle,
                parkingSpot
        );
    }
}
