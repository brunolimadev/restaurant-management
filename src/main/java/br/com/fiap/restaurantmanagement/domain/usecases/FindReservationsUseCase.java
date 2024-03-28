package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.GetReservationsRequestHeaders;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.ReservationModel;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.exceptions.TransactionException;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.FindReservationsUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.ReservationAdapterPort;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the use case to search for a restaurant
 */
public class FindReservationsUseCase implements FindReservationsUseCasePort {

  private final ReservationAdapterPort reservationAdapterPort;

  public FindReservationsUseCase(ReservationAdapterPort reservationAdapterPort) {

    this.reservationAdapterPort = reservationAdapterPort;

  }

  @Override
  public List<Reservation> get(GetReservationsRequestHeaders getReservationsRequestHeaders) {

    try {

      var restaurantReservation = reservationAdapterPort.findReservationsByRestaurant(
                      getReservationsRequestHeaders.getRestaurantId()
      );

      return toListReservation(restaurantReservation);

    } catch (Exception exception) {

      throw  new TransactionException("An error occurred while processing reservation list");

    }

  }

  private List<Reservation> toListReservation(List<ReservationModel> reservations) {

    var reservationsToReturn = new ArrayList<Reservation>();

    reservations.forEach(reservationModel ->
            reservationsToReturn.add(
                    Reservation.toReservation(reservationModel)
            )
    );

    return reservationsToReturn;

  }

}