package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.AddressRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.AddressModel;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaveAddressAdapter {
    private final AddressRepository addressRepository;

    public SaveAddressAdapter(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressModel> save(Restaurant restaurant) {

        List<AddressModel> addressModels = AddressModel.fromDomain(restaurant);

        return addressRepository.saveAll(addressModels);
    }
}
