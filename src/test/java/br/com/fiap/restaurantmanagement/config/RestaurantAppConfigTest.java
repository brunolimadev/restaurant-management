package br.com.fiap.restaurantmanagement.config;

import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.RestaurantAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateRestaurantUseCase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class RestaurantAppConfigTest {

    @Bean
    public CreateRestaurantUseCasePort createRestaurantUseCase(RestaurantAdapterPort restaurantAdapterPort) {

        return new CreateRestaurantUseCase(restaurantAdapterPort);

    }

}
