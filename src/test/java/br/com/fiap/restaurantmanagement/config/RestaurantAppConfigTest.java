package br.com.fiap.restaurantmanagement.config;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateRestaurantUseCase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration
public class RestaurantAppConfigTest {

    @Bean
    public CreateRestaurantUseCasePort createRestaurantUseCase(SaveAdapterPort<Restaurant> saveAdapterPort) {
        return new CreateRestaurantUseCase(saveAdapterPort);
    }

}
