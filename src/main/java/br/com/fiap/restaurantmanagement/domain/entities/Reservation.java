package br.com.fiap.restaurantmanagement.domain.entities;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a reservation
 */
@Getter
public class Reservation {
    private ReservationRestaurant restaurant;
    private Client client;
    private LocalDate date;
    private LocalTime time;

    public Reservation(ReservationRestaurant restaurant, Client client, LocalDate date, LocalTime time) {
        validateMandatoryValues(restaurant, client, date, time);

        this.restaurant = restaurant;
        this.client = client;
        this.date = LocalDate.parse(date.format((DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        this.time = LocalTime.parse(time.format(DateTimeFormatter.ofPattern("HH:mm")));

    }

    private void validateMandatoryValues(ReservationRestaurant restaurant, Client client, LocalDate date, LocalTime time) {

        if (restaurant == null || client == null || date == null || time == null) {

            throw new IllegalArgumentException("todos os dados de reserva são obrigatórios");

        }

    }

}