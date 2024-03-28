package br.com.fiap.restaurantmanagement.domain.ports.inbound;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;

/**
 * This interface represents the gateway to canceling a reservation at the restaurant
 */
public interface DeleteReservationUseCasePort {

  Reservation delete(Long id);

}