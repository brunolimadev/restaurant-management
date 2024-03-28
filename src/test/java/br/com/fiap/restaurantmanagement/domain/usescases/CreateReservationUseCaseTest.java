package br.com.fiap.restaurantmanagement.domain.usescases;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.ReservationModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.exceptions.OccupiedTablesToReservationException;
import br.com.fiap.restaurantmanagement.domain.exceptions.TransactionException;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.ReservationAdapterPort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.TableAdapterPort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.UserAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateReservationUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.restaurantmanagement.utils.ReservationHelper.assembleReservation;
import static br.com.fiap.restaurantmanagement.utils.ReservationHelper.assembleReservationModel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CreateReservationUseCaseTest {

  @Mock
  private ReservationAdapterPort reservationAdapterPort;

  @Mock
  private TableAdapterPort tableAdapterPort;

  @Mock
  private UserAdapterPort userAdapterPort;

  private CreateReservationUseCase createReservationUseCase;

  private AutoCloseable openMocks;

  @BeforeEach
  void  setup() {

    openMocks = MockitoAnnotations.openMocks(this);

    createReservationUseCase = new CreateReservationUseCase(
            reservationAdapterPort,
            tableAdapterPort,
            userAdapterPort
    );

  }

  @AfterEach
  void tearDown() throws Exception {

    openMocks.close();

  }

  @Test
  void shouldMustCreateReservationWhenThereAreNoReservationsAtTheRestaurant() {

    //Arrange
    var request = assembleReservation(
            LocalDate.now().toString(), LocalTime.now().toString());

    List<ReservationModel> reservations = new ArrayList<>();
    List<TableModel> freeTablesInRestaurant = new ArrayList<>();

    freeTablesInRestaurant.add(assembleReservationModel().getTable());


    when(tableAdapterPort.findTablesByRestaurant(anyLong())).thenReturn(freeTablesInRestaurant);
    when(tableAdapterPort.findTablesNotReservation(anyLong())).thenReturn(new ArrayList<>());
    when(userAdapterPort.findByEmail(anyString())).thenReturn(assembleReservationModel().getUser());
    when(reservationAdapterPort.findReservationsByRestaurant(anyLong())).thenReturn(reservations);
    when(reservationAdapterPort.save(any(Reservation.class))).thenReturn(request);

    //Act
    var response = createReservationUseCase.execute(request);

    //Assert
    assertThat(response.getId()).isEqualTo(request.getId());
    verify(reservationAdapterPort, times(1)).save(any(Reservation.class));

  }

  @Test
  void shouldMustCreateReservationWhenThereAreReservationsAtTheRestaurant() {

    //Arrange
    var request = assembleReservation(
            LocalDate.now().toString(), LocalTime.now().toString());

    List<ReservationModel> reservations = new ArrayList<>();
    List<TableModel> occupiedTablesInRestaurant = new ArrayList<>();
    List<TableModel> freeTablesInRestaurant = new ArrayList<>();

    reservations.add(assembleReservationModel());

    occupiedTablesInRestaurant.add(assembleReservationModel().getTable());
    occupiedTablesInRestaurant.add(assembleReservationModel().getTable());

    freeTablesInRestaurant.add(assembleReservationModel().getTable());


    when(tableAdapterPort.findTablesByRestaurant(anyLong())).thenReturn(occupiedTablesInRestaurant);
    when(tableAdapterPort.findTablesNotReservation(anyLong())).thenReturn(freeTablesInRestaurant);
    when(userAdapterPort.findByEmail(anyString())).thenReturn(assembleReservationModel().getUser());
    when(reservationAdapterPort.findReservationsByRestaurant(anyLong())).thenReturn(reservations);
    when(reservationAdapterPort.save(any(Reservation.class))).thenReturn(request);

    //Act
    var response = createReservationUseCase.execute(request);

    //Assert
    assertThat(response.getId()).isEqualTo(request.getId());
    verify(reservationAdapterPort, times(1)).save(any(Reservation.class));

  }

  @Test
  void shouldThrowAnExceptionWhenTheRestaurantHasAllTablesReserved() {

    //Arrange
    var request = assembleReservation(
            LocalDate.now().toString(), LocalTime.now().toString());

    List<ReservationModel> reservations = new ArrayList<>();
    List<TableModel> occupiedTablesInRestaurant = new ArrayList<>();
    List<TableModel> freeTablesInRestaurant = new ArrayList<>();

    reservations.add(assembleReservationModel());

    occupiedTablesInRestaurant.add(assembleReservationModel().getTable());

    freeTablesInRestaurant.add(assembleReservationModel().getTable());


    when(tableAdapterPort.findTablesByRestaurant(anyLong())).thenReturn(occupiedTablesInRestaurant);
    when(tableAdapterPort.findTablesNotReservation(anyLong())).thenReturn(freeTablesInRestaurant);
    when(userAdapterPort.findByEmail(anyString())).thenReturn(assembleReservationModel().getUser());
    when(reservationAdapterPort.findReservationsByRestaurant(anyLong())).thenReturn(reservations);
    when(reservationAdapterPort.save(any(Reservation.class))).thenReturn(request);

    //Act & Assert
    assertThatThrownBy(() -> createReservationUseCase.execute(request))
            .isInstanceOf(OccupiedTablesToReservationException.class)
            .hasMessage("All tables are occupied in the restaurant for the selected date and time");

  }

  @Test
  void shouldThrowAnExceptionWhenUnexpectedError() {

    //Arrange
    var request = assembleReservation(
            LocalDate.now().toString(), LocalTime.now().toString());

    when(reservationAdapterPort.findReservationsByRestaurant(anyLong())).thenThrow(RuntimeException.class);

    //Act & Assert
    assertThatThrownBy(() -> createReservationUseCase.execute(request))
            .isInstanceOf(TransactionException.class)
            .hasMessage("An error occurred trying to process the transaction");

  }

}