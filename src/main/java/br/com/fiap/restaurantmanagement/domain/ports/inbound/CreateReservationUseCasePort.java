package br.com.fiap.restaurantmanagement.domain.ports.inbound;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;

/**
 * This interface represents the port for creating a reservation
 */
public interface CreateReservationUseCasePort {
    Reservation execute(Reservation restaurant);
}