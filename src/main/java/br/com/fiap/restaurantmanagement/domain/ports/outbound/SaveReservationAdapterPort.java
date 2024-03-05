package br.com.fiap.restaurantmanagement.domain.ports.outbound;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;

public interface SaveReservationAdapterPort<T> {
    T saveReservation(Reservation reservation);
}