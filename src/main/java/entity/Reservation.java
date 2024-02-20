package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private int id;
    private String customerName;
    private LocalDateTime dateTime;
    private List<restaurantTable> tables;
    private int numberPersonnes;

    public Reservation(int id, String customerName, LocalDateTime dateTime, int numberPersonnes) {
        this.id = id;
        this.customerName = customerName;
        this.dateTime = dateTime;
        this.tables = new ArrayList<>();
        this.numberPersonnes = numberPersonnes;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<restaurantTable> getTables() {
        return tables;
    }

    public void setTables(List<restaurantTable> tables) {
        this.tables = tables;
    }

    public void addTable(restaurantTable table) {
        this.tables.add(table);
    }

    public int getNumberPersonnes() {
        return numberPersonnes;
    }

    public void setNumberPersonnes(int numberPersonnes) {
        this.numberPersonnes = numberPersonnes;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", dateTime=" + dateTime +
                ", numberPersonnes=" + numberPersonnes +
                ", tables=" + tables +
                '}';
    }
}
