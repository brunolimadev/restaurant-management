package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import org.springframework.stereotype.Component;

/**
 * This class represents the reservation save adapter
 */
@Component
public class ReservationSaveAdapter implements SaveAdapterPort<Reservation> {

    @Override
    public Reservation save(Reservation entity) {
        return null;
    }

}