package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class represents the create reservation request
 */
@Data
@Builder
@NoArgsConstructor
public class CreateReservationRequest {

    private Long restaurantId;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate date;
    private LocalTime time;
    private int places;

    public CreateReservationRequest(Long restaurantId,
                                    String name,
                                    String email, String phoneNumber,
                                    LocalDate date,
                                    LocalTime time,
                                    int places) {

        validateMandatoryValues(restaurantId, name, email, phoneNumber, date, time);

        this.restaurantId = restaurantId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;
        this.places = places;

    }

    public Reservation toDomain() {

        return new Reservation(
                new ReservationRestaurant(
                        restaurantId,
                        capacityTableReservationRequestToTable()),
                new Client(name, email, phoneNumber),
                date,
                time
        );

    }

    private void validateMandatoryValues(Long restaurantId,
                                         String name,
                                         String email,
                                         String phoneNumber,
                                         LocalDate date,
                                         LocalTime time) {

        if (restaurantId == null ||
                name == null ||
                email == null ||
                phoneNumber == null ||
                date == null ||
                time == null
        ) {

            throw new IllegalArgumentException("os campos não podem ser nulos");

        }

    }

    private Table capacityTableReservationRequestToTable() {

       return new Table(places);

    }

}