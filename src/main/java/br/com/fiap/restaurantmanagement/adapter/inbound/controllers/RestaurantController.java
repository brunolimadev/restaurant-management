package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateRestaurantRequest;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateRestaurantResponse;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.SearchRestaurant;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.SearchRestaurantResponse;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.SearchRestaurantUseCasePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the restaurant controller
 */
@RestController
@RequestMapping("/restaurant")
@Tag(name = "Restaurant Controller",
     description = "Restaurant owners will register their establishments in this option, informing the name, location, type of cuisine, opening hours and capacity.")
public class RestaurantController {

    private final CreateRestaurantUseCasePort createRestaurantUseCasePort;

    private final SearchRestaurantUseCasePort searchRestaurantUseCasePort;

    public RestaurantController(
            CreateRestaurantUseCasePort createRestaurantUseCasePort,
            SearchRestaurantUseCasePort searchRestaurantUseCasePort) {

        this.createRestaurantUseCasePort = createRestaurantUseCasePort;
        this.searchRestaurantUseCasePort = searchRestaurantUseCasePort;

    }

    @Operation(summary = "Search Restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a list of restaurants by parameters"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping
    public ResponseEntity<SearchRestaurantResponse> getRestaurant(
            @Parameter(description = "name", example = "Dona Chica") @RequestParam("name") Optional<String> name,
            @Parameter(description = "location", example = "São Paulo") @RequestParam("location") Optional<String> location,
            @Parameter(description = "type of food", example = "BRAZILIAN") @RequestParam("typeOfFood") Optional<String> typeOfFood
    ) throws FoodTypeNotFoundException {

        SearchRestaurantResponse response = new SearchRestaurantResponse();

        List<Restaurant> restaurants = searchRestaurantUseCasePort.execute(name, location, typeOfFood);

        for (Restaurant restaurant : restaurants) {

            response.getRestaurants().add(new SearchRestaurant(
                    restaurant.getId(),
                    restaurant.getName(),
                    restaurant.getTypeOfFood().name(),
                    restaurant.getAddress().get(0).getStreet()
            ));
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Register Restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<CreateRestaurantResponse> createRestaurant(@RequestBody CreateRestaurantRequest createRestaurantRequest) throws FoodTypeNotFoundException {

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
