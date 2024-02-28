package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.RestaurantRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.FoodTypeModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


class RestaurantRepositoryTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    AutoCloseable openMocks;

    @BeforeEach
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldPermitRegisterARestaurant() {

        // arrange
        var restaurant = createRestaurant();
        when(restaurantRepository.save(any(RestaurantModel.class))).thenReturn(restaurant);

        // act
        var result = restaurantRepository.save(restaurant);

        // assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(restaurant.getName());

        verify(restaurantRepository, times(1)).save(any(RestaurantModel.class));

    }

    private RestaurantModel createRestaurant() {
        var restaurant = new RestaurantModel();

        restaurant.setFoodType(FoodTypeModel.fromDomain(TypesOfFood.BRAZILIAN, null));
        restaurant.setName("Restaurant 1");

        return restaurant;
    }


}
