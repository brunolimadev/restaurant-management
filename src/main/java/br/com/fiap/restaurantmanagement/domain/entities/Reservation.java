package br.com.fiap.restaurantmanagement.domain.entities;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.ReservationModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a reservation
 */
@Getter
public class Reservation {
    private Long id;
    private ReservationRestaurant restaurant;
    private Client client;
    private LocalDate date;
    private LocalTime time;

    public Reservation(
            Long id,
            ReservationRestaurant restaurant,
            Client client,
            LocalDate date,
            LocalTime time) {

        validateMandatoryValues(restaurant, client, date, time);

        this.id = id;
        this.restaurant = restaurant;
        this.client = client;
        this.date = LocalDate.parse(date.format((DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        this.time = LocalTime.parse(time.format(DateTimeFormatter.ofPattern("HH:mm")));

    }

    public Reservation(
            ReservationRestaurant restaurant,
            Client client,
            LocalDate date,
            LocalTime time) {

        validateMandatoryValues(restaurant, client, date, time);

        this.restaurant = restaurant;
        this.client = client;
        this.date = LocalDate.parse(date.format((DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        this.time = LocalTime.parse(time.format(DateTimeFormatter.ofPattern("HH:mm")));

    }

    public Reservation(ReservationRestaurant restaurant) {

        this.restaurant = restaurant;

    }

    public static Reservation toReservation(ReservationModel reservationModel) {

        return new Reservation(
                reservationModel.getId(),
                toReservationRestaurant(reservationModel),
                toClient(reservationModel.getUser()),
                LocalDate.parse(reservationModel.getDate()),
                LocalTime.parse(reservationModel.getTime()));

    }

    private static ReservationRestaurant  toReservationRestaurant(
            ReservationModel reservationModel) {

        return new ReservationRestaurant(
                reservationModel.getRestaurant().getId(),
                toTable(reservationModel.getTable(), reservationModel));

    }

    private static Table toTable(TableModel tableModel, ReservationModel reservationModel) {

        return new Table(
                tableModel.getId(),
                tableModel.getDescription(),
                reservationModel.getNumberOfpeople());

    }

    private static Client toClient(UserModel userModel) {

        return new Client(
                userModel.getId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getPhoneNumber());

    }

    private void validateMandatoryValues(ReservationRestaurant restaurant, Client client, LocalDate date, LocalTime time) {

        if (restaurant == null || client == null || date == null || time == null) {

            throw new IllegalArgumentException("All booking details are mandatory");

        }

    }

}