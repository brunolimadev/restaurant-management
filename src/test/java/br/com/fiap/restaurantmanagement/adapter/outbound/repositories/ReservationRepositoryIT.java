package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.*;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ReservationRepositoryIT {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void devePermirtirSalvarReserva() {
        //Arrange
        var reservation = gerarReserva();

        //Act
        var reservationResult = reservationRepository.save(reservation);

        //Assert
        assertThat(reservationResult).isNotNull().isInstanceOf(ReservationModel.class);
        assertThat(reservationResult.getUser().getName()).isEqualTo(reservation.getUser().getName());
    }

    @Test
    void devePermitirRemoverReserva() {
        fail("teste não implementado");
    }

    @Test
    void devePermitirListarReserva() {
        fail("teste não implementado");
    }

    private ReservationModel gerarReserva() {
        return ReservationModel.builder()
                .date(LocalDateTime.now().toString())
                .time(LocalTime.now().toString())
                .numberOfpeople(2)
                .user(UserModel.builder()
                        .id(1L)
                        .name("Januário")
                        .email("januario@hotmail.com")
                        .build())
                .table(TableModel.builder()
                        .id(1L)
                        .description("Mesa para 4 pessoas")
                        .numberOfSeats(4)
                        .restaurant(RestaurantModel.builder()
                                .id(1L)
                                .name("Meu restaurante")
                                .foodType(FoodTypeModel.builder()
                                        .name(TypesOfFood.BRAZILIAN)
                                        .createdAt(LocalDateTime.now())
                                        .build())
                                .build())
                        .createdAt(LocalDateTime.now())
                        .build())
                .build();
    }

}