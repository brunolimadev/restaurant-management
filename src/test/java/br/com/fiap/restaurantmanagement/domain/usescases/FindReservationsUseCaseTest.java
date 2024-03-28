package br.com.fiap.restaurantmanagement.domain.usescases;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.ReservationModel;
import br.com.fiap.restaurantmanagement.domain.exceptions.TransactionException;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.ReservationAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.FindReservationsUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.restaurantmanagement.utils.ReservationHelper.assembleReservationModel;
import static br.com.fiap.restaurantmanagement.utils.ReservationHelper.assembleReservationsRequestHeaders;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class FindReservationsUseCaseTest {

  @Mock
  private ReservationAdapterPort reservationAdapterPort;

  private FindReservationsUseCase findReservationsUseCase;

  private AutoCloseable openMocks;


  @BeforeEach
  void  setup() {

    openMocks = MockitoAnnotations.openMocks(this);

    findReservationsUseCase = new FindReservationsUseCase(
            reservationAdapterPort
    );

  }

  @AfterEach
  void tearDown() throws Exception {

    openMocks.close();

  }

  @Test
  void shouldReservationsSuccessfully() {

    //Arrange
    List<ReservationModel> request = new ArrayList<>();
    request.add(assembleReservationModel());

    when(reservationAdapterPort.findReservationsByRestaurant(anyLong())).thenReturn(request);

    //Act
    var response = findReservationsUseCase.get(assembleReservationsRequestHeaders());

    //Assert
    assertThat(response.get(0).getId()).isEqualTo(request.get(0).getId());
    verify(reservationAdapterPort, times(1)).findReservationsByRestaurant(anyLong());

  }

  @Test
  void shouldThrowExceptionWhenTryingToListReservations() {

    //Arrange
    when(reservationAdapterPort.findReservationsByRestaurant(anyLong())).thenThrow(RuntimeException.class);

    //Act & Assert
    assertThatThrownBy(() -> findReservationsUseCase.get(assembleReservationsRequestHeaders()))
            .isInstanceOf(TransactionException.class)
            .hasMessage(
                    "An error occurred while processing reservation list"
            );

  }

}