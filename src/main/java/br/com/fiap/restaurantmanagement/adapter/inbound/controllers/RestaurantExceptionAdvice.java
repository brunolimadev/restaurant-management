package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.ErrorResponse;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestaurantExceptionAdvice {

    @ExceptionHandler(FoodTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFoodTypeNotFoundException(FoodTypeNotFoundException e) {
        return ResponseEntity.badRequest().body(ErrorResponse.builder().title("Food type not found").message(e.getMessage()).build());
    }
}
