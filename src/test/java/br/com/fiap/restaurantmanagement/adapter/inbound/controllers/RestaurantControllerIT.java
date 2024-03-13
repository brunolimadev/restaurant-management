package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

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
                    .body("{\n" +
                            "    \"name\": \"Dona Chica\",\n" +
                            "    \"foodType\": \"BRAZILIAN\",\n" +
                            "    \"address\": [{\n" +
                            "        \"street\": \"Av. Paulista\",\n" +
                            "        \"number\": \"1010\",\n" +
                            "        \"complement\": \"\",\n" +
                            "        \"neighborhood\": \"centro\",\n" +
                            "        \"city\": \"São Paulo\",\n" +
                            "        \"state\": \"SP\",\n" +
                            "        \"zipCode\": \"10001-005\",\n" +
                            "        \"country\": \"Brazil\"\n" +
                            "    }],\n" +
                            "    \"tables\": [\n" +
                            "        {\n" +
                            "            \"description\": \"1\",\n" +
                            "            \"numberOfSeats\": 10\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"description\": \"2\",\n" +
                            "            \"numberOfSeats\": 4\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"description\": \"3\",\n" +
                            "            \"numberOfSeats\": 6\n" +
                            "        }\n" +
                            "    ],\n" +
                            "    \"openingHours\": [\n" +
                            "        {\n" +
                            "            \"dayOfWeek\": \"SATURDAY\",\n" +
                            "            \"openingTime\": \"10:00\",\n" +
                            "            \"closingTime\": \"22:00\"\n" +
                            "\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"dayOfWeek\": \"SUNDAY\",\n" +
                            "            \"openingTime\": \"10:00\",\n" +
                            "            \"closingTime\": \"22:00\"\n" +
                            "        }\n" +
                            "    ]\n" +
                            "    \n" +
                            "}")
            .when()
                    .post("/restaurant-management/api/restaurant")
            .then()
                    .statusCode(HttpStatus.CREATED.value());
        }

    }

}
