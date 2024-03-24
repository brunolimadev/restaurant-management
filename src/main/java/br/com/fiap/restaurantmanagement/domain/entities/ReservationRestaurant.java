package br.com.fiap.restaurantmanagement.domain.entities;

public class ReservationRestaurant {

    private Long restaurantId;
    private OpeningHours openingHours;
    private Table table;

    public ReservationRestaurant(Long restaurantId, OpeningHours openingHours, Table table) {
        this.restaurantId = restaurantId;
        this.openingHours = openingHours;
        this.table = table;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}