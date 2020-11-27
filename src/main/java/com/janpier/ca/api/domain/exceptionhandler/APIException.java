package com.janpier.ca.api.domain.exceptionhandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.janpier.ca.api.domain.exception.ExceptionBusiness;
import com.janpier.ca.api.domain.exception.InsufficientFundsException;

/**
 * Classe controladora para tratamento de exceções da API.
 * @author Janpier
 *
 */
@ControllerAdvice
public class APIException extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler(InsufficientFundsException.class)
  public ResponseEntity<Object> handleInsufficentFundsException (ExceptionBusiness ex, WebRequest request) {
    
    HttpStatus status = HttpStatus.UNAUTHORIZED;
    
    Problem problem = new Problem(status.value(), ex.getMessage(), OffsetDateTime.now());
    return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }
  
  @ExceptionHandler(ExceptionBusiness.class)
  public ResponseEntity<Object> handleExceptionBusiness (ExceptionBusiness ex, WebRequest request) {
    
    HttpStatus status =  HttpStatus.BAD_REQUEST;
    
    Problem problem = new Problem(status.value(), ex.getMessage(), OffsetDateTime.now());
    return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);    
  }
}
