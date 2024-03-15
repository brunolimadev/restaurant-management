package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the create reservation response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantResponse {

    public Long id;

    public String name;

    public String foodType;

}
