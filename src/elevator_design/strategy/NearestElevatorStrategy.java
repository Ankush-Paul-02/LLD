package elevator_design.strategy;

import elevator_design.dto.Request;
import elevator_design.entities.Direction;
import elevator_design.entities.Elevator;

import java.util.List;

public class NearestElevatorStrategy implements DispatchStrategy {


    @Override
    public Elevator selectOptimalElevator(List<Elevator> elevators, Request request) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - request.requestedFloor());
            Direction direction = elevator.getCurrentDirection();

            // check elevator is idle or moving towards the request in the same direction
            boolean isMovingForward = (direction == Direction.UP && elevator.getCurrentFloor() <= request.requestedFloor()) ||
                    (direction == Direction.DOWN && elevator.getCurrentFloor() >= request.requestedFloor());

            if (direction == Direction.IDLE || isMovingForward) {
                minDistance = Math.min(minDistance, distance);
                bestElevator = elevator;
            }
        }

        return bestElevator != null ? bestElevator : elevators.getFirst();
    }
}
