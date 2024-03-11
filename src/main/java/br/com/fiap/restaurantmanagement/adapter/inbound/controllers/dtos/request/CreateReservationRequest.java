package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class CreateReservationRequest {

    public Reservation toDomain() {
        return new Reservation();
    }
}
