package entity;

public class restaurantTable {
    private int id;
    private int capacity;
    private boolean available;
    private Reservation reservation; // The reservation associated with this table

    public restaurantTable(int id, int capacity, boolean available) {
        this.id = id;
        this.capacity = capacity;
        this.available = available;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", available=" + available +
                ", reservation=" + reservation +
                '}';
    }
}
