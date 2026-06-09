package parking_lot_design.parking_spot;

import parking_lot_design.vehicle.Vehicle;
import parking_lot_design.vehicle.VehicleType;

public abstract class ParkingSpot {
    private final Integer id;
    private boolean isAvailable;
    private Vehicle vehicle;
    private final double price;

    public ParkingSpot(int id, double price) {
        this.id = id;
        this.price = price;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isAvailable = false;
    }

    public void unparkVehicle() {
        this.vehicle = null;
        this.isAvailable = true;
    }

    public double getPrice() {
        return price;
    }

    public abstract VehicleType getSpotType();
}
