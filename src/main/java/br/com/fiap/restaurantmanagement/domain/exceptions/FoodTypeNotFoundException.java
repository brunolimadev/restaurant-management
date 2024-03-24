package br.com.fiap.restaurantmanagement.domain.exceptions;

public class FoodTypeNotFoundException extends Exception{
    public FoodTypeNotFoundException(String message) {
        super(message);
    }
}
