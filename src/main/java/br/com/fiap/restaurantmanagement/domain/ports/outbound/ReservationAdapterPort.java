package br.com.fiap.restaurantmanagement.domain.ports.outbound;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.ReservationModel;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;

import java.util.List;

public interface ReservationAdapterPort extends  SaveAdapterPort<Reservation>{

  List<ReservationModel> findReservationsByRestaurant(Long id);

  ReservationModel findById(Long id);

  void deleteById(Long id);

}