package br.com.fiap.restaurantmanagement.domain.usescases;

import br.com.fiap.restaurantmanagement.domain.exceptions.TransactionException;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.ReservationAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.DeleteReservationUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.fiap.restaurantmanagement.utils.ReservationHelper.assembleReservationModel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DeleteReservationUseCaseTest {

  @Mock
  private ReservationAdapterPort reservationAdapterPort;

  private DeleteReservationUseCase deleteReservationUseCase;

  private AutoCloseable openMocks;


  @BeforeEach
  void  setup() {

    openMocks = MockitoAnnotations.openMocks(this);

    deleteReservationUseCase = new DeleteReservationUseCase(
            reservationAdapterPort
    );

  }

  @AfterEach
  void tearDown() throws Exception {

    openMocks.close();

  }

  @Test
  void shouldDeleteTheReservationSuccessfully() {

    //Arrange
    var request = assembleReservationModel();

    when(reservationAdapterPort.findById(anyLong())).thenReturn(assembleReservationModel());
    doNothing().when(reservationAdapterPort).deleteById(anyLong());

    //Act
    var response = deleteReservationUseCase.delete(request.getId());

    //Assert
    assertThat(response.getId()).isEqualTo(request.getId());
    verify(reservationAdapterPort, times(1)).deleteById(anyLong());

  }

  @Test
  void shouldThrowExceptionWhenTryingToDeleteReservation() {

    //Arrange
    var request = assembleReservationModel();

    doThrow(RuntimeException.class).when(reservationAdapterPort).deleteById(anyLong());

    //Act & Assert
    assertThatThrownBy(() -> deleteReservationUseCase.delete(request.getId()))
            .isInstanceOf(TransactionException.class)
            .hasMessage(
                    "An error occurred while processing the reservation removal"
            );

  }

}