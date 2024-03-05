package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveReservationAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class SaveReservationAdapter implements SaveReservationAdapterPort<Reservation> {

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return null;
    }
}