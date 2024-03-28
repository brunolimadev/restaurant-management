package br.com.fiap.restaurantmanagement.config;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.*;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.*;
import br.com.fiap.restaurantmanagement.domain.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class represents the application configuration
 */
@Configuration
public class RestaurantAppConfig {

    @Bean
    public CreateRestaurantUseCasePort createRestaurantUseCase(RestaurantAdapterPort restaurantAdapterPort) {

        return new CreateRestaurantUseCase(restaurantAdapterPort);

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
    public SearchRestaurantUseCasePort searchRestaurantUseCase(RestaurantAdapterPort restaurantAdapterPort) {

        return new SearchRestaurantUseCase(restaurantAdapterPort);

    }

}