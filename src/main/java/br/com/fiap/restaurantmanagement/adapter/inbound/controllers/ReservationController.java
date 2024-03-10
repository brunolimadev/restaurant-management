package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateReservationRequest;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateReservationResponse;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateRestaurantResponse;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final CreateReservationUseCasePort createReservationUseCasePort;

    public ReservationController(CreateReservationUseCasePort createReservationUseCasePort) {
        this.createReservationUseCasePort = createReservationUseCasePort;
    }

    @PostMapping
    public ResponseEntity<CreateReservationResponse> createRestaurant(@RequestBody CreateReservationRequest createReservationRequest) {

        Reservation reservation = createReservationUseCasePort.execute(createReservationRequest.toDomain());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                CreateReservationResponse.builder()
                        .id(reservation.getId())
                        .build()
        );
    }
}