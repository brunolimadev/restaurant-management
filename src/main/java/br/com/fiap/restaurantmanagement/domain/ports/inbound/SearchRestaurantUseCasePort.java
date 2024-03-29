package br.com.fiap.restaurantmanagement.domain.ports.inbound;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the gateway to searching for restaurants
 */
public interface SearchRestaurantUseCasePort {
    List<Restaurant> execute(
            Optional<String> name,
            Optional<String> location,
            Optional<String> foodType
    ) throws FoodTypeNotFoundException;
}
