package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.Address;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantAddressRequest {

    public String street;

    public String number;

    public String complement;

    public String neighborhood;

    public String city;

    public String state;

    public String zipCode;

    public String country;

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
