package br.com.fiap.restaurantmanagement.bdd.steps;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateRestaurantResponse;
import br.com.fiap.restaurantmanagement.utils.RestaurantHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class CreateRestaurantStepDefinition {

    private Response response;

    private CreateRestaurantResponse createRestaurantResponse;

    private final String ENDPOINT_API_RESTAURANT = "http://localhost:8080/restaurant-management/api/restaurant";

    @When("I register a new restaurant")
    public void i_register_a_restaurant() {

        var request = RestaurantHelper.createRestaurantRequestJson();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(ENDPOINT_API_RESTAURANT);
    }

    @Then("I should see status code 201")
    public void i_should_see_status_code_201() {
        response.then().statusCode(201);
    }
}
