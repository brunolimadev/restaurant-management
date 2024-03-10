package br.com.fiap.restaurantmanagement.config;

import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateReservationUseCase;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateRestaurantUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantAppConfig {

    @Bean
    public CreateRestaurantUseCasePort createRestaurantUseCase(SaveAdapterPort<Restaurant> saveRestaurant) {
        return new CreateRestaurantUseCase(saveRestaurant);
    }

    @Bean
    public CreateReservationUseCasePort createReservationUseCase(SaveAdapterPort<Reservation> saveReservation) {
        return new CreateReservationUseCase(saveReservation);
    }

}