package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.RestaurantRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.*;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
import br.com.fiap.restaurantmanagement.domain.exceptions.TransactionException;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.RestaurantAdapterPort;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.TableAdapterPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RestaurantAdapter implements RestaurantAdapterPort {

  private final EntityManager entityManager;
  private final RestaurantRepository restaurantRepository;
  private final FoodTypeAdapter foodTypeAdapter;
  private final AddressAdapter addressAdapter;
  private final TableAdapterPort tableAdapterPort;
  private final OpeningHourAdapter openingHourAdapter;

  public RestaurantAdapter(
          EntityManager entityManager,
          RestaurantRepository restaurantRepository,
          FoodTypeAdapter foodTypeAdapter,
          AddressAdapter addressAdapter,
          TableAdapterPort tableAdapterPort,
          OpeningHourAdapter openingHourAdapter) {

    this.entityManager = entityManager;
    this.restaurantRepository = restaurantRepository;
    this.foodTypeAdapter = foodTypeAdapter;
    this.addressAdapter = addressAdapter;
    this.tableAdapterPort = tableAdapterPort;
    this.openingHourAdapter = openingHourAdapter;

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

  @Override
  public Restaurant save(Restaurant restaurant) {

    RestaurantModel restaurantModel = new RestaurantModel();

    try{

      FoodTypeModel foodTypeModel = foodTypeAdapter.save(FoodTypeModel.fromDomain(restaurant.getTypeOfFood(), null));
      restaurantModel.setFoodType(foodTypeModel);

      List<AddressModel> addressModels = addressAdapter.save(AddressModel.fromDomain(restaurant));

      for (AddressModel addressModel : addressModels) {
        addressModel.setRestaurant(restaurantModel);
      }

      List<TableModel> tableModels = tableAdapterPort.save(TableModel.fromDomain(restaurant));

      for (TableModel tableModel : tableModels) {
        tableModel.setRestaurant(restaurantModel);
      }

      List<OpeningHourModel> openingHourModels = openingHourAdapter.save(OpeningHourModel.fromDomain(restaurant));

      for (OpeningHourModel openingHourModel : openingHourModels) {
        openingHourModel.setRestaurant(restaurantModel);
      }

      restaurantModel = restaurantRepository.save(RestaurantModel.fromDomain(restaurantModel, restaurant, foodTypeModel.getId()));

      return restaurantModel.toDomain();

    } catch (Exception exception){

      throw new TransactionException("Error to save restaurant", exception);

    }

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