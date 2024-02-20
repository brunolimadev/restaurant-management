package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @PostMapping
    public ResponseEntity<?> createRestaurant() {
        return null;
    }

}
