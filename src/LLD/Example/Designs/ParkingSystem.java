package LLD.Example.Designs;

import java.time.LocalDateTime;
import java.util.*;

// Enum for Vehicle Types
enum VehicleType {
    TWO_WHEELER,
    FOUR_WHEELER,
    THREE_WHEELER  // for future extension
}

// Vehicle class
class Vehicle {
    private String vehicleNo;
    private VehicleType vehicleType;

    public Vehicle(String vehicleNo, VehicleType vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }
}

// ParkingSpot class
class ParkingSpot {
    private int spotNumber;
    private VehicleType vehicleType;
    private boolean isEmpty = true;

    public ParkingSpot(int spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void occupySpot() {
        isEmpty = false;
    }

    public void freeSpot() {
        isEmpty = true;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getSpotNumber() {
        return spotNumber;
    }
}

// Ticket class
class Ticket {
    private LocalDateTime entryTime;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;

    public Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.entryTime = LocalDateTime.now();
        this.vehicle = vehicle;
        this.parkingSpot = spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

// Abstract PaymentStrategy for flexibility
interface PaymentStrategy {
    double calculatePayment(LocalDateTime entryTime, LocalDateTime exitTime);
}

// Minute based payment
class MinutePayment implements PaymentStrategy {
    private static final double RATE_PER_MIN = 0.05;
    @Override
    public double calculatePayment(LocalDateTime entryTime, LocalDateTime exitTime) {
        long durationInSeconds = java.time.Duration.between(entryTime, exitTime).getSeconds();

        long durationInMinutes = durationInSeconds / 60;

        if (durationInSeconds % 60 != 0) {
            durationInMinutes++;  // round up partial minutes
        }

        return durationInMinutes * RATE_PER_MIN;
    }

}

// Hourly based payment
class HourlyPayment implements PaymentStrategy {
    private static final double RATE_PER_HOUR = 3.0;

    @Override
    public double calculatePayment(LocalDateTime entryTime, LocalDateTime exitTime) {
        long durationInMinutes = java.time.Duration.between(entryTime, exitTime).toMinutes();

        long durationInHours = durationInMinutes / 60;
        if (durationInMinutes % 60 != 0) {
            durationInHours++;  // round up partial hours
        }
        return durationInHours * RATE_PER_HOUR;
    }

}

// Entrance gate class: find parking spot and update
class EntranceGate {
    private String gateId;
    private Map<Integer, ParkingSpot> parkingSpots;

    public EntranceGate(String gateId, Map<Integer, ParkingSpot> parkingSpots) {
        this.gateId = gateId;
        this.parkingSpots = parkingSpots;
    }

    public ParkingSpot findParkingSpot(VehicleType vehicleType) {
        for (ParkingSpot spot : parkingSpots.values()) {
            if (spot.getVehicleType() == vehicleType && spot.isEmpty()) {
                return spot;
            }
        }
        return null; // no spot available
    }

    public void updateParkingSpot(ParkingSpot spot, boolean occupy) {
        if (occupy) {
            spot.occupySpot();
        } else {
            spot.freeSpot();
        }
    }
}

// Exit gate class: calculate and process payment and update spot
class ExitGate {
    private String gateId;
    private PaymentStrategy paymentStrategy;

    public ExitGate(String gateId, PaymentStrategy paymentStrategy) {
        this.gateId = gateId;
        this.paymentStrategy = paymentStrategy;
    }

    public double calculatePayment(Ticket ticket) {
        return paymentStrategy.calculatePayment(ticket.getEntryTime(), LocalDateTime.now());
    }

    public boolean processExit(Ticket ticket) {
        if (ticket == null) {
            System.out.println("Invalid ticket");
            return false;
        }
        double amount = calculatePayment(ticket);
        System.out.println("Payment due: $" + amount);
        // Payment logic here (simulate success)
        ticket.getParkingSpot().freeSpot();
        System.out.println("Vehicle with license " + ticket.getVehicle().getVehicleNo() + " exited. Spot " + ticket.getParkingSpot().getSpotNumber() + " is now free.");
        // Here you can remove ticket from active tickets list if maintained
        return true;
    }
}



public class ParkingSystem {
    public static void main(String[] args) throws InterruptedException {
        // Setup parking spots
        Map<Integer, ParkingSpot> spots = new HashMap<>();
        spots.put(1, new ParkingSpot(1, VehicleType.TWO_WHEELER));
        spots.put(2, new ParkingSpot(2, VehicleType.FOUR_WHEELER));
        spots.put(3, new ParkingSpot(3, VehicleType.FOUR_WHEELER));
        spots.put(4, new ParkingSpot(4, VehicleType.TWO_WHEELER));

        // Entrances and exits (2 each)
        EntranceGate entrance1 = new EntranceGate("Entrance-1", spots);
        EntranceGate entrance2 = new EntranceGate("Entrance-2", spots);

        ExitGate exit1 = new ExitGate("Exit-1", new MinutePayment());
        ExitGate exit2 = new ExitGate("Exit-2", new HourlyPayment());

        // Vehicle 1 entering
        Vehicle vehicle1 = new Vehicle("KA-01-1234", VehicleType.TWO_WHEELER);
        ParkingSpot assignedSpot1 = entrance1.findParkingSpot(vehicle1.getVehicleType());

        if (assignedSpot1 != null) {
            entrance1.updateParkingSpot(assignedSpot1, true);
            Ticket ticket1 = new Ticket(vehicle1, assignedSpot1);

            System.out.println("Vehicle 1 parked at spot " + assignedSpot1.getSpotNumber());

            // Simulate time elapsed
            Thread.sleep(8000);  // 8 seconds

            // Vehicle 1 exits
            boolean exitSuccess1 = exit1.processExit(ticket1);
            if (exitSuccess1) {
                System.out.println("Vehicle 1 has successfully exited.");
            } else {
                System.out.println("Vehicle 1 exit failed.");
            }
        } else {
            System.out.println("No parking spot available for vehicle 1 of type " + vehicle1.getVehicleType());
        }

        System.out.println("-----");

        // Vehicle 2 entering
        Vehicle vehicle2 = new Vehicle("MH-02-5678", VehicleType.FOUR_WHEELER);
        ParkingSpot assignedSpot2 = entrance2.findParkingSpot(vehicle2.getVehicleType());

        if (assignedSpot2 != null) {
            entrance2.updateParkingSpot(assignedSpot2, true);
            Ticket ticket2 = new Ticket(vehicle2, assignedSpot2);

            System.out.println("Vehicle 2 parked at spot " + assignedSpot2.getSpotNumber());

            // Simulate time elapsed
            Thread.sleep(2000);  // 2 seconds

            // Vehicle 2 exits
            boolean exitSuccess2 = exit2.processExit(ticket2);
            if (exitSuccess2) {
                System.out.println("Vehicle 2 has successfully exited.");
            } else {
                System.out.println("Vehicle 2 exit failed.");
            }
        } else {
            System.out.println("No parking spot available for vehicle 2 of type " + vehicle2.getVehicleType());
        }
    }
}
