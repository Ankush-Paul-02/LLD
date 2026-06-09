package elevator_design;

import elevator_design.controller.ElevatorController;
import elevator_design.entities.Direction;
import elevator_design.strategy.NearestElevatorStrategy;

public class ElevatorDemo {
    public static void main(String[] args) throws InterruptedException {
        ElevatorController controller = new ElevatorController(3, new NearestElevatorStrategy());

        System.out.println("Elevator demo started...");

        // simulate halfway requests
        controller.submitExternalRequest(5, Direction.UP);
        controller.submitExternalRequest(2, Direction.UP);

        Thread.sleep(2000);

        // simulate someone getting into 1 and pressing floor 10
        controller.submitInternalRequest(1, 10);

        Thread.sleep(3000);
    }
}
