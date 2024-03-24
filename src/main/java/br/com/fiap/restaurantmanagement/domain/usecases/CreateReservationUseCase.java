package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.RestaurantRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.TableRepository;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class represents the use case for creating a reservation
 */
public class CreateReservationUseCase implements CreateReservationUseCasePort {

    private final SaveAdapterPort<Reservation> reservationSaveAdapterPort;

    private final RestaurantRepository restaurantRepository;

    private final ReservationRepository reservationRepository;

    private final TableRepository tableRepository;

    public CreateReservationUseCase(SaveAdapterPort<Reservation> reservationSaveAdapterPort,
                                    RestaurantRepository restaurantRepository,
                                    ReservationRepository reservationRepository,
                                    TableRepository tableRepository) {

        this.reservationSaveAdapterPort = reservationSaveAdapterPort;
        this.restaurantRepository = restaurantRepository;
        this.reservationRepository = reservationRepository;
        this.tableRepository = tableRepository;

    }

    @Override
    public Reservation execute(Reservation reservation) {

        var restaurant = restaurantRepository.findById(reservation.getRestaurant().getRestaurantId());
        var reservations = reservationRepository.findAll();
        var tables = tableRepository.findTablesByRestaurantId(reservation.getRestaurant().getRestaurantId());

        reservations.stream().forEach(reservationModel -> tables
                .forEach(tableModel -> {
                    if (Objects.equals(reservation.getRestaurant().getRestaurantId(), reservationModel.getRestaurant().getId())) {

                        if (Objects.equals(tableModel.getId(), reservationModel.getTable().getId())) {

                            if (reservation.getDate().isEqual(LocalDate.parse(reservationModel.getDate())) && reservation.getTime().toString().equals(reservationModel.getTime())) {

                                throw new IllegalArgumentException("Já existe reserva neste restaurante");

                            }


                        }
                    }
                }));


        return  this.reservationSaveAdapterPort.save(reservation);

    }
}