package com.janpier.ca.api.domain.exceptionhandler;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Classe que instância as informações da exceções do sistema.
 * @author Janpier
 *
 */
@JsonInclude(Include.NON_NULL)
public class Problem {
  private int statusCode;
  private String message;
  private OffsetDateTime dataTime;    

  public Problem(int statusCode, String message, OffsetDateTime dataTime) {
    super();
    this.statusCode = statusCode;
    this.message = message;
    this.dataTime = dataTime;
  }
  
  public Integer getStatusCode() {
    return statusCode;
  }
  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public OffsetDateTime getDataTime() {
    return dataTime;
  }
  public void setDataTime(OffsetDateTime dataTime) {
    this.dataTime = dataTime;
  }
}
