package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantRequest {

    public String name;

    public String foodType;

    public List<CreateRestaurantAddressRequest> address;

    public List<CreateRestaurantTableRequest> tables;

    public List<CreateRestaurantOpeningHoursRequest> openingHours;

    public Restaurant toDomain() {
        return new Restaurant(
                this.name,
                this.address.stream().map(CreateRestaurantAddressRequest::toDomain).toList(),
                TypesOfFood.findByName(this.foodType),
                this.openingHours.stream().map(CreateRestaurantOpeningHoursRequest::toDomain).toList(),
                this.tables.stream().map(CreateRestaurantTableRequest::toDomain).toList()
        );
    }
}
