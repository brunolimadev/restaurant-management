package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateReservationRequest;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.GetReservationsRequestHeaders;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateReservationResponse;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.GetReservationsResponse;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.DeleteReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.FindReservationsUseCasePort;
import br.com.fiap.restaurantmanagement.domain.usecases.DeleteReservationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * This class represents the reservation controller
 */
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final CreateReservationUseCasePort createReservationUseCasePort;

    private final FindReservationsUseCasePort findReservationsUseCasePort;

    private final DeleteReservationUseCasePort deleteReservationUseCasePort;

    public ReservationController(
            CreateReservationUseCasePort createReservationUseCasePort,
            FindReservationsUseCasePort findReservationsUseCasePort,
            DeleteReservationUseCasePort deleteReservationUseCasePort) {

        this.createReservationUseCasePort = createReservationUseCasePort;
        this.findReservationsUseCasePort = findReservationsUseCasePort;
        this.deleteReservationUseCasePort = deleteReservationUseCasePort;
    }

    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservation(@RequestBody CreateReservationRequest createReservationRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        CreateReservationResponse
                        .builder()
                        .reservation(createReservationUseCasePort.execute(createReservationRequest.toDomain()))
                        .message("Reserva realizada com sucesso")
                        .build()
                );
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations(
            @RequestHeader("restaurant_id") String restaurantId,
            @RequestHeader("date") String date,
            @RequestHeader("time") String time) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        GetReservationsResponse
                        .builder()
                        .reservations(findReservationsUseCasePort.get(
                                GetReservationsRequestHeaders
                                        .builder()
                                        .restaurantId(Long.parseLong(restaurantId))
                                        .date(LocalDate.parse(date))
                                        .time(LocalTime.parse(time))
                                        .build())
                        )
                        .build().getReservations()
                );

    }

    @DeleteMapping
    @RequestMapping("{id}")
    public ResponseEntity<Reservation> deleteReservation(
            @PathVariable("id") String id
    ) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        deleteReservationUseCasePort.delete(Long.parseLong(id))
                );

    }
}