package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.ReservationModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.ReservationAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationAdapter implements ReservationAdapterPort {

  private final ReservationRepository reservationRepository;

  public ReservationAdapter(ReservationRepository reservationRepository) {

    this.reservationRepository = reservationRepository;

  }

  @Override
  public List<ReservationModel> findReservationsByRestaurant(Long id) {

    return reservationRepository.findReservationsByRestaurant(id);

  }

  @Override
  public ReservationModel findById(Long id) {

    return reservationRepository.findById(id).orElseThrow();

  }

  @Override
  public void deleteById(Long id) {

    reservationRepository.deleteById(id);

  }

  @Override
  public Reservation save(Reservation reservation) {

    var reservationModel = ReservationModel
            .builder()
            .date(reservation.getDate().toString())
            .time(reservation.getTime().toString())
            .user(UserModel
                    .builder()
                    .id(reservation.getClient().getId())
                    .name(reservation.getClient().getName())
                    .email(reservation.getClient().getEmail())
                    .phoneNumber(reservation.getClient().getPhoneNumber())
                    .build())
            .numberOfpeople(reservation.getRestaurant().getTable().getCapacity())
            .restaurant(RestaurantModel
                    .builder()
                    .id(reservation.getRestaurant().getRestaurantId())
                    .build())
            .table(TableModel
                    .builder()
                    .id(reservation.getRestaurant().getTable().getId())
                    .description(reservation.getRestaurant().getTable().getDescription())
                    .build())
            .build();

    reservationRepository.save(reservationModel);

    return reservation;

  }

}