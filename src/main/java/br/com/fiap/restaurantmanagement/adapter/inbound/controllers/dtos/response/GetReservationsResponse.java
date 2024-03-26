package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetReservationsResponse {

  List<Reservation> reservations;

}