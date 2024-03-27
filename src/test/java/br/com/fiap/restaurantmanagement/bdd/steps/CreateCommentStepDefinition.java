package br.com.fiap.restaurantmanagement.bdd.steps;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateCommentResponse;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateRestaurantResponse;
import br.com.fiap.restaurantmanagement.utils.CommentHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class CreateCommentStepDefinition {

    private Response response;

    private CreateCommentResponse createCommentResponse;

    private final String ENDPOINT_API_RESTAURANT_COMMENT = "http://localhost:8080/restaurant-management/api/comment";

    @When("I register a new restaurant comment")
    public void i_register_a_comment() {

        var request = CommentHelper.createCommentRequestJson();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(ENDPOINT_API_RESTAURANT_COMMENT);
    }

    @Then("I should see status code success 201")
    public void i_should_see_status_code_success_201() {
        response.then().statusCode(201);
    }
}
