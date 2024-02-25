package br.com.fiap.restaurantmanagement.domain.entities;

public class Table {

    private String description;
    private int capacity;


    public Table(String description, int capacity) {
        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("Description cannot be null or empty");

        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity cannot be less than or equal to zero");

        this.description = description;
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }


}
