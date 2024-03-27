package br.com.fiap.restaurantmanagement.domain.usecases;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.SearchRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.RestaurantAdapterPort;

import java.util.List;
import java.util.Optional;

public class SearchRestaurantUseCase implements SearchRestaurantUseCasePort {

    private final RestaurantAdapterPort restaurantAdapterPort;

    public SearchRestaurantUseCase(RestaurantAdapterPort restaurantAdapterPort) {

        this.restaurantAdapterPort = restaurantAdapterPort;

    }

    public List<Restaurant> execute(
            Optional<String> name,
            Optional<String> location,
            Optional<String> foodType
    ) throws FoodTypeNotFoundException {

        return restaurantAdapterPort.search(name, location, foodType);

    }

}