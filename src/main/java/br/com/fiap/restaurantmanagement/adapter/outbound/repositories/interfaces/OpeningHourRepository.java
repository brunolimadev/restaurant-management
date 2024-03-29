package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.OpeningHourModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents the opening hour repository
 */
@Repository
public interface OpeningHourRepository extends JpaRepository<OpeningHourModel, Long> {
}
