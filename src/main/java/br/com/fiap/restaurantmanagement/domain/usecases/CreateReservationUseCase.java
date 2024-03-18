package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;

/**
 * This class represents the use case for creating a reservation
 */
public class CreateReservationUseCase implements CreateReservationUseCasePort {
    private final SaveAdapterPort<Reservation> reservationSaveAdapterPort;

    public CreateReservationUseCase(SaveAdapterPort<Reservation> reservationSaveAdapterPort) {
        this.reservationSaveAdapterPort = reservationSaveAdapterPort;
    }

    @Override
    public Reservation execute(Reservation reservation) {
        return  this.reservationSaveAdapterPort.save(reservation);
    }
}