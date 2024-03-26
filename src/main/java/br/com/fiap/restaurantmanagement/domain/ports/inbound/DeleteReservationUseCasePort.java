package br.com.fiap.restaurantmanagement.domain.ports.inbound;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;

public interface DeleteReservationUseCasePort {

  Reservation delete(Long id);

}