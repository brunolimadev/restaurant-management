package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.TableRepository;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import java.util.Random;

/**
 * This class represents the use case for creating a reservation
 */
public class CreateReservationUseCase implements CreateReservationUseCasePort {

  private final SaveAdapterPort<Reservation> reservationSaveAdapterPort;

  private final ReservationRepository reservationRepository;

  private final TableRepository tableRepository;

  private final Random random = new Random();

  public CreateReservationUseCase(SaveAdapterPort<Reservation> reservationSaveAdapterPort,
                                  ReservationRepository reservationRepository,
                                  TableRepository tableRepository) {

    this.reservationSaveAdapterPort = reservationSaveAdapterPort;
    this.reservationRepository = reservationRepository;
    this.tableRepository = tableRepository;

  }

  @Override
  public Reservation execute(Reservation reservation) {

    var reservations = reservationRepository
            .findReservationsByRestaurantDateTime(
                    reservation.getRestaurant().getRestaurantId(),
                    reservation.getDate().toString(),
                    reservation.getTime().toString());

    var tablesInRestaurant = tableRepository.findTablesByRestaurant(reservation.getRestaurant().getRestaurantId());

    var tablesNotReservation = tableRepository.findTablesNotReservation(reservation.getRestaurant().getRestaurantId());

    if (reservations.isEmpty()) {

      var tableSelect = random.nextInt(tablesInRestaurant.size() - 1);
      var table = tablesInRestaurant.get(tableSelect);

      reservation.getRestaurant().getTable().setId(table.getId());
      reservation.getRestaurant().getTable().setDescription(table.getDescription());
      reservation.getRestaurant().getTable().setCapacity(table.getNumberOfSeats());

      return  this.reservationSaveAdapterPort.save(reservation);

    }

    if (reservations.size() < tablesInRestaurant.size()) {

      var table = tablesNotReservation.stream()
              .findFirst()
              .orElseThrow();

      reservation.getRestaurant().getTable().setId(table.getId());
      reservation.getRestaurant().getTable().setDescription(table.getDescription());

      return this.reservationSaveAdapterPort.save(reservation);
    }

    throw new IllegalArgumentException("Todas as mesas estão ocupadas no restaurante para a data e horário selecionados");

  }
}