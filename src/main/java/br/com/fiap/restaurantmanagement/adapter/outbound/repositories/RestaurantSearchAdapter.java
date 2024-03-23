package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.AddressRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.AddressModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.FoodTypeModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SearchAdapterPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RestaurantSearchAdapter implements SearchAdapterPort<List<Restaurant>> {

    private final EntityManager entityManager;

    private final AddressRepository addressRepository;

    public RestaurantSearchAdapter(EntityManager entityManager, AddressRepository addressRepository) {
        this.entityManager = entityManager;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Restaurant> search(Optional<String>... args) throws FoodTypeNotFoundException {

        String name = args[0].orElse("");
        String location = args[1].orElse("");
        String foodType = args[2].isPresent() ? TypesOfFood.findByName(args[2].orElseThrow()).name() : "";

        List<Restaurant> restaurants = new ArrayList<>();

        var result = this.findAddresses(location, name, foodType);

        for (var addressModel : result) {
            restaurants.add(addressModel.toDomain());
        }

        return restaurants;

    }

    private List<AddressModel> findAddresses(String location, String name, String foodType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AddressModel> criteriaQuery = criteriaBuilder.createQuery(AddressModel.class);
        Root<AddressModel> addressRoot = criteriaQuery.from(AddressModel.class);

        Join<AddressModel, RestaurantModel> restaurantJoin = addressRoot.join("restaurant");
        Join<RestaurantModel, FoodTypeModel> foodTypeJoin = restaurantJoin.join("foodType");

        List<Predicate> predicates = new ArrayList<>();

        if (!location.isEmpty()) {
            predicates.add(criteriaBuilder.like(addressRoot.get("street"), "%" + location + "%"));
        }

        if(!name.isEmpty()){
            predicates.add(criteriaBuilder.like(restaurantJoin.get("name"), "%" + name + "%"));
        }

        if (!foodType.isEmpty()){
            predicates.add(criteriaBuilder.equal(foodTypeJoin.get("name"), foodType));

        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
