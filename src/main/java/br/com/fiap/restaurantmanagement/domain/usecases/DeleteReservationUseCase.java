package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.exceptions.TransactionException;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.DeleteReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.ReservationAdapterPort;

public class DeleteReservationUseCase implements DeleteReservationUseCasePort {

  private final ReservationAdapterPort reservationAdapterPort;

  public DeleteReservationUseCase(ReservationAdapterPort reservationAdapterPort) {

    this.reservationAdapterPort = reservationAdapterPort;

  }

  @Override
  public Reservation delete(Long id) {

    try {

      var deletedReservation = reservationAdapterPort.findById(id);

      reservationAdapterPort.deleteById(id);

      return Reservation.toReservation(deletedReservation);

    } catch (Exception exception) {

      throw  new TransactionException("An error occurred while processing the reservation removal");

    }

  }

}