package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;


import br.com.fiap.restaurantmanagement.domain.entities.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
public class GetReservationsRequestHeaders {

  private Long restaurantId;
  private LocalDate date;
  private LocalTime time;

  public GetReservationsRequestHeaders(
          Long restaurantId,
          LocalDate date,
          LocalTime time) {

    validateMandatoryValues(restaurantId, date, time);

    this.restaurantId = restaurantId;
    this.date = date;
    this.time = time;

  }

  public Reservation toDomain() {

    return new Reservation(
            new ReservationRestaurant(restaurantId),
            date,
            time
    );

  }

  private void validateMandatoryValues(
          Long restaurantId,
          LocalDate date,
          LocalTime time) {

    if (
            restaurantId == null ||
            date == null ||
            time == null
    ) {

      throw new IllegalArgumentException("os campos não podem ser nulos");

    }

  }

}