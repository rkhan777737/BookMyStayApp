/**

 * The Room class serves as an abstract blueprint for all room types.
 * It encapsulates common properties like beds, size, and price.
 */
abstract class Room {
    private String type;
    private int beds;
    private int size;
    private double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayInfo(int availability) {
        System.out.println(type + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + availability + "\n");
    }
}

// Concrete Subclasses using Inheritance
class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1, 250, 1500.0); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 400, 2500.0); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 750, 5000.0); }
}

/**
 * Main application class for Use Case 2.
 * @author User
 * @version 2.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Hotel Room Initialization\n");

        // 1. Initialize Room Objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // 2. Static Availability Representation
        // Note: These are simple variables, highlighting the lack of a collection/DS.
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // 3. Display Room Details
        single.displayInfo(singleAvailable);
        doubleRoom.displayInfo(doubleAvailable);
        suite.displayInfo(suiteAvailable);
    }
}
 * The UseCase1HotelBookingApp class serves as the entry point for the
 * Hotel Booking Management System.
 * * This use case establishes the application startup behavior,
 * demonstrating the JVM entry point and console output.
 * * @author User
 * @version 1.0
 */
public class BookMyStayApp {

    /**
     * The main method is the entry point of the standalone Java application.
     * The JVM invokes this method to start execution.
     * * @param args Command line arguments (not used in this use case)
     */
    public static void main(String[] args) {
        // Display the welcome message and application name
        System.out.println("Welcome to the Hotel Booking Management System");

        // Display system status/initialization message
        System.out.println("System initialized successfully.");
    }
}

