package scr;
import java.util.LinkedList;
import java.util.Queue;

class Reservation {

    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

class BookingRequestQueue {

    private Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    void displayRequests() {
        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Book My Stay App - Version 5.0");

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        requestQueue.addRequest(new Reservation("Aisha", "Single Room"));
        requestQueue.addRequest(new Reservation("Rahul", "Double Room"));
        requestQueue.addRequest(new Reservation("Fatima", "Suite Room"));

        System.out.println("Booking Requests in Queue:");

        requestQueue.displayRequests();
    }
}