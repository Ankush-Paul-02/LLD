package elevator_design.dto;

import elevator_design.entities.Direction;

public record Request(
        int requestedFloor,
        Direction direction
) {

}
