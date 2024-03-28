package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class represents the create restaurant request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantRequest {

    @Schema(description = "name", example = "Dona Chica")
    private String name;

    @Schema(description = "food type", example = "BRAZILIAN")
    private String foodType;

    private List<CreateRestaurantAddressRequest> address;

    private List<CreateRestaurantTableRequest> tables;

    private List<CreateRestaurantOpeningHoursRequest> openingHours;

    public Restaurant toDomain() throws FoodTypeNotFoundException {
        return new Restaurant(
                this.name,
                this.address.stream().map(CreateRestaurantAddressRequest::toDomain).toList(),
                TypesOfFood.findByName(this.foodType),
                this.openingHours.stream().map(CreateRestaurantOpeningHoursRequest::toDomain).toList(),
                this.tables.stream().map(CreateRestaurantTableRequest::toDomain).toList()
        );
    }
}
