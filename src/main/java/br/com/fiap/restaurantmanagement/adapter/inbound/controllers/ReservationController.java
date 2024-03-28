package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateReservationRequest;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.GetReservationsRequestHeaders;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateReservationResponse;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.GetReservationsResponse;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.DeleteReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.FindReservationsUseCasePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents the reservation controller
 */
@RestController
@RequestMapping("/reservation")
@Tag(name = "Reservation Controller",
     description = "When the customer finds the restaurant, they can make a reservation for a specific day and time as long as the restaurant's capacity has not reached its maximum for that date and time.")
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

    @Operation(summary = "Register reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reservation made successfully")
    })
    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservation(
            @RequestBody CreateReservationRequest createReservationRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        CreateReservationResponse
                        .builder()
                        .reservation(createReservationUseCasePort.execute(createReservationRequest.toDomain()))
                        .message("Reservation made successfully")
                        .build()
                );
    }

    @Operation(summary = "Search for reservations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations(
            @Parameter(description = "restaurant id", required = true)
            @RequestHeader("restaurant_id") String restaurantId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        GetReservationsResponse
                        .builder()
                        .reservations(findReservationsUseCasePort.get(
                                GetReservationsRequestHeaders
                                        .builder()
                                        .restaurantId(Long.parseLong(restaurantId))
                                        .build())
                        )
                        .build().getReservations()
                );

    }

    @Operation(summary = "Cancel reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "reservation id"),
    })
    @DeleteMapping
    @RequestMapping("{id}")
        public ResponseEntity<Reservation> deleteReservation(
            @Parameter(description = "Reservation id", required = true)
            @PathVariable("id") String id
    ) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        deleteReservationUseCasePort.delete(Long.parseLong(id))
                );

    }
}