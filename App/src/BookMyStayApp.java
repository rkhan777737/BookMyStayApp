import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Room class remains the domain model holder.
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
        System.out.println("Available: " + availability + "\n");
    }
}

class SingleRoom extends Room { public SingleRoom() { super("Single Room", 1, 250, 1500.0); } }
class DoubleRoom extends Room { public DoubleRoom() { super("Double Room", 2, 400, 2500.0); } }
class SuiteRoom extends Room { public SuiteRoom() { super("Suite Room", 3, 750, 5000.0); } }

/**
 * Inventory Management (State Holder).
 */
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

/**
 * Search Service (Read-Only Logic).
 * Handles the filtering and display of available rooms.
 */
class RoomSearchService {
    public void performSearch(List<Room> rooms, RoomInventory inventory) {
        System.out.println("Room Search\n");
        boolean found = false;

        for (Room room : rooms) {
            int availableCount = inventory.getAvailability(room.getType());

            // Validation Logic: Only show rooms with availability > 0
            if (availableCount > 0) {
                room.displayDetails(availableCount);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms currently available.");
        }
    }
}

/**
 * Main application class for Use Case 4.
 * @author User
 * @version 4.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        // 1. Setup Inventory
        RoomInventory hotelInventory = new RoomInventory();
        hotelInventory.addRoomType("Single Room", 5);
        hotelInventory.addRoomType("Double Room", 3);
        hotelInventory.addRoomType("Suite Room", 2);

        // 2. Setup Room List (Domain Objects)
        List<Room> roomCatalog = new ArrayList<>();
        roomCatalog.add(new SingleRoom());
        roomCatalog.add(new DoubleRoom());
        roomCatalog.add(new SuiteRoom());

        // 3. Use Search Service to view rooms (Read-Only)
        RoomSearchService searchService = new RoomSearchService();
        searchService.performSearch(roomCatalog, hotelInventory);
    }
}