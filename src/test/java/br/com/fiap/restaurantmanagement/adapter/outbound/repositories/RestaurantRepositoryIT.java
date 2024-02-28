package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.RestaurantRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.FoodTypeModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class RestaurantRepositoryIT {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void shouldPermitCreateATable() {
        // Please, check if sql script is into resources folder before run this test
        var totalDeRegistros = restaurantRepository.count();
        assertThat(totalDeRegistros).isGreaterThan(0);
    }

    @Test
    void shouldPermittedRegisterARestaurant() {

        // arrange
        var restaurant = createRestaurant();

        // act
        var result = restaurantRepository.save(restaurant);

        // assert
        assertThat(TypesOfFood.BRAZILIAN.equals(restaurant.getFoodType())).isNotNull();

    }

    private RestaurantModel createRestaurant() {
        var restaurant = new RestaurantModel();

        restaurant.setFoodType(FoodTypeModel.fromDomain(TypesOfFood.BRAZILIAN, null));
        restaurant.setName("Restaurant 1");

        return restaurant;
    }
}
