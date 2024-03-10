package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;

public class CreateReservationUseCase implements CreateReservationUseCasePort {
    private SaveAdapterPort<Reservation> saveAdapterPort;

    public CreateReservationUseCase(SaveAdapterPort saveAdapterPort) {
        this.saveAdapterPort = saveAdapterPort;
    }

    @Override
    public Reservation execute(Reservation reservation) {
        var reservationResult = this.saveAdapterPort.save(reservation);

        return reservationResult;
    }
}