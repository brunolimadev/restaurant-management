package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * This interface represents the restaurant repository
 */
@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantModel, Long> {
}
