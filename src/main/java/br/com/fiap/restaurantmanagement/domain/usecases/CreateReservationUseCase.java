package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveReservationAdapterPort;

public class CreateReservationUseCase implements CreateReservationUseCasePort {
    private SaveReservationAdapterPort<Reservation> saveReservationAdapterPort;

    public CreateReservationUseCase(SaveReservationAdapterPort saveReservationAdapterPort) {
        this.saveReservationAdapterPort = saveReservationAdapterPort;
    }

    @Override
    public Reservation execute(Reservation reservation) {
        var reservationResult = this.saveReservationAdapterPort.saveReservation(reservation);

        return reservationResult;
    }
}