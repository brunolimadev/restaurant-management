package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.FoodTypeRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.FoodTypeModel;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SaveFoodTypeAdapter {

    private final FoodTypeRepository foodTypeRepository;

    public SaveFoodTypeAdapter(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }

    public FoodTypeModel save(TypesOfFood foodTypeModel) {

        Optional<FoodTypeModel> result = foodTypeRepository.findFirstByName(foodTypeModel);

        if (result.isEmpty()) {
            return foodTypeRepository.save(FoodTypeModel.fromDomain(foodTypeModel, null));
        }else{
            return result.get();
        }

    }

}
