package br.com.fiap.restaurantmanagement.domain.exceptions;

public class TransactionException extends RuntimeException{

  public TransactionException(String message) {

    super(message);

  }

}