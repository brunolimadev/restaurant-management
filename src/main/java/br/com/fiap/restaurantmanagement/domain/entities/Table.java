package br.com.fiap.restaurantmanagement.domain.entities;

public class Table {

    private int number;
    private int capacity;
    private boolean isAvailable;

    public Table(int number, int capacity, boolean isAvailable) {
        this.number = number;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
