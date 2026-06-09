package elevator_design.entities;

import elevator_design.dto.Request;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Represents a single elevator. Run on its own thread.
 */
public class Elevator implements Runnable {

    private final Integer id;
    private final AtomicInteger currentFloor;
    private final AtomicReference<Direction> currentDirection;

    // ConcurrentSkipListSet keeps floors automatically sorted and its thread safe
    // O(log n) time cost for basic operations
    private final ConcurrentSkipListSet<Integer> upRequests;
    private final ConcurrentSkipListSet<Integer> downRequests;

    public Elevator(Integer id) {
        this.id = id;
        this.currentFloor = new AtomicInteger(0);
        this.currentDirection = new AtomicReference<Direction>(Direction.IDLE);
        this.upRequests = new ConcurrentSkipListSet<>();
        this.downRequests = new ConcurrentSkipListSet<>(Comparator.reverseOrder());
    }

    public void addRequest(Request request) {
        if (request.direction() == Direction.UP) {
            upRequests.add(request.requestedFloor());
        } else if (request.direction() == Direction.DOWN) {
            downRequests.add(request.requestedFloor());
        }

        if (currentDirection.get() == Direction.IDLE) {
            if (request.requestedFloor() > currentFloor.get()) {
                currentDirection.set(Direction.UP);
            } else if (request.requestedFloor() < currentFloor.get()) {
                currentDirection.set(Direction.DOWN);
            }
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                processRequest();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void processRequest() throws InterruptedException {
        if (currentDirection.get() == Direction.IDLE || currentDirection.get() == Direction.UP) {
            processUpRequest();
            processDownRequest();
        } else {
            processDownRequest();
            processUpRequest();
        }

        if (upRequests.isEmpty() && downRequests.isEmpty()) {
            currentDirection.set(Direction.IDLE);
        }
    }

    private void processUpRequest() throws InterruptedException {
        while (!upRequests.isEmpty()) {
            int nextFloor = upRequests.first();
            upRequests.remove(nextFloor);
            moveToNextFloor(nextFloor);
        }

        if (!downRequests.isEmpty()) {
            currentDirection.set(Direction.DOWN);
        }
    }

    private void processDownRequest() throws InterruptedException {
        while (!downRequests.isEmpty()) {
            int nextFloor = downRequests.first();
            downRequests.remove(nextFloor);
            moveToNextFloor(nextFloor);
        }

        if (!upRequests.isEmpty()) {
            currentDirection.set(Direction.UP);
        }
    }

    private void moveToNextFloor(int nextFloor) throws InterruptedException {
        System.out.println("Elevator::" + id + " is moving from floor " + currentFloor.get() + " to floor " + nextFloor);
        Thread.sleep(Math.abs(currentFloor.get() - nextFloor) * 500L);
        currentFloor.set(nextFloor);
        System.out.println("Elevator::" + id + " open doors at floor " + nextFloor);
    }

    public Integer getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public Direction getCurrentDirection() {
        return currentDirection.get();
    }
}
