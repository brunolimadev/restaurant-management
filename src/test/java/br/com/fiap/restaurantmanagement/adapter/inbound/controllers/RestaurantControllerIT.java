package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.utils.RestaurantHelper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class RestaurantControllerIT {

    @LocalServerPort
    private int serverPort;

    @BeforeEach
    public void setup(){
        RestAssured.port = serverPort;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class RegisterRestaurant{

        @Test
        void shouldRegisterRestaurant(){
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(RestaurantHelper.createRestaurantRequestJson())
            .when()
                    .post("/restaurant-management/api/restaurant")
            .then()
                    .statusCode(HttpStatus.CREATED.value());
        }

    }

}
