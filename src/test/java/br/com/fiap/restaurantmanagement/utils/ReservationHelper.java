package br.com.fiap.restaurantmanagement.utils;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateReservationRequest;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.GetReservationsRequestHeaders;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationHelper {

  public static Reservation assembleReservation(String date, String time) {
    return   CreateReservationRequest
            .builder()
            .name("Zeca")
            .email("zeca@gmail.com")
            .phoneNumber("11958974563")
            .restaurantId(1L)
            .places(1)
            .date(LocalDate.parse(date))
            .time(LocalTime.parse(time))
            .build().toDomain();
  }

  public static GetReservationsRequestHeaders assembleReservationsRequestHeaders() {

    return GetReservationsRequestHeaders
            .builder()
            .restaurantId(1L)
            .build();

  }

}
