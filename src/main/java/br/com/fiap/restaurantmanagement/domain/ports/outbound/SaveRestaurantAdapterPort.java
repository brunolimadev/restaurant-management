package br.com.fiap.restaurantmanagement.domain.ports.outbound;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;

public interface SaveRestaurantAdapterPort<T> {

    public T saveRestaurant(Restaurant restaurant);

}
