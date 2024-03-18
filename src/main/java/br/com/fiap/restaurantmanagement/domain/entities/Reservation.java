package br.com.fiap.restaurantmanagement.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class represents a reservation
 */
public class Reservation {
    private Long id;
    private Long restaurantId;
    private Long userId;
    private Long tableId;
    private Integer dayOfWeek;
    private LocalDateTime time;

    public Reservation() {

    }

    public Reservation(Long id, Long restaurantId, Long userId, Long tableId, Integer dayOfWeek, LocalDateTime time) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.tableId = tableId;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) && Objects.equals(restaurantId, that.restaurantId) && Objects.equals(userId, that.userId) && Objects.equals(tableId, that.tableId) && Objects.equals(dayOfWeek, that.dayOfWeek) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantId, userId, tableId, dayOfWeek, time);
    }
}