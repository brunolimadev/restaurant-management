package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents the table repository
 */
@Repository
public interface TableRepository extends JpaRepository<TableModel, Long> {

    @Query(value = "SELECT * FROM restaurant_table tables WHERE tables.restaurant_id = :restaurantId", nativeQuery = true)
    List<TableModel> findTablesByRestaurant(@Param("restaurantId") Long restaurantId);

    @Query(value = "SELECT * FROM restaurant_table " +
            "WHERE NOT EXISTS " +
            "(SELECT * FROM reservation WHERE restaurant_table.id = reservation.restaraunt_table_id) " +
            "and restaurant_table.restaurant_id = :restaurantId", nativeQuery = true)
    List<TableModel> findTablesNotReservation(@Param("restaurantId") Long restaurantId);

}