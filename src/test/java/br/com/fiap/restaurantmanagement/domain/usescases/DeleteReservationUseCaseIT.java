package br.com.fiap.restaurantmanagement.domain.usescases;

import br.com.fiap.restaurantmanagement.domain.usecases.DeleteReservationUseCase;
import br.com.fiap.restaurantmanagement.domain.usecases.FindReservationsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static br.com.fiap.restaurantmanagement.utils.ReservationHelper.assembleReservationsRequestHeaders;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class DeleteReservationUseCaseIT {

  @Autowired
  private DeleteReservationUseCase deleteReservationUseCase;

  @Autowired
  private FindReservationsUseCase findReservationsUseCase;

  @Test
  void shouldDeleteAnExistingReservation() {

    //Arrange
    var request = findReservationsUseCase.get(assembleReservationsRequestHeaders());

    //Act
    var response = deleteReservationUseCase.delete(request.get(0).getId());

    //Assert
    assertThat(response.getId()).isEqualTo(request.get(0).getId());

  }

}