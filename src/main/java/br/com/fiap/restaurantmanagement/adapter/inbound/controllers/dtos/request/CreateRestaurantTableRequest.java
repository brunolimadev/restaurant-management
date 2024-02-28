package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantTableRequest {

    public String description;

    public Integer numberOfSeats;

    public Table toDomain() {

        return new Table(
                this.description,
                this.numberOfSeats);
    }
}
