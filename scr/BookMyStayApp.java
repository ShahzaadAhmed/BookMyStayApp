package scr;
import java.util.HashMap;

abstract class Room {
    String type;
    double price;

    Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    void displayDetails(int availability) {
        System.out.println("Room Type: " + type);
        System.out.println("Price: " + price);
        System.out.println("Available: " + availability);
        System.out.println();
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 3000);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 5000);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 9000);
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

class RoomSearchService {

    private RoomInventory inventory;

    RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void searchRooms(Room[] rooms) {
        for (Room room : rooms) {
            int availability = inventory.getAvailability(room.type);
            if (availability > 0) {
                room.displayDetails(availability);
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Book My Stay App - Version 4.0");

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        RoomSearchService searchService = new RoomSearchService(inventory);

        searchService.searchRooms(rooms);
    }
}