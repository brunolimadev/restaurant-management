package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.domain.entities.Client;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.entities.ReservationRestaurant;
import br.com.fiap.restaurantmanagement.domain.entities.Table;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.DeleteReservationUseCasePort;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeleteReservationUseCase implements DeleteReservationUseCasePort {

  private final ReservationRepository reservationRepository;

  public DeleteReservationUseCase(ReservationRepository reservationRepository) {

    this.reservationRepository = reservationRepository;

  }

  @Override
  public Reservation delete(Long id) {

    var deletedReservation = reservationRepository.findById(id).orElseThrow();

    var reservationToReturn =  new Reservation(
            new ReservationRestaurant(
                    deletedReservation.getRestaurant().getId(),
                    new Table(
                            deletedReservation.getTable().getId(),
                            deletedReservation.getTable().getDescription(),
                            deletedReservation.getNumberOfpeople())),
            new Client(
                    deletedReservation.getUser().getId(),
                    deletedReservation.getUser().getName(),
                    deletedReservation.getUser().getEmail(),
                    deletedReservation.getUser().getPhoneNumber()),
            LocalDate.parse(deletedReservation.getDate()),
            LocalTime.parse(deletedReservation.getTime()));

    reservationRepository.deleteById(id);

    return reservationToReturn;

  }

}