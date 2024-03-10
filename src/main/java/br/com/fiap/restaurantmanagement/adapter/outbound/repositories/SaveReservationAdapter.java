package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class SaveReservationAdapter implements SaveAdapterPort<Reservation> {

    @Override
    public Reservation save(Reservation entity) {
        return null;
    }

}