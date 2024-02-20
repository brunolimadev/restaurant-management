package br.com.fiap.restaurantmanagement.domain.entities;

import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Restaurant {
    private String name;
    private Address address;
    private TypesOfFood typeOfFood;
    private OperatingPeriod timesAndPeriodsAvailable;
    private List<Table> tables;
    private int likes;
    private List<Comment> comments;

    public Restaurant(String name, Address address, TypesOfFood typeOfFood, OperatingPeriod timesAndPeriodsAvailable, List<Table> tables, int likes, List<Comment> comments) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");

        if (typeOfFood == null)
            throw new IllegalArgumentException("TypesOfFood cannot be null");

        this.name = name;
        this.address = address;
        this.typeOfFood = typeOfFood;
        this.timesAndPeriodsAvailable = timesAndPeriodsAvailable;
        this.tables = tables;
        this.likes = likes;
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant other = (Restaurant) o;
        return Objects.equals(name, other.name) && typeOfFood == other.typeOfFood;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, typeOfFood);
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public TypesOfFood getTypeOfFood() {
        return typeOfFood;
    }

    public OperatingPeriod getTimesAndPeriodsAvailable() {
        return timesAndPeriodsAvailable;
    }

    public List<Table> getTables() {
        return Collections.unmodifiableList(tables);
    }

    public int getLikes() {
        return likes;
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }
}
