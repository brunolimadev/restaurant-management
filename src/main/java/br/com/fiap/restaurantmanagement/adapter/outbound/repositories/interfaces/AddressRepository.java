package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.AddressModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the address repository
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {

    public Optional<AddressModel> findByStreetAndNumberAndCity(String street, String number, String city);

    @Query("select a from AddressModel a " +
            "inner join RestaurantModel r on r.id = a.restaurant.id " +
            "inner join FoodTypeModel ft on r.foodType.id = ft.id " +
            "where a.street like %:location% or r.name like %:name% or ft.name = :foodType")
    public List<AddressModel> findRestaurantsByNameOrFoodTypeOrLocation(@Param("name") String name, @Param("location") String location, @Param("foodType") TypesOfFood foodType);
}
