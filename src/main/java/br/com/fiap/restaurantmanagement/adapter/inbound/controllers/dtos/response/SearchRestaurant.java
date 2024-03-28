package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchRestaurant {

    @Schema(description = "Id restaurant", example = "1")
    public Long id;

    @Schema(description = "name", example = "Dona Chica")
    public String name;

    @Schema(description = "food type", example = "BRAZILIAN")
    public String foodType;

    @Schema(description = "location", example = "Av. Paulista")
    public String location;

}
