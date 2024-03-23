package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.SearchRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SearchAdapterPort;

import java.util.List;
import java.util.Optional;

public class SearchRestaurantUseCase implements SearchRestaurantUseCasePort {

    private final SearchAdapterPort<List<Restaurant>> restaurantsSaveAdapterPort;

    public SearchRestaurantUseCase(SearchAdapterPort<List<Restaurant>> restaurantsSaveAdapterPort) {
        this.restaurantsSaveAdapterPort = restaurantsSaveAdapterPort;
    }

    public List<Restaurant> execute(
            Optional<String> name,
            Optional<String> location,
            Optional<String> foodType
    ) throws FoodTypeNotFoundException {
        return this.restaurantsSaveAdapterPort.search(name, location, foodType);
    }
}
