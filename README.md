This project is from https://hyperskill.org/projects/288?track=12

This Java program focuses on working with multi-threading, handling exceptions, inherit classes, and implement and apply the circular queue data structure.

---

The program implements a simple traffic management system that allows users to dynamically manage and monitor the status of multiple roads. The program utilizes multithreading, user input, and a circular queue data structure to simulate the behavior of roads opening and closing based on a predefined time interval. Here is an overview of the program's key features:

Main Functionalities:

Road Management: Users can add and delete roads dynamically. The CircularQueue data structure is employed to manage the order of roads, ensuring a cyclical rotation of road statuses.

System Monitoring: The program runs a background thread (QueueThread) that continuously updates and displays the system state. It tracks the elapsed time since the system startup, the number of roads, and the interval between road status changes.

Road Status Control: The Road class encapsulates individual roads, each having a status (open/closed) and a time to change status. The roadStatus method is responsible for updating road statuses based on the elapsed time, ensuring an organized flow of traffic.

Console Interface: The program provides a console-based user interface. Users can open the system, add or delete roads, and interact with the program through a menu system.

Libraries Used:

Java Standard Libraries: The program relies on core Java libraries for fundamental functionalities, including thread management, input handling (Scanner), and basic data structures.

How It Works:
Upon startup, users input the number of roads and the interval between status changes. The program then initializes a CircularQueue to manage the roads. A background thread continuously updates the system state, and roads open and close based on the predefined interval. The user can interact with the system through a console menu, adding or deleting roads dynamically.
