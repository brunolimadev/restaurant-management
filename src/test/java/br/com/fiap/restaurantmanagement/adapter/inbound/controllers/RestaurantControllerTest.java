package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateRestaurantUseCasePort;
import br.com.fiap.restaurantmanagement.utils.RestaurantHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {

    @Mock
    private CreateRestaurantUseCasePort createRestaurantUseCasePort;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldPermitCreateRestaurant() throws Exception {
        var restaurant = RestaurantHelper.createRestaurant();
        Mockito.when(createRestaurantUseCasePort.execute(any(Restaurant.class))).thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(RestaurantHelper.createRestaurantRequest())))
                .andExpect(status().isCreated());

    }

    public static String asJsonString(final Object restaurant) {
        try{
            return new ObjectMapper().writeValueAsString(restaurant);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
