package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.TableRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;

import java.time.LocalDate;
import java.util.Objects;
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

    var tables = tableRepository.findTablesByRestaurant(reservation.getRestaurant().getRestaurantId());

    if (reservations.isEmpty()) {

      var tableSelect = random.nextInt(tables.size() - 1);
      var table = tables.get(tableSelect);

      reservation.getRestaurant().getTable().setId(table.getId());
      reservation.getRestaurant().getTable().setDescription(table.getDescription());
      reservation.getRestaurant().getTable().setCapacity(table.getNumberOfSeats());

      return  this.reservationSaveAdapterPort.save(reservation);

    }

    if (reservations.size() < tables.size()) {

//      reservations.forEach(reservationModel -> tables
//              .forEach(tableModel -> {
//
//                if (!(Objects.equals(tableModel.getId(), reservationModel.getTable().getId()) &&
//                        (reservation.getDate().isEqual(LocalDate.parse(reservationModel.getDate())) &&
//                                reservation.getTime().toString().equals(reservationModel.getTime())))) {
//
//                  reservation.getRestaurant().getTable().setId(tableModel.getId());
//                  reservation.getRestaurant().getTable().setDescription(tableModel.getDescription());
//
//                }
//              }));

      reservations.forEach(reservationModel -> {
        for (TableModel tableModel : tables) {
          if (!(Objects.equals(tableModel.getId(), reservationModel.getTable().getId()) &&
                  (reservation.getDate().isEqual(LocalDate.parse(reservationModel.getDate())) &&
                          reservation.getTime().toString().equals(reservationModel.getTime())))) {

            reservation.getRestaurant().getTable().setId(tableModel.getId());
            reservation.getRestaurant().getTable().setDescription(tableModel.getDescription());
            break;
          }
        }
      });
      return this.reservationSaveAdapterPort.save(reservation);
    }

    throw new IllegalArgumentException("Todas as mesas estão ocupadas no restaurante para a data e horário selecionados");

  }
}