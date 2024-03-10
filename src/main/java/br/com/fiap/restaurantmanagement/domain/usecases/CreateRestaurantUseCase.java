package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;

public class CreateRestaurantUseCase implements CreateRestaurantUseCasePort {

    private SaveAdapterPort<Restaurant> saveAdapterPort;

    public CreateRestaurantUseCase(SaveAdapterPort saveAdapterPort) {
        this.saveAdapterPort = saveAdapterPort;
    }

    @Override
    public Restaurant execute(Restaurant restaurant) {
      var restaurantResult = this.saveAdapterPort.save(restaurant);

      return restaurantResult;
    }

}
