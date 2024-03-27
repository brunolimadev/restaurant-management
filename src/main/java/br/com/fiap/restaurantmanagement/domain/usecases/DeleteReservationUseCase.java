package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.exceptions.TransactionException;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.DeleteReservationUseCasePort;

public class DeleteReservationUseCase implements DeleteReservationUseCasePort {

  private final ReservationRepository reservationRepository;

  public DeleteReservationUseCase(ReservationRepository reservationRepository) {

    this.reservationRepository = reservationRepository;

  }

  @Override
  public Reservation delete(Long id) {

    try {

      var deletedReservation = reservationRepository.findById(id).orElseThrow();

      reservationRepository.deleteById(id);

      return Reservation.toReservation(deletedReservation);

    } catch (Exception exception) {

      throw  new TransactionException("An error occurred while processing the reservation removal");

    }

  }

}