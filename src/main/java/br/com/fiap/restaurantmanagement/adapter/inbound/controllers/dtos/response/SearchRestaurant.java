package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchRestaurant {

    public Long id;

    public String name;

    public String foodType;

    public String location;

}
