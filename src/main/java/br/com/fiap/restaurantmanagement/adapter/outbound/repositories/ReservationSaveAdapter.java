package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.ReservationModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class represents the reservation save adapter
 */
@Component
public class ReservationSaveAdapter implements SaveAdapterPort<Reservation> {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationSaveAdapter(ReservationRepository reservationRepository) {

        this.reservationRepository = reservationRepository;

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