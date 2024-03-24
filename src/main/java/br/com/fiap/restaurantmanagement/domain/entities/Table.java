package br.com.fiap.restaurantmanagement.domain.entities;

import lombok.ToString;

/**
 * This class represents a table
 */
@ToString
public class Table {

    private Long id;
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

    public Table(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity cannot be less than or equal to zero");
        }
        if (capacity > 4) {
            throw new IllegalArgumentException("a capacidade máxima de lugares por mesa é de 4");
        }

        this.capacity = capacity;
    }

    public Table(Long id, int capacity) {
        if (capacity <= 0 || id == null) {
            throw new IllegalArgumentException("a capacidade e o id da mesa devem ser informados");
        }
        if (capacity > 4) {
            throw new IllegalArgumentException("a capacidade máxima de lugares por mesa é de 4");
        }

        this.capacity = capacity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return  id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }


}
