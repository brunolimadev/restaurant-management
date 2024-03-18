package br.com.fiap.restaurantmanagement.domain.entities;

import br.com.fiap.restaurantmanagement.domain.enumerators.DaysOfWeek;

import java.time.LocalDateTime;

/**
 * This class represents the opening hours of a restaurant
 */
public class OpeningHours {
    private DaysOfWeek dayOfWeek;
    private String openingTime;
    private String closingTime;

    public OpeningHours(DaysOfWeek dayOfWeek, String openingTime, String closingTime) {
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public DaysOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }
}
