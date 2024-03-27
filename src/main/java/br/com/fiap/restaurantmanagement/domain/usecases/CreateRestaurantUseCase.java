package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.RestaurantAdapterPort;
import org.springframework.stereotype.Component;

/**
 * This class represents the use case for creating a restaurant
 */
@Component
public class CreateRestaurantUseCase implements CreateRestaurantUseCasePort {

    private final RestaurantAdapterPort restaurantAdapterPort;

    public CreateRestaurantUseCase(RestaurantAdapterPort restaurantAdapterPort) {

        this.restaurantAdapterPort = restaurantAdapterPort;

    }

    @Override
    public Restaurant execute(Restaurant restaurant) {

      return this.restaurantAdapterPort.save(restaurant);

    }

}