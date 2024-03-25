package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents the reservation repository
 */
@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {

    @Query(value = "SELECT * FROM reservation reservations WHERE reservations.restaurant_id = :restaurantId", nativeQuery = true)
    List<ReservationModel> findReservationsByRestaurant(@Param("restaurantId") Long restaurantId);

}