import java.util.HashMap;
import java.util.Map;

/**
 * Abstract Room class remains the same as Use Case 2.
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

    public String getType() { return type; }

    public void displayDetails(int availability) {
        System.out.println(type + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available Rooms: " + availability + "\n");
    }
}

class SingleRoom extends Room { public SingleRoom() { super("Single Room", 1, 250, 1500.0); } }
class DoubleRoom extends Room { public DoubleRoom() { super("Double Room", 2, 400, 2500.0); } }
class SuiteRoom extends Room { public SuiteRoom() { super("Suite Room", 3, 750, 5000.0); } }

/**
 * Centralized Room Inventory Management.
 * Encapsulates the HashMap and provides controlled access to data.
 */
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    // Register a room type with its initial count
    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    // Retrieve availability for a specific type
    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

/**
 * Main application class for Use Case 3.
 * @author User
 * @version 3.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Hotel Room Inventory Status\n");

        // 1. Initialize Inventory System
        RoomInventory hotelInventory = new RoomInventory();
        hotelInventory.addRoomType("Single Room", 5);
        hotelInventory.addRoomType("Double Room", 3);
        hotelInventory.addRoomType("Suite Room", 2);

        // 2. Initialize Room objects for display
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // 3. Display status using Centralized Data
        single.displayDetails(hotelInventory.getAvailability(single.getType()));
        doubleRoom.displayDetails(hotelInventory.getAvailability(doubleRoom.getType()));
        suite.displayDetails(hotelInventory.getAvailability(suite.getType()));
    }
}
