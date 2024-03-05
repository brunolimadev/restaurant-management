package br.com.fiap.restaurantmanagement.config;

import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveReservationAdapterPort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveRestaurantAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateReservationUseCase;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateRestaurantUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantAppConfig {

    @Bean
    public CreateRestaurantUseCasePort createRestaurantUseCase(SaveRestaurantAdapterPort saveRestaurantAdapterPort) {
        return new CreateRestaurantUseCase(saveRestaurantAdapterPort);
    }

    @Bean
    public CreateReservationUseCasePort createReservationUseCase(SaveReservationAdapterPort saveReservationAdapterPort) {
        return new CreateReservationUseCase(saveReservationAdapterPort);
    }

}