package com.janpier.ca.api.domain.exception;

/**
 * Responsável por tratar uma exceções em transações da conta corrente.
 * @author Janpier
 *
 */
public class InsufficientFundsException extends ExceptionBusiness {
  
  private static final long serialVersionUID = 1L;
  
  public InsufficientFundsException (String message) {
    super(message);
  }

}
