package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.AddressRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.RestaurantRepository;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SearchAdapterPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RestaurantSearchAdapter implements SearchAdapterPort<List<Restaurant>> {

    private final AddressRepository addressRepository;
//    private final RestaurantRepository restaurantRepository;

//    public RestaurantSearchAdapter(RestaurantRepository restaurantRepository) {
//        this.restaurantRepository = restaurantRepository;
//    }

    public RestaurantSearchAdapter(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Restaurant> search(Optional<String>... args) throws FoodTypeNotFoundException {

            String name = args[0].orElse("");
            String location = args[1].orElse("");
            TypesOfFood foodType = args[2].isPresent() ? TypesOfFood.findByName(args[2].orElseThrow()) : null;

            List<Restaurant> restaurants = new ArrayList<>();

            var result = this.addressRepository.findRestaurantsByNameOrFoodTypeOrLocation(name, location, foodType);

            for (var addressModel : result) {
                restaurants.add(addressModel.toDomain());
            }

            return restaurants;

    }
}
