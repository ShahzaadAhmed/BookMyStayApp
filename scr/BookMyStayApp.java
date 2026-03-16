package scr;
import java.util.HashMap;

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 10);
        inventory.put("Double", 7);
        inventory.put("Suite", 3);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void displayInventory() {
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " Rooms Available: " + inventory.get(roomType));
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Book My Stay App - Version 3.1");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("Available Single Rooms: " + inventory.getAvailability("Single"));

        inventory.updateAvailability("Single", 8);

        System.out.println("Updated Inventory:");

        inventory.displayInventory();
    }
}