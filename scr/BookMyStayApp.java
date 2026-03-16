package scr;
import java.util.*;

class Reservation {

    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingRequestQueue {

    Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation r) {
        queue.add(r);
    }

    Reservation getNextRequest() {
        return queue.poll();
    }

    boolean hasRequests() {
        return !queue.isEmpty();
    }
}

class InventoryService {

    HashMap<String, Integer> inventory;

    InventoryService() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

class BookingService {

    InventoryService inventory;
    HashMap<String, Set<String>> allocatedRooms;
    int idCounter = 1;

    BookingService(InventoryService inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
    }

    void processReservation(Reservation r) {

        if (!inventory.isAvailable(r.roomType)) {
            System.out.println("No rooms available for " + r.roomType);
            return;
        }

        String roomId = r.roomType.replace(" ", "").substring(0,3).toUpperCase() + idCounter++;

        allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());

        Set<String> assigned = allocatedRooms.get(r.roomType);

        if (assigned.contains(roomId)) {
            System.out.println("Duplicate room ID detected");
            return;
        }

        assigned.add(roomId);
        inventory.decrement(r.roomType);

        System.out.println("Reservation Confirmed");
        System.out.println("Guest: " + r.guestName);
        System.out.println("Room Type: " + r.roomType);
        System.out.println("Room ID: " + roomId);
        System.out.println();
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Book My Stay App - Version 6.0");

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        requestQueue.addRequest(new Reservation("Aisha", "Single Room"));
        requestQueue.addRequest(new Reservation("Rahul", "Double Room"));
        requestQueue.addRequest(new Reservation("Fatima", "Suite Room"));

        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService(inventory);

        while (requestQueue.hasRequests()) {
            Reservation r = requestQueue.getNextRequest();
            bookingService.processReservation(r);
        }
    }
}