package traffic;

public class Main {

    public static void main(String[] args) {
        CircularQueue<Road> roadQueue = new CircularQueue<>(TrafficManager.numberOfRoads);
        TrafficManager.queueThread = new QueueThread(roadQueue);
        TrafficManager.start();
    }
}