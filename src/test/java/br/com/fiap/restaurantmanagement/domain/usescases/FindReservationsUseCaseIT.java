package br.com.fiap.restaurantmanagement.domain.usescases;

import br.com.fiap.restaurantmanagement.domain.usecases.FindReservationsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static br.com.fiap.restaurantmanagement.utils.ReservationHelper.assembleReservationsRequestHeaders;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"/insert_reservations_data.sql"})
@AutoConfigureTestDatabase
class FindReservationsUseCaseIT {

  @Autowired
  private FindReservationsUseCase findReservationsUseCase;

  @Test
  void shouldLookForAndFindReserves() {

    //Arrange
    var request = assembleReservationsRequestHeaders();

    //Act
    var response = findReservationsUseCase.get(request);

    //Assert
    assertThat(response).isNotEmpty();

  }

}