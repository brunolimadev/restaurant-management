package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.RestaurantRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.*;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class represents the restaurant save adapter
 */
@Component
public class RestaurantSaveAdapter implements SaveAdapterPort<Restaurant> {

    private final RestaurantRepository restaurantRepository;
    private final FoodTypeSaveAdapter foodTypeSaveAdapter;
    private final AddressSaveAdapter addressSaveAdapter;
    private final TableSaveAdapter tableSaveAdapter;
    private final OpeningHourSaveAdapter openingHourSaveAdapter;


    public RestaurantSaveAdapter(RestaurantRepository restaurantRepository, FoodTypeSaveAdapter foodTypeSaveAdapter, AddressSaveAdapter addressSaveAdapter, TableSaveAdapter tableSaveAdapter, OpeningHourSaveAdapter openingHourSaveAdapter) {
        this.restaurantRepository = restaurantRepository;
        this.foodTypeSaveAdapter = foodTypeSaveAdapter;
        this.addressSaveAdapter = addressSaveAdapter;
        this.tableSaveAdapter = tableSaveAdapter;
        this.openingHourSaveAdapter = openingHourSaveAdapter;
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {

        RestaurantModel restaurantModel = new RestaurantModel();

        try{
            FoodTypeModel foodTypeModel = foodTypeSaveAdapter.save(FoodTypeModel.fromDomain(restaurant.getTypeOfFood(), null));
            restaurantModel.setFoodType(foodTypeModel);

            List<AddressModel> addressModels = addressSaveAdapter.save(AddressModel.fromDomain(restaurant));

            for (AddressModel addressModel : addressModels) {
                addressModel.setRestaurant(restaurantModel);
            }

            List<TableModel> tableModels = tableSaveAdapter.save(TableModel.fromDomain(restaurant));

            for (TableModel tableModel : tableModels) {
                tableModel.setRestaurant(restaurantModel);
            }

            List<OpeningHourModel> openingHourModels = openingHourSaveAdapter.save(OpeningHourModel.fromDomain(restaurant));

            for (OpeningHourModel openingHourModel : openingHourModels) {
                openingHourModel.setRestaurant(restaurantModel);
            }

            restaurantModel = restaurantRepository.save(RestaurantModel.fromDomain(restaurantModel, restaurant, foodTypeModel.getId()));

            return restaurantModel.toDomain();
        }catch (Exception e){
            throw new RuntimeException("Error to save restaurant", e);
        }
    }


}
