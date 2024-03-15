package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;

/**
 * This class represents the use case for creating a restaurant
 */
public class CreateRestaurantUseCase implements CreateRestaurantUseCasePort {

    private final SaveAdapterPort<Restaurant> restaurantSaveAdapterPort;

    public CreateRestaurantUseCase(SaveAdapterPort<Restaurant> restaurantSaveAdapterPort) {
        this.restaurantSaveAdapterPort = restaurantSaveAdapterPort;
    }

    @Override
    public Restaurant execute(Restaurant restaurant) {
      return this.restaurantSaveAdapterPort.save(restaurant);
    }

}