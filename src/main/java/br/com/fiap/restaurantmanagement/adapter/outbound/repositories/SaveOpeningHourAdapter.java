package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.OpeningHourRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.OpeningHourModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import br.com.fiap.restaurantmanagement.domain.entities.OpeningHours;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SaveOpeningHourAdapter {

    private final OpeningHourRepository openingHourRepository;

    public SaveOpeningHourAdapter(OpeningHourRepository openingHourRepository) {
        this.openingHourRepository = openingHourRepository;
    }

    public List<OpeningHourModel> save(Restaurant restaurant) {

        List<OpeningHourModel> openingHourModels = OpeningHourModel.fromDomain(restaurant);

        return openingHourRepository.saveAll(openingHourModels);


    }
}

