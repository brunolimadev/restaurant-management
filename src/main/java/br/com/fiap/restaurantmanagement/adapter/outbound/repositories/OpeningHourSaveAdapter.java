package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.OpeningHourRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.OpeningHourModel;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpeningHourSaveAdapter implements SaveAdapterPort<List<OpeningHourModel>> {

    private final OpeningHourRepository openingHourRepository;

    public OpeningHourSaveAdapter(OpeningHourRepository openingHourRepository) {
        this.openingHourRepository = openingHourRepository;
    }

    @Override
    public List<OpeningHourModel> save(List<OpeningHourModel> entity) {
        return openingHourRepository.saveAll(entity);
    }
}

