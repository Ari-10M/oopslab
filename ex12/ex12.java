import java.io.*;

class Ticket {
    int seatNo;
    String passengerName;
    String destination;
    boolean isBooked;

    Ticket(int seatNo) {
        this.seatNo = seatNo;
        this.isBooked = false;
    }
}

class BookingSystem {
    final static int TOTAL_SEATS = 5;
    Ticket[] seats = new Ticket[TOTAL_SEATS];
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    BookingSystem() {
        for (int i = 0; i < TOTAL_SEATS; i++) {
            seats[i] = new Ticket(i + 1);
        }
    }

    void bookTicket() throws IOException {
        System.out.print("\n\t Available seats: ");
        boolean any = false;
        for (Ticket t : seats) {
            if (!t.isBooked) {
                System.out.print(t.seatNo + " ");
                any = true;
            }
        }
        System.out.println();
        
        if (!any) {
            System.out.println("\t Sorry, all seats are booked.");
            return;
        }
        
        System.out.print("\t Enter seat number to book = ");
        int seatNo = Integer.parseInt(br.readLine());
        
        if (seatNo < 1 || seatNo > TOTAL_SEATS) {
            System.out.println("\t Invalid seat number");
            return;
        }
        
        Ticket t = seats[seatNo - 1];
        if (t.isBooked) {
            System.out.println("\t Seat already booked");
            return;
        }
        
        System.out.print("\t Enter passenger name = ");
        t.passengerName = br.readLine();
        System.out.print("\t Enter destination = ");
        t.destination = br.readLine();
        
        t.isBooked = true;
        System.out.println("\t Ticket booked successfully for " + t.passengerName + " (Seat " + seatNo + ")");
    }

    void cancelTicket() throws IOException {
        System.out.print("\t Enter seat number to cancel = ");
        int seatNo = Integer.parseInt(br.readLine());
        
        if (seatNo < 1 || seatNo > TOTAL_SEATS) {
            System.out.println("\t Invalid seat number");
            return;
        }
        
        Ticket t = seats[seatNo - 1];
        if (!t.isBooked) {
            System.out.println("\t Seat is not booked");
            return;
        }
        
        System.out.println("\t Cancelled ticket for " + t.passengerName);
        t.isBooked = false;
        t.passengerName = null;
        t.destination = null;
    }

    void displayBookings() {
        System.out.println("\n\t Seat\tStatus\t\tPassenger\tDestination");
        for (Ticket t : seats) {
            if (t.isBooked) {
                System.out.println("\t" + t.seatNo + "\tBooked\t\t" + t.passengerName + "\t\t" + t.destination);
            } else {
                System.out.println("\t" + t.seatNo + "\tAvailable\t-\t\t-");
            }
        }
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BookingSystem system = new BookingSystem();
        int choice;
        
        do {
            System.out.println("\n\t Ticket Booking System");
            System.out.println("\t 1. Book Ticket");
            System.out.println("\t 2. Cancel Ticket");
            System.out.println("\t 3. Display All Bookings");
            System.out.println("\t 4. Exit");
            
            System.out.print("\t Enter your choice = ");
            choice = Integer.parseInt(br.readLine());
            
            switch (choice) {
                case 1: 
                    system.bookTicket(); 
                    break;
                case 2: 
                    system.cancelTicket(); 
                    break;
                case 3: 
                    system.displayBookings(); 
                    break;
                case 4:
                    System.out.println("\t Thank you for using Ticket Booking System"); 
                    break;
                default: 
                    System.out.println("\t Invalid choice");
            }
        } while (choice != 4);
    }
}