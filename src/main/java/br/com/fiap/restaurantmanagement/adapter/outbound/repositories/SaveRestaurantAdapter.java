package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveRestaurantAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class SaveRestaurantAdapter implements SaveRestaurantAdapterPort {

    private final RestaurantRepository restaurantRepository;

    public SaveRestaurantAdapter(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        // TODO: Implement this method
        return null;
    }
}
