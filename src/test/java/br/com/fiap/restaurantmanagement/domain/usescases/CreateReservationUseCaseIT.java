package br.com.fiap.restaurantmanagement.domain.usescases;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateReservationRequest;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateReservationUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Sql(scripts = "/delete_reservations_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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
        //Arrange
        var reservationRequest1 = gerarReserva("2021-10-10", "12:00");
        var reservationRequest2 = gerarReserva("2021-10-10", "12:00");
        var reservationRequest3 = gerarReserva("2021-10-10", "12:00");
        var reservationRequest4 = gerarReserva("2021-10-10", "12:00");
        var reservationRequest5 = gerarReserva("2021-10-10", "12:00");

        createReservationUseCase.execute(reservationRequest1);
        createReservationUseCase.execute(reservationRequest2);
        createReservationUseCase.execute(reservationRequest3);
        createReservationUseCase.execute(reservationRequest4);

        //Act & Assert
        assertThatThrownBy(() -> createReservationUseCase.execute(reservationRequest5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Todas as mesas estão ocupadas no restaurante para a data e horário selecionados");
    }

    @Test
    void deveCriarReserva() {
        //Arrange
        var reservationRequest = gerarReserva("2022-10-10", "12:00:37.942483700");

        //Act
        var reservation = createReservationUseCase.execute(reservationRequest);

        //Assert
        assertThat(reservation.getClient().getName()).isEqualTo(reservationRequest.getClient().getName());
        assertThat(reservation.getClient().getEmail()).isEqualTo(reservationRequest.getClient().getEmail());
        assertThat(reservation.getClient().getPhoneNumber()).isEqualTo(reservationRequest.getClient().getPhoneNumber());
        assertThat(reservation.getRestaurant().getRestaurantId()).isEqualTo(reservationRequest.getRestaurant().getRestaurantId());
    }

    private Reservation gerarReserva(String date, String time) {
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

}