package elevator_design.strategy;

import elevator_design.dto.Request;
import elevator_design.entities.Elevator;

import java.util.List;

public interface DispatchStrategy {
    Elevator selectOptimalElevator(List<Elevator> elevators, Request request);
}
