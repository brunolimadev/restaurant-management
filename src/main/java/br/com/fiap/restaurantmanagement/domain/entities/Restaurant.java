package br.com.fiap.restaurantmanagement.domain.entities;

import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a restaurant
 */
public class Restaurant {

    private Long id;
    private String name;
    private List<Address> address;
    private TypesOfFood typeOfFood;
    private List<OpeningHours> openingHours;
    private List<Table> tables;
    private int likes;
    private List<Comment> comments;

    public Restaurant(String name, List<Address> address, TypesOfFood typeOfFood, List<OpeningHours> openingHours, List<Table> tables) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");

        if (typeOfFood == null)
            throw new IllegalArgumentException("TypesOfFood cannot be null");

        this.name = name;
        this.address = address;
        this.typeOfFood = typeOfFood;
        this.openingHours = openingHours;
        this.tables = tables;
        this.likes = 0;
        this.comments = Arrays.asList();
    }

    public Restaurant(Long id, String name, List<Address> address, TypesOfFood typeOfFood, List<OpeningHours> openingHours, List<Table> tables, int likes, List<Comment> comments) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");

        if (typeOfFood == null)
            throw new IllegalArgumentException("TypesOfFood cannot be null");


        this.id = id;
        this.name = name;
        this.address = address;
        this.typeOfFood = typeOfFood;
        this.openingHours = openingHours;
        this.tables = tables;
        this.likes = likes;
        this.comments = comments;
    }

    public Restaurant(String name, List<Address> address, TypesOfFood typeOfFood, List<OpeningHours> openingHours, List<Table> tables, int likes, List<Comment> comments) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");

        if (typeOfFood == null)
            throw new IllegalArgumentException("TypesOfFood cannot be null");

        this.name = name;
        this.address = address;
        this.typeOfFood = typeOfFood;
        this.openingHours = openingHours;
        this.tables = tables;
        this.likes = likes;
        this.comments = comments;
    }

    public Restaurant(Long id, String name, TypesOfFood domain) {
        this.id = id;
        this.name = name;
        this.typeOfFood = domain;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Address> getAddress() {
        return Collections.unmodifiableList(address);
    }

    public TypesOfFood getTypeOfFood() {
        return typeOfFood;
    }

    public List<OpeningHours> getOpeningHours() {
        return openingHours;
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
