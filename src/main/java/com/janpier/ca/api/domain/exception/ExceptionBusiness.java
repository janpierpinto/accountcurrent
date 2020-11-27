package com.janpier.ca.api.domain.exception;

/**
 * Responsável por personalizar as exceções de erro operacional do usuário.
 * @author Janpier
 *
 */
public class ExceptionBusiness extends RuntimeException {

  private static final long serialVersionUID = 1L;
  
  public ExceptionBusiness (String message) {
    super(message);
  }
}
