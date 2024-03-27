package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;


import br.com.fiap.restaurantmanagement.domain.entities.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class GetReservationsRequestHeaders {

  private Long restaurantId;

  public GetReservationsRequestHeaders(Long restaurantId) {

    validateMandatoryValues(restaurantId);

    this.restaurantId = restaurantId;

  }

  public Reservation toDomain() {

    return new Reservation(
            new ReservationRestaurant(restaurantId)
    );

  }

  private void validateMandatoryValues(Long restaurantId) {

    if (restaurantId == null) {

      throw new IllegalArgumentException("Fields cannot be null");

    }

  }

}