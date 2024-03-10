package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateRestaurantRequest;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateRestaurantResponse;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final CreateRestaurantUseCasePort createRestaurantUseCasePort;

    public RestaurantController(CreateRestaurantUseCasePort createRestaurantUseCasePort) {
        this.createRestaurantUseCasePort = createRestaurantUseCasePort;
    }

    @PostMapping
    public ResponseEntity<CreateRestaurantResponse> createRestaurant(@RequestBody CreateRestaurantRequest createRestaurantRequest) {

        Restaurant restaurant = createRestaurantUseCasePort.execute(createRestaurantRequest.toDomain());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                CreateRestaurantResponse.builder()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .foodType(restaurant.getTypeOfFood().name())
                        .build()
        );
    }

}
