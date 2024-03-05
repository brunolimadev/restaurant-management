package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.*;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationRepositoryTest {

    @Mock
    private ReservationRepository reservationRepository;
    private AutoCloseable openMocks;

    @BeforeEach
    public void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermirtirSalvarReserva() {
        //Arrange
        var reserva = gerarReserva();

        when(reservationRepository.save(any(ReservationModel.class))).thenReturn(reserva);

        //Act
        var reservaResult = reservationRepository.save(reserva);

        //Assert
        assertThat(reservaResult).isNotNull().isInstanceOf(ReservationModel.class);
        assertThat(reservaResult.getUser().getName()).isEqualTo(reserva.getUser().getName());
        verify(reservationRepository, times(1)).save(any(ReservationModel.class));
    }

    @Test
    void devePermitirAlterarReserva() {
        fail("teste não implementado");
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
                .id(1L)
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