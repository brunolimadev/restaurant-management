package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "id", example = "100")
    public Long id;

    @Schema(description = "name", example = "Dona Chica")
    public String name;

    @Schema(description = "food type", example = "BRAZILIAN")
    public String foodType;

}
