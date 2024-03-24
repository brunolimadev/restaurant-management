package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the create reservation response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateReservationResponse {
    private Reservation reservation;
    private String message;
}