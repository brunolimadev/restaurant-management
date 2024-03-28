package br.com.fiap.restaurantmanagement.domain.ports.inbound;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;

/**
 * This interface represents the gateway to searching for restaurant reservations
 */
public interface CreateRestaurantUseCasePort {

    Restaurant execute(Restaurant restaurant);

}