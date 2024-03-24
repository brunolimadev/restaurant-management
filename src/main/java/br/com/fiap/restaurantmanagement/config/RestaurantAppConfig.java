package br.com.fiap.restaurantmanagement.config;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.ReservationRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.TableRepository;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateReservationUseCase;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateRestaurantUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class represents the application configuration
 */
@Configuration
public class RestaurantAppConfig {

    @Bean
    public CreateRestaurantUseCasePort createRestaurantUseCase(SaveAdapterPort<Restaurant> restaurantSaveAdapterPort) {
        return new CreateRestaurantUseCase(restaurantSaveAdapterPort);
    }

    @Bean
    public CreateReservationUseCasePort createReservationUseCase(SaveAdapterPort<Reservation> reservationSaveAdapterPort,
                                                                 ReservationRepository reservationRepository,
                                                                 TableRepository tableRepository) {

        return new CreateReservationUseCase(reservationSaveAdapterPort, reservationRepository, tableRepository);

    }

}