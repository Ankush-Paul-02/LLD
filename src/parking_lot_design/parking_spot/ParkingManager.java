package parking_lot_design.parking_spot;

import parking_lot_design.vehicle.Vehicle;
import parking_lot_design.vehicle.VehicleType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingManager {
    //    private final List<ParkingSpot> parkingSpots;
    private final Map<VehicleType, List<ParkingSpot>> parkingSpots;

    public ParkingManager(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = new ConcurrentHashMap<>();
    }

    public ParkingSpot findSpot(Vehicle vehicle) {

        for (ParkingSpot spot : parkingSpots.get(vehicle.getVehicleType())) {
            if (spot.isAvailable()) {
                return spot;
            }
        }
        return null;
    }
}
