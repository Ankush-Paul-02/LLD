package parking_lot_design.vehicle;

public class Vehicle {
    private Integer id;
    private VehicleType vehicleType;

    public Vehicle(Integer id, VehicleType vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
