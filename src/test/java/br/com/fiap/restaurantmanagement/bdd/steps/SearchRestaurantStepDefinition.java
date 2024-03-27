package br.com.fiap.restaurantmanagement.bdd.steps;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateRestaurantResponse;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.SearchRestaurantResponse;
import br.com.fiap.restaurantmanagement.utils.RestaurantHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class SearchRestaurantStepDefinition {

    private Response response;

    private SearchRestaurantResponse searchRestaurantResponse;

    private final String ENDPOINT_API_RESTAURANT = "http://localhost:8080/restaurant-management/api/restaurant";

    @When("I search for restaurants by name")
    public void i_search_for_restaurants_by_name() {

        Optional<String> name = Optional.of("Dona Chica");

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("name", name.get())
                .when()
                .get(ENDPOINT_API_RESTAURANT);
    }

    @Then("I should see status code 200")
    public void i_should_see_status_code_200() {
        response.then().statusCode(200);
    }
}
