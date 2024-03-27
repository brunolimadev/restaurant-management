package br.com.fiap.restaurantmanagement.domain.usecases;


import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.ReservationModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.exceptions.OccupiedTablesToReservationException;
import br.com.fiap.restaurantmanagement.domain.exceptions.TransactionException;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.ReservationAdapterPort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.TableAdapterPort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.UserAdapterPort;

import java.util.List;
import java.util.Random;

/**
 * This class represents the use case for creating a reservation
 */
public class CreateReservationUseCase implements CreateReservationUseCasePort {

  private final ReservationAdapterPort reservationSaveAdapterPort;

  private final TableAdapterPort tableAdapterPort;

  private final UserAdapterPort userAdapterPort;

  private final Random random = new Random();

  public CreateReservationUseCase(
          ReservationAdapterPort reservationSaveAdapterPort,
          TableAdapterPort tableAdapterPort,
          UserAdapterPort userAdapterPort) {

    this.reservationSaveAdapterPort = reservationSaveAdapterPort;
    this.tableAdapterPort = tableAdapterPort;
    this.userAdapterPort = userAdapterPort;

  }

  @Override
  public Reservation execute(Reservation reservation) {

    try {

      var reservations = reservationSaveAdapterPort.findReservationsByRestaurant(reservation.getRestaurant().getRestaurantId());

      var tablesInRestaurant = tableAdapterPort.findTablesByRestaurant(reservation.getRestaurant().getRestaurantId());

      var tablesNotReservation = tableAdapterPort.findTablesNotReservation(reservation.getRestaurant().getRestaurantId());

      var user = userAdapterPort.findByEmail(reservation.getClient().getEmail());

      return  reservationSaveAdapterPort.save(orchestrateCreateReservation(
              reservations,
              tablesInRestaurant,
              tablesNotReservation,
              user,
              reservation
      ));

    } catch (OccupiedTablesToReservationException occupiedTablesToReservationException) {

      throw occupiedTablesToReservationException;

    } catch (Exception exception) {

      throw  new TransactionException("An error occurred trying to process the transaction");

    }

  }

  private Reservation orchestrateCreateReservation(
          List<ReservationModel> reservations,
          List<TableModel> tablesInRestaurant,
          List<TableModel> tablesNotReservation,
          UserModel user,
          Reservation reservation

  ) {

    if (reservations.isEmpty()) {

      return createReservationIfNotExistsReservationsInRestaurant(
              tablesInRestaurant,
              reservation,
              user
      );

    }

    if (reservations.size() < tablesInRestaurant.size()) {

      return createReservationIfFreeTables(
              tablesNotReservation,
              reservation,
              user
      );

    }

    throw new OccupiedTablesToReservationException(
            "All tables are occupied in the restaurant for the selected date and time");

  }

  private Reservation createReservationIfNotExistsReservationsInRestaurant(
          List<TableModel> tablesInRestaurant,
          Reservation reservation,
          UserModel user) {

        var tableSelect = random.nextInt(tablesInRestaurant.size() - 1);
        var table = tablesInRestaurant.get(tableSelect);

        reservation.getRestaurant().getTable().setId(table.getId());
    setCommonValuesInTypeReservation(
            reservation,
            table,
            user
    );

    return reservation;

  }

  private Reservation createReservationIfFreeTables(
          List<TableModel> tablesNotReservation,
          Reservation reservation,
          UserModel user
  ) {
        var table = tablesNotReservation.stream().findFirst().orElseThrow();

        reservation.getRestaurant().getTable().setId(table.getId());

        setCommonValuesInTypeReservation(
                reservation,
                table,
                user
        );

    return reservation;

  }

  private void setCommonValuesInTypeReservation(
          Reservation reservation,
          TableModel table,
          UserModel user) {

    reservation.getRestaurant().getTable().setDescription(table.getDescription());
    reservation.getClient().setId(user.getId());
    reservation.getClient().setName(user.getName());

  }

}