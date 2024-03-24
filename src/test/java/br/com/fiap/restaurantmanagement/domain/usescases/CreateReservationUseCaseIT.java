package br.com.fiap.restaurantmanagement.domain.usescases;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateReservationRequest;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateReservationUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class CreateReservationUseCaseIT {

    @Autowired
    private CreateReservationUseCase createReservationUseCase;

    private AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveGerarExcecaoAoTentarCriarReserva() {
        CreateReservationRequest reservationRequest = CreateReservationRequest
                .builder()
                .name("Zeca")
                .email("zeca@gmail.com")
                .phoneNumber("11958974563")
                .restaurantId(1L)
                .places(1)
                .date(LocalDate.parse("2021-10-10"))
                .time(LocalTime.parse("12:00:37.942483700"))
                .build();

        assertThatThrownBy(() -> createReservationUseCase.execute(reservationRequest.toDomain()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Já existe reserva neste restaurante");
    }

    @Test
    void deveCriarReserva() {
        CreateReservationRequest reservationRequest = CreateReservationRequest
                .builder()
                .name("Zeca")
                .email("zeca@gmail.com")
                .phoneNumber("11958974563")
                .restaurantId(1L)
                .places(1)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();

        var reservation = createReservationUseCase.execute(reservationRequest.toDomain());

        assertThat(reservation.getClient().getName()).isEqualTo(reservationRequest.toDomain().getClient().getName());
        assertThat(reservation.getClient().getEmail()).isEqualTo(reservationRequest.toDomain().getClient().getEmail());
        assertThat(reservation.getClient().getPhoneNumber()).isEqualTo(reservationRequest.toDomain().getClient().getPhoneNumber());
        assertThat(reservation.getRestaurant().getRestaurantId()).isEqualTo(reservationRequest.toDomain().getRestaurant().getRestaurantId());
        assertThat(reservation.getRestaurant().getTable().getId()).isEqualTo(reservationRequest.toDomain().getRestaurant().getTable().getId());
    }

}