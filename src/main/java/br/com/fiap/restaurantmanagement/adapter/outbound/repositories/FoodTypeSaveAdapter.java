package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.FoodTypeRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.FoodTypeModel;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * This class represents the food type save adapter
 */
@Component
public class FoodTypeSaveAdapter implements SaveAdapterPort<FoodTypeModel> {

    private final FoodTypeRepository foodTypeRepository;

    public FoodTypeSaveAdapter(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }

    @Override
    public FoodTypeModel save(FoodTypeModel entity) {
        Optional<FoodTypeModel> result = foodTypeRepository.findFirstByName(entity.getName());

        if (result.isEmpty()) {
            return foodTypeRepository.save(FoodTypeModel.fromDomain(entity.getName(), null));
        }else{
            return result.get();
        }
    }
}
