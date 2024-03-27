package br.com.fiap.restaurantmanagement.domain.exceptions;

public class OccupiedTablesToReservationException extends RuntimeException{

  public OccupiedTablesToReservationException(String message) {

    super(message);

  }

}