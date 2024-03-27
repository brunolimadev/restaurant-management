package br.com.fiap.restaurantmanagement.config;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.*;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.*;
import br.com.fiap.restaurantmanagement.domain.usecases.*;
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
    public CreateReservationUseCasePort createReservationUseCase(
            ReservationAdapterPort reservationSaveAdapterPort,
            TableAdapterPort tableAdapterPort,
            UserAdapterPort userAdapterPort) {

        return new CreateReservationUseCase(
                reservationSaveAdapterPort,
                tableAdapterPort,
                userAdapterPort);

    }

    @Bean
    public FindReservationsUseCasePort findReservationsUseCase(ReservationAdapterPort reservationAdapterPort) {

        return  new FindReservationsUseCase(reservationAdapterPort);

    }

    @Bean
    public DeleteReservationUseCasePort deleteReservationUseCase(ReservationAdapterPort reservationAdapterPort) {

        return new DeleteReservationUseCase(reservationAdapterPort);

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