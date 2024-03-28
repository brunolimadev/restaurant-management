package br.com.fiap.restaurantmanagement.domain.entities;

/**
 * This class represents a reservation at the restaurant
 */
public class ReservationRestaurant {

    private Long restaurantId;
    private Table table;

    public ReservationRestaurant(Long restaurantId, Table table) {

        this.restaurantId = restaurantId;
        this.table = table;

    }
    public ReservationRestaurant(Long restaurantId) {

        this.restaurantId = restaurantId;

    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

}