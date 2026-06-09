package elevator_design.controller;

import elevator_design.dto.Request;
import elevator_design.entities.Direction;
import elevator_design.entities.Elevator;
import elevator_design.strategy.DispatchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElevatorController {

    private final List<Elevator> elevators;
    private DispatchStrategy dispatchStrategy;

    public ElevatorController(int numberOfElevators, DispatchStrategy dispatchStrategy) {
        this.dispatchStrategy = dispatchStrategy;
        this.elevators = new ArrayList<>(numberOfElevators);

        for (int i = 0; i < numberOfElevators; i++) {
            Elevator elevator = new Elevator(i + 1);
            elevators.add(elevator);
            new Thread(elevator).start();
        }
    }

    public DispatchStrategy setDispatchStrategy(DispatchStrategy dispatchStrategy) {
        return this.dispatchStrategy = dispatchStrategy;
    }

    public void submitExternalRequest(int floor, Direction direction) {
        Request request = new Request(floor, direction);
        Elevator optimalElevator = dispatchStrategy.selectOptimalElevator(elevators, request);

        System.out.println("Dispatcher assigned elevator:: " + optimalElevator.getId() + " to floor: " + floor);
        optimalElevator.addRequest(request);
    }

    public void submitInternalRequest(int elevatorId, int floor) {
        // check the elevator
        Optional<Elevator> optionalElevator = elevators.stream().filter(elevator -> elevator.getId() == elevatorId).findFirst();

        if (optionalElevator.isEmpty()) {
            System.out.println("No elevator with id " + elevatorId);
            throw new RuntimeException("No elevator with id " + elevatorId);
        } else {
            Elevator elevator = optionalElevator.get();
            if (floor == elevator.getCurrentFloor()) {
                return;
            }

            Direction dir = floor > elevator.getCurrentFloor()
                    ? Direction.UP
                    : Direction.DOWN;
            Request request = new Request(floor, dir);
            elevator.addRequest(request);
        }
    }
}
