package traffic;

import java.util.Comparator;

public class Road {
    private final String name;
    private boolean isOpen;
    private int timeToChangeStatus;
    static int remainingTime = TrafficManager.interval + 1;
    static CircularQueue<Road> circularQueue;

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    public Road(String name, CircularQueue<Road> roadCircularQueue) {
        this.name = name;
        circularQueue = roadCircularQueue;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public int getTimeToChangeStatus() {
        return timeToChangeStatus;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setTimeToChangeStatus(int timeToChangeStatus) {
        this.timeToChangeStatus = timeToChangeStatus;
    }
    @Override
    public String toString() {
        return String.format("%s%s will be %s for %ds.%s",
                isOpen ? ANSI_GREEN : timeToChangeStatus > 1 ? ANSI_RED : ANSI_YELLOW,
                name, isOpen ? "open" : "closed",
                timeToChangeStatus,
                ANSI_RESET);
    }

    public String getName() {
        return name;
    }


    public static void roadStatus() {
            if (circularQueue.isEmpty()) {
                return;
            }
        remainingTime--;

            if (circularQueue.stream().noneMatch(Road::isOpen)) {
                circularQueue.stream().min(Comparator.comparingInt(Road::getTimeToChangeStatus))
                        .ifPresent(road -> road.setOpen(true));
            }

            if (remainingTime == 0) {
                circularQueue.peekCurrent().setOpen(false);
                circularQueue.getNext().setOpen(true);
            }
            for (int i = 0; i < circularQueue.size(); i++) {
                circularQueue.peekCurrent().setTimeToChangeStatus((remainingTime == 0 ? TrafficManager.interval : remainingTime) + (i > 1 ? TrafficManager.interval * (i - 1) : 0));
                circularQueue.getNext();
            }
        }
    }