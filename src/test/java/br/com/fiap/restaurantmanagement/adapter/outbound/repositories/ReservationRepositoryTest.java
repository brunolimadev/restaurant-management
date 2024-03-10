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
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
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
    void devePermitirRemoverReserva() {
        //Arrange
        var id = 1L;
        doNothing().when(reservationRepository).deleteById(anyLong());

        //Act
        reservationRepository.deleteById(id);

        //Assert
        verify(reservationRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void devePermitirListarReserva() {
        //Arrange
        var reservationOne = gerarReserva();
        var reservationTwo = gerarReserva();
        var reservationThree = gerarReserva();
        var reservationList = Arrays.asList(reservationOne, reservationTwo, reservationThree);

        when(reservationRepository.findAll()).thenReturn(reservationList);

        //Act
        var receiveReservationList = reservationRepository.findAll();

        //Assert
        assertThat(receiveReservationList)
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder(reservationOne, reservationTwo, reservationThree);
        verify(reservationRepository, times(1)).findAll();
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