package br.com.fiap.restaurantmanagement.domain.entities;

import br.com.fiap.restaurantmanagement.domain.enumerators.DaysOfWeek;

import java.time.LocalDateTime;

public class OperatingPeriod {
    private DaysOfWeek dayOfWeek;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;

    public OperatingPeriod(DaysOfWeek dayOfWeek, LocalDateTime openingTime, LocalDateTime closingTime) {
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public DaysOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }
}
