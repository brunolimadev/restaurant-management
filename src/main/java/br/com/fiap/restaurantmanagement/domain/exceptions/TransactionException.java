package br.com.fiap.restaurantmanagement.domain.exceptions;

public class TransactionException extends RuntimeException{

  public TransactionException(String message) {

    super(message);

  }

  public TransactionException(String message, Exception exception) {

    super(message, exception);

  }

}