package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveRestaurantAdapterPort;

public class CreateRestaurantUseCase implements CreateRestaurantUseCasePort {

    private SaveRestaurantAdapterPort<Restaurant> saveRestaurantAdapterPort;

    public CreateRestaurantUseCase(SaveRestaurantAdapterPort saveRestaurantAdapterPort) {
        this.saveRestaurantAdapterPort = saveRestaurantAdapterPort;
    }

    @Override
    public Restaurant execute(Restaurant restaurant) {
      var restaurantResult = this.saveRestaurantAdapterPort.saveRestaurant(restaurant);

      return restaurantResult;
    }

}
