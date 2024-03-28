package br.com.fiap.restaurantmanagement.domain.ports.inbound;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.GetReservationsRequestHeaders;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;

import java.util.List;

/**
 This interface represents the gateway to searching for restaurant reservations
 */
public interface FindReservationsUseCasePort {

  List<Reservation> get(GetReservationsRequestHeaders getReservationsRequestHeaders);

}