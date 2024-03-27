package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.SearchRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.utils.RestaurantHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class RestaurantControllerTest {

    @Mock
    private CreateRestaurantUseCasePort createRestaurantUseCasePort;

    @Mock
    private SearchRestaurantUseCasePort searchRestaurantUseCasePort;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldPermitCreateRestaurant() throws Exception {
        var restaurant = RestaurantHelper.createRestaurant();
        Mockito.when(createRestaurantUseCasePort.execute(any(Restaurant.class))).thenReturn(restaurant);

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(RestaurantHelper.createRestaurantRequest())))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldPermitSearchRestaurant() throws Exception {
        // arrange
        var restaurant = RestaurantHelper.createRestaurant();
        List<Restaurant> restaurants = List.of(restaurant);
        Optional<String> location = Optional.of("São Paulo");
        Optional<String> name = Optional.of("Japa");
        Optional<String> foodType = Optional.of("Japanese");

        Mockito.when(searchRestaurantUseCasePort.execute(location, name, foodType)).thenReturn(restaurants);

        mockMvc.perform(MockMvcRequestBuilders.get("/restaurant")
                        .queryParam("location", "São Paulo")
                        .queryParam("name", "Japa")
                        .queryParam("foodType", "Japanese")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object restaurant) {
        try{
            return new ObjectMapper().writeValueAsString(restaurant);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
