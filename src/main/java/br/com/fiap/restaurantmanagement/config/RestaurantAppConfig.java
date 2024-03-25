package br.com.fiap.restaurantmanagement.config;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import br.com.fiap.restaurantmanagement.domain.entities.Reservation;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateCommentUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateReservationUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.SearchRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateCommentUseCase;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SearchAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateReservationUseCase;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateRestaurantUseCase;
import br.com.fiap.restaurantmanagement.domain.usecases.SearchRestaurantUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
    public CreateReservationUseCasePort createReservationUseCase(SaveAdapterPort<Reservation> reservationSaveAdapterPort) {
        return new CreateReservationUseCase(reservationSaveAdapterPort);
    }
    
    @Bean
    public CreateCommentUseCasePort createCommentUseCase(SaveAdapterPort<Comment> commentSaveAdapterPort){
        return new CreateCommentUseCase(commentSaveAdapterPort);
    }


    @Bean
    public SearchRestaurantUseCasePort searchRestaurantUseCase(SearchAdapterPort<List<Restaurant>> searchRestaurantUseCasePort) {
        return new SearchRestaurantUseCase(searchRestaurantUseCasePort);
    }

}