package br.com.fiap.restaurantmanagement.domain.usescases;

import br.com.fiap.restaurantmanagement.domain.exceptions.OccupiedTablesToReservationException;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateReservationUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static br.com.fiap.restaurantmanagement.utils.ReservationHelper.assembleReservation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"/insert_reservations_data.sql"})
@AutoConfigureTestDatabase
class CreateReservationUseCaseIT {

    @Autowired
    private CreateReservationUseCase createReservationUseCase;

    @Test
    void shouldThrownAnExceptionWhenTryingCreateReservation() {

        //Arrange
        var reservationRequest1 = assembleReservation("2021-10-10", "12:00");
        var reservationRequest2 = assembleReservation("2021-10-10", "12:00");
        var reservationRequest3 = assembleReservation("2021-10-10", "12:00");
        var reservationRequest4 = assembleReservation("2021-10-10", "12:00");
        var reservationRequest5 = assembleReservation("2021-10-10", "12:00");

        createReservationUseCase.execute(reservationRequest1);
        createReservationUseCase.execute(reservationRequest2);
        createReservationUseCase.execute(reservationRequest3);
        createReservationUseCase.execute(reservationRequest4);

        //Act & Assert
        assertThatThrownBy(() -> createReservationUseCase.execute(reservationRequest5))
                .isInstanceOf(OccupiedTablesToReservationException.class)
                .hasMessage("All tables are occupied in the restaurant for the selected date and time");
    }

    @Test
    void shouldCreateReservation() {

        //Arrange
        var reservationRequest = assembleReservation("2022-10-10", "12:00:37.942483700");

        //Act
        var reservation = createReservationUseCase.execute(reservationRequest);

        //Assert
        assertThat(reservation.getClient().getName()).isEqualTo(reservationRequest.getClient().getName());
        assertThat(reservation.getClient().getEmail()).isEqualTo(reservationRequest.getClient().getEmail());
        assertThat(reservation.getClient().getPhoneNumber()).isEqualTo(reservationRequest.getClient().getPhoneNumber());
        assertThat(reservation.getRestaurant().getRestaurantId()).isEqualTo(reservationRequest.getRestaurant().getRestaurantId());
    }

}