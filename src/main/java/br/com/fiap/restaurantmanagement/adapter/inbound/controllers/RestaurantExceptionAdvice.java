package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.ErrorResponse;
import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;
import br.com.fiap.restaurantmanagement.domain.exceptions.OccupiedTablesToReservationException;
import br.com.fiap.restaurantmanagement.domain.exceptions.TransactionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestaurantExceptionAdvice {

    @ExceptionHandler(FoodTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFoodTypeNotFoundException(
            FoodTypeNotFoundException exception) {

        return ResponseEntity
                .badRequest()
                .body(
                        ErrorResponse
                                .builder()
                                .title("Food type not found")
                                .message(exception.getMessage())
                                .build());

    }

  @ExceptionHandler(OccupiedTablesToReservationException.class)
  public ResponseEntity<ErrorResponse> handleOccupiedTablesToReservationException(
          OccupiedTablesToReservationException exception) {

    return ResponseEntity
            .badRequest()
            .body(
                    ErrorResponse
                            .builder()
                            .title("Busy tables")
                            .message(exception.getMessage())
                            .build());

  }

  @ExceptionHandler(TransactionException.class)
  public ResponseEntity<ErrorResponse> handleTransactionException(
          TransactionException exception) {

    return ResponseEntity
            .internalServerError()
            .body(
                    ErrorResponse
                            .builder()
                            .title("Unprocessed transaction")
                            .message(exception.getMessage())
                            .build());

  }


}