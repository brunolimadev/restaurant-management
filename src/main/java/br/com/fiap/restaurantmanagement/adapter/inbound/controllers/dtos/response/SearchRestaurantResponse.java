package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchRestaurantResponse {

    List<SearchRestaurant> restaurants = new ArrayList<>();

}
