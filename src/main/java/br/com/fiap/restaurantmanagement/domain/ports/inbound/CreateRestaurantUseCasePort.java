package br.com.fiap.restaurantmanagement.domain.ports.inbound;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;

public interface CreateRestaurantUseCasePort {
    public Restaurant execute(Restaurant restaurant);
}
