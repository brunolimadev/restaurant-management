package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the create restaurant address request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantAddressRequest {

    @Schema(description = "street", example = "Av. Paulista")
    private String street;

    @Schema(description = "number", example = "1010")
    private String number;

    @Schema(description = "complement", example = "after the metro station")
    private String complement;

    @Schema(description = "neighborhood", example = "center")
    private String neighborhood;

    @Schema(description = "city", example = "São Paulo")
    private String city;

    @Schema(description = "state", example = "SP")
    private String state;

    @Schema(description = "zip code", example = "10001-005")
    private String zipCode;

    @Schema(description = "country", example = "Brazil")
    private String country;

    public Address toDomain() {
        return new Address(
                this.street,
                this.number,
                this.complement,
                this.neighborhood,
                this.city,
                this.state,
                this.zipCode,
                this.country);
    }
}
