package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.TableRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.UserRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.ReservationModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.exceptions.OccupiedTablesToReservationException;
import br.com.fiap.restaurantmanagement.domain.exceptions.TransactionException;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;

import java.util.List;
import java.util.Random;

/**
 * This class represents the use case for creating a reservation
 */
public class CreateReservationUseCase implements CreateReservationUseCasePort {

  private final SaveAdapterPort<Reservation> reservationSaveAdapterPort;

  private final ReservationRepository reservationRepository;

  private final TableRepository tableRepository;

  private final UserRepository userRepository;

  private final Random random = new Random();

  public CreateReservationUseCase(SaveAdapterPort<Reservation> reservationSaveAdapterPort,
                                  ReservationRepository reservationRepository,
                                  TableRepository tableRepository,
                                  UserRepository userRepository) {

    this.reservationSaveAdapterPort = reservationSaveAdapterPort;
    this.reservationRepository = reservationRepository;
    this.tableRepository = tableRepository;
    this.userRepository = userRepository;

  }

  @Override
  public Reservation execute(Reservation reservation) {

    try {

      var reservations = reservationRepository.findReservationsByRestaurant(reservation.getRestaurant().getRestaurantId());

      var tablesInRestaurant = tableRepository.findTablesByRestaurant(reservation.getRestaurant().getRestaurantId());

      var tablesNotReservation = tableRepository.findTablesNotReservation(reservation.getRestaurant().getRestaurantId());

      var user = userRepository.findByEmail(reservation.getClient().getEmail());

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