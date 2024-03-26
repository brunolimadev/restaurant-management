package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.GetReservationsRequestHeaders;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.domain.entities.Client;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.entities.ReservationRestaurant;
import br.com.fiap.restaurantmanagement.domain.entities.Table;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.FindReservationsUseCasePort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FindReservationsUseCase implements FindReservationsUseCasePort {

  private final ReservationRepository reservationRepository;

  public FindReservationsUseCase(ReservationRepository reservationRepository) {

    this.reservationRepository = reservationRepository;

  }

  @Override
  public List<Reservation> get(GetReservationsRequestHeaders getReservationsRequestHeaders) {

    var restaurantReservation = reservationRepository
            .findReservationsByRestaurant(getReservationsRequestHeaders.getRestaurantId());

    var reservationsToReturn = new ArrayList<Reservation>();

    restaurantReservation.forEach(reservationModel -> reservationsToReturn
            .add(
                    new Reservation(
                    new ReservationRestaurant(
                            reservationModel.getRestaurant().getId(),
                            new Table(
                                    reservationModel.getTable().getId(),
                                    reservationModel.getTable().getDescription(),
                                    reservationModel.getNumberOfpeople())),
                            new Client(
                                    reservationModel.getUser().getId(),
                                    reservationModel.getUser().getName(),
                                    reservationModel.getUser().getEmail(),
                                    reservationModel.getUser().getPhoneNumber()),
                            LocalDate.parse(reservationModel.getDate()),
                            LocalTime.parse(reservationModel.getTime())
            )
    ));

    return reservationsToReturn;

  }

}