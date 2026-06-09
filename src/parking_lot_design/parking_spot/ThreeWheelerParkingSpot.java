package parking_lot_design.parking_spot;

import parking_lot_design.vehicle.VehicleType;

public class ThreeWheelerParkingSpot extends ParkingSpot {
    public ThreeWheelerParkingSpot(int id, double price) {
        super(id, price);
    }

    @Override
    public VehicleType getSpotType() {
        return VehicleType.THREE_WHEELER;
    }
}
