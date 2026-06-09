package parking_lot_design.parking_spot;

import parking_lot_design.vehicle.VehicleType;

public class TwoWheelerParkingSpot extends ParkingSpot {

    public TwoWheelerParkingSpot(int id, double price) {
        super(id, price);
    }

    @Override
    public VehicleType getSpotType() {
        return VehicleType.TWO_WHEELER;
    }
}
