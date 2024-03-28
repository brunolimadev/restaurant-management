package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.*;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Restaurant id", example = "1", required = true)
    private Long restaurantId;

    @Schema(description = "Restaurant name", example = "Mr. Lam", required = true)
    private String name;

    @Schema(description = "email", example = "your_email@gmail.com", required = true)
    private String email;

    @Schema(description = "Phone Number", example = "119999999", required = true)
    private String phoneNumber;

    @Schema(description = "Booking date", example = "2024-03-24", required = true)
    private LocalDate date;

    @Schema(description = "Reservation time", example = "19:11", required = true)
    private LocalTime time;

    @Schema(description = "Number of seats", example = "2")
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

            throw new IllegalArgumentException("fields cannot be null\n");

        }

    }

    private Table capacityTableReservationRequestToTable() {

       return new Table(places);

    }

}