package traffic;

import java.util.List;

public class QueueThread extends Thread {
    private int secondsSinceSystemStart = 0;
    private volatile State state = State.NOT_STARTED;
    private CircularQueue<Road> circularQueue;

    public QueueThread(CircularQueue<Road> circularQueue) {
        super("QueueThread");
        this.circularQueue = circularQueue;
    }

    public enum State {
        NOT_STARTED,
        MENU,
        SYSTEM
    }

    public void setState(State state) {
        this.state = state;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                if (state == State.SYSTEM || state == State.MENU) {
                    secondsSinceSystemStart++;
                }
                if (state == State.SYSTEM) {
                    System.out.println("! " + secondsSinceSystemStart + "s. have passed since system startup !");
                    System.out.println("! Number of roads: " + TrafficManager.numberOfRoads + " !");
                    System.out.println("! Interval: " + TrafficManager.numberOfRoads + " !");
                    if (!TrafficManager.roadQueue.isEmpty()) {
                        Road.roadStatus();
                        if (Road.remainingTime == 0) Road.remainingTime = TrafficManager.interval;
                        if (!circularQueue.getAllElements().isEmpty()) {
                            System.out.println();
                            circularQueue.getAllElements().forEach(System.out::println);
                            System.out.println();
                        }
                    }
                    System.out.println("! Press \"Enter\" to open menu !");
                    ClearConsoleOutput.clearConsoleOutput();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


