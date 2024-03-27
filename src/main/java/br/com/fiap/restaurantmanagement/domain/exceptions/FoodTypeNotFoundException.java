package br.com.fiap.restaurantmanagement.domain.exceptions;

public class FoodTypeNotFoundException extends RuntimeException{

    public FoodTypeNotFoundException(String message) {

        super(message);

    }

}