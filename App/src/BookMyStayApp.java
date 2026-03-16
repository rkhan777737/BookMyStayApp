import java.util.LinkedList;
import java.util.Queue;

/**
 * The Reservation class captures the guest's booking intent.
 * It stores the guest name and the specific room type they desire.
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
 * Main application class for Use Case 5.
 * Demonstrates decoupling request intake from actual allocation.
 * @author User
 * @version 5.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        // 1. Initialize the Booking Request Queue
        // We use LinkedList as it implements the Queue interface in Java.
        Queue<Reservation> bookingQueue = new LinkedList<>();

        System.out.println("Booking Request Queue\n");

        // 2. Simulating incoming requests (Request Intake)
        // Requests arrive in a specific order: Abhi -> Subha -> Vanmathi
        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Subha", "Double"));
        bookingQueue.add(new Reservation("Vanmathi", "Suite"));

        // 3. Processing the Queue (FIFO Principle)
        // poll() retrieves and removes the head of the queue.
        while (!bookingQueue.isEmpty()) {
            Reservation currentRequest = bookingQueue.poll();
            System.out.println("Processing booking for Guest: " + currentRequest.getGuestName() +
                    ", Room Type: " + currentRequest.getRoomType());
        }
    }
}