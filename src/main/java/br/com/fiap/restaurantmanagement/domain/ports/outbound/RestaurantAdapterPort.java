package br.com.fiap.restaurantmanagement.domain.ports.outbound;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RestaurantAdapterPort extends SaveAdapterPort<Restaurant> {

  List<Restaurant> search(Optional<String>... args) throws FoodTypeNotFoundException;

}