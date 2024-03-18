package br.com.fiap.restaurantmanagement.domain.ports.inbound;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;

/**
 * This interface represents the port for creating a restaurant
 */
public interface CreateRestaurantUseCasePort {

    Restaurant execute(Restaurant restaurant);

}