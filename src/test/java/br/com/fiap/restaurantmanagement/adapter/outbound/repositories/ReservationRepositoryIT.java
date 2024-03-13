package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.*;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@ActiveProfiles("test")
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
        //Arrange
        var id = 1L;

        //Act
        reservationRepository.deleteById(id);
        var reservationReceiveOptional = reservationRepository.findById(id);

        //Assert
        assertThat(reservationReceiveOptional).isEmpty();
    }

    @Test
    void devePermitirListarReserva() {
        //Act
        var reservationResults = reservationRepository.findAll();

        //Assert
        assertThat(reservationResults).hasSizeGreaterThan(0);
    }

    private ReservationModel gerarReserva() {
        return ReservationModel.builder()
                .date(LocalDateTime.now().toString())
                .time(LocalTime.now().toString())
                .numberOfpeople(2)
                .restaurant(RestaurantModel.builder()
                        .id(1L)
                        .name("Meu restaurante")
                        .foodType(FoodTypeModel.builder()
                                .id(1L)
                                .name(TypesOfFood.BRAZILIAN)
                                .createdAt(LocalDateTime.now())
                                .build())
                        .build())
                .user(UserModel.builder()
                        .id(1L)
                        .name("Januário")
                        .email("januario@hotmail.com")
                        .build())
                .table(TableModel.builder()
                        .id(2L)
                        .description("Mesa para 4 pessoas")
                        .numberOfSeats(4)
                        .restaurant(RestaurantModel.builder()
                                .id(1L)
                                .name("Meu restaurante")
                                .foodType(FoodTypeModel.builder()
                                        .id(1L)
                                        .name(TypesOfFood.BRAZILIAN)
                                        .createdAt(LocalDateTime.now())
                                        .build())
                                .build())
                        .createdAt(LocalDateTime.now())
                        .build())
                .build();
    }

}