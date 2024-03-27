package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.OpeningHourRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.OpeningHourModel;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class represents the opening hour save adapter
 */
@Component
public class OpeningHourAdapter implements SaveAdapterPort<List<OpeningHourModel>> {

    private final OpeningHourRepository openingHourRepository;

    public OpeningHourAdapter(OpeningHourRepository openingHourRepository) {
        this.openingHourRepository = openingHourRepository;
    }

    @Override
    public List<OpeningHourModel> save(List<OpeningHourModel> entity) {
        return openingHourRepository.saveAll(entity);
    }
}

