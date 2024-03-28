package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the create restaurant table request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantTableRequest {

    @Schema(description = "description", example = "1")
    private String description;

    @Schema(description = "numberOfSeats", example = "4")
    private Integer numberOfSeats;

    public Table toDomain() {

        return new Table(
                this.description,
                this.numberOfSeats);
    }
}
