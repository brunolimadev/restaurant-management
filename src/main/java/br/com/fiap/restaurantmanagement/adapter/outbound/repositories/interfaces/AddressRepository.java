package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents the address repository
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {

}
