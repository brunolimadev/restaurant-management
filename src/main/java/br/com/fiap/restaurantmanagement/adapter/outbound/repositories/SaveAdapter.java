package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.RestaurantRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.*;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SaveAdapter implements SaveAdapterPort<Restaurant> {

    private final RestaurantRepository restaurantRepository;
    private final SaveFoodTypeAdapter saveFoodTypeAdapter;
    private final SaveAddressAdapter saveAddressAdapter;
    private final SaveTableAdapter saveTableAdapter;
    private final SaveOpeningHourAdapter saveOpeningHourAdapter;


    public SaveAdapter(RestaurantRepository restaurantRepository, SaveFoodTypeAdapter saveFoodTypeAdapter, SaveAddressAdapter saveAddressAdapter, SaveTableAdapter saveTableAdapter, SaveOpeningHourAdapter saveOpeningHourAdapter) {
        this.restaurantRepository = restaurantRepository;
        this.saveFoodTypeAdapter = saveFoodTypeAdapter;
        this.saveAddressAdapter = saveAddressAdapter;
        this.saveTableAdapter = saveTableAdapter;
        this.saveOpeningHourAdapter = saveOpeningHourAdapter;
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {

        RestaurantModel restaurantModel = new RestaurantModel();

        try{
            FoodTypeModel foodTypeModel = saveFoodTypeAdapter.save(restaurant.getTypeOfFood());
            restaurantModel.setFoodType(foodTypeModel);

            List<AddressModel> addressModels = saveAddressAdapter.save(restaurant);

            for (AddressModel addressModel : addressModels) {
                addressModel.setRestaurant(restaurantModel);
            }

            List<TableModel> tableModels = saveTableAdapter.save(restaurant);

            for (TableModel tableModel : tableModels) {
                tableModel.setRestaurant(restaurantModel);
            }

            List<OpeningHourModel> openingHourModels = saveOpeningHourAdapter.save(restaurant);

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
