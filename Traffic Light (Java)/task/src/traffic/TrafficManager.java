package traffic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TrafficManager {

    private static volatile boolean systemState = false;
    protected static int numberOfRoads;
    protected static int interval;
    protected static CircularQueue roadQueue;
    public static QueueThread queueThread;

    static Scanner scanner = new Scanner(System.in);

    public static void start() {
        System.out.println("Welcome to the traffic management system!");

        System.out.print("Input the number of roads: ");
        while (true) {
            try {
                numberOfRoads = scanner.nextInt();
                if (numberOfRoads <= 0) {
                    System.out.print("Error! Incorrect Input. Number of roads must be a positive integer. Try again: ");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.print("Error! Incorrect Input. Number of roads must be a positive integer. Try again: ");
                scanner.next(); // discard the incorrect input
            }
        }

        roadQueue = new CircularQueue<>(numberOfRoads);
        queueThread = new QueueThread(roadQueue);

        queueThread.start();  // Start the thread

        System.out.print("Input the interval: ");
        while (true) {
            try {
                interval = scanner.nextInt();
                if (interval <= 0) {
                    System.out.print("Error! Incorrect Input. Interval must be a positive integer. Try again: ");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.print("Error! Incorrect Input. Interval must be a positive integer. Try again: ");
                scanner.next();
            }
        }
        scanner.nextLine();
        menu(scanner);
    }

    private static synchronized void addRoad() {
        System.out.print("Input road name: ");
        String roadName = scanner.nextLine();
        Road road = new Road(roadName, roadQueue);
        if (roadQueue.size() >= numberOfRoads) {
            System.out.println("Queue is full");
        } else {
            roadQueue.enqueue(road);
            System.out.println("Road added: " + road.getName());
        }

        new Scanner(System.in).nextLine();
    }


    private static synchronized void deleteRoad() {
        if (roadQueue.isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            Road road = (Road) roadQueue.removeFirst();
            System.out.println("Road deleted: " + road.getName());
        }
        new Scanner(System.in).nextLine();
    }

    public static void openSystem() {
        //    System.out.println("System opened");
    }

    public static void menuItems() {
        System.out.println("""
            Menu:
            1. Add road
            2. Delete road
            3. Open system
            0. Quit
            """);
    }

    public static void menu(Scanner scanner) {
        while (true) {
            if (!systemState) {
                menuItems();
                int choice;
                while (true) {
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                        if (choice < 0 || choice > 3) {
                            System.out.println("Incorrect option");
                            scanner.nextLine();
                            ClearConsoleOutput.clearConsoleOutput();
                            menuItems();
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect option");
                        scanner.nextLine();
                        ClearConsoleOutput.clearConsoleOutput();
                        menuItems();
                    }
                }
                switch (choice) {
                    case 1:
                        addRoad();
                        break;
                    case 2:
                        deleteRoad();
                        break;
                    case 3:
                        openSystem();
                        systemState = true;
                        queueThread.setState(QueueThread.State.SYSTEM);
                        break;
                    case 0:
                        System.out.println("Bye!");
                        queueThread.interrupt();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again");
                }
            } else {
                ClearConsoleOutput.clearConsoleOutput();
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    systemState = false;
                    queueThread.setState(QueueThread.State.MENU);
                }
            }
        }
    }
}
