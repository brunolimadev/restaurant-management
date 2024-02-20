package br.com.fiap.restaurantmanagement.domain.entities;

import br.com.fiap.restaurantmanagement.domain.enumerators.DaysOfWeek;

import java.time.LocalDateTime;

public class OperatingPeriod {
    private DaysOfWeek dayOfWeek;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;
}
