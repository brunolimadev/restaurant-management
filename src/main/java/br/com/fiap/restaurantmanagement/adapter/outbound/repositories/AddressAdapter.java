package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.AddressRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.AddressModel;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class represents the address save adapter
 */
@Component
public class AddressAdapter implements SaveAdapterPort<List<AddressModel>> {

    private final AddressRepository addressRepository;

    public AddressAdapter(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressModel> save(List<AddressModel> entity) {
        return addressRepository.saveAll(entity);
    }
}
