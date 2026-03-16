import java.util.*;

/**
 * Reservation class captures guest intent.
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

/**
 * RoomAllocationService manages the transition from Request to Confirmation.
 */
class RoomAllocationService {
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    public void addInventory(String type, int count) {
        inventory.put(type, count);
        allocatedRooms.put(type, new HashSet<>());
    }

    public void processBookings(Queue<Reservation> queue) {
        System.out.println("Room Allocation Processing");

        while (!queue.isEmpty()) {
            Reservation request = queue.poll();
            String type = request.getRoomType();
            int currentCount = inventory.getOrDefault(type, 0);

            if (currentCount > 0) {
                // Generate a unique Room ID (e.g., Single-1, Single-2)
                int roomNumber = allocatedRooms.get(type).size() + 1;
                String roomID = type + "-" + roomNumber;

                // Set uniqueness check (Defensive Programming)
                if (allocatedRooms.get(type).add(roomID)) {
                    // Decrement Inventory (Atomic-like operation)
                    inventory.put(type, currentCount - 1);

                    System.out.println("Booking confirmed for Guest: " + request.getGuestName() +
                            ", Room ID: " + roomID);
                }
            } else {
                System.out.println("Booking failed for Guest: " + request.getGuestName() +
                        " (No " + type + " rooms available)");
            }
        }
    }
}

/**
 * Main application class for Use Case 6.
 * @author User
 * @version 6.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        // 1. Setup Inventory and Allocation Service
        RoomAllocationService bookingService = new RoomAllocationService();
        bookingService.addInventory("Single", 5);
        bookingService.addInventory("Double", 3);
        bookingService.addInventory("Suite", 2);

        // 2. Setup Request Queue (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Subha", "Single")); // Requesting same type
        bookingQueue.add(new Reservation("Vanmathi", "Suite"));

        // 3. Perform Allocation
        bookingService.processBookings(bookingQueue);
    }
}