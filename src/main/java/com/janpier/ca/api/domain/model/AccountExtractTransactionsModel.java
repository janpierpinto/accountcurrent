package com.janpier.ca.api.domain.model;

import java.math.BigDecimal;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotNull;

/**
 * Modelo para extrado de transações.
 * @author Janpier
 *
 */
public class AccountExtractTransactionsModel {
  
  private AccountTypeOperations typeoperation;
  
  public AccountTypeOperations getTypeoperation() {
    return typeoperation;
  }

  public void setTypeoperation(AccountTypeOperations typeoperation) {
    this.typeoperation = typeoperation;
  }

  public BigDecimal getOperationvalue() {
    return operationvalue;
  }

  public void setOperationvalue(BigDecimal operationvalue) {
    this.operationvalue = operationvalue;
  }

  public OffsetDateTime getOperationdata() {
    return operationdata;
  }

  public void setOperationdata(OffsetDateTime operationdata) {
    this.operationdata = operationdata;
  }

  @NotNull
  private BigDecimal operationvalue;
  
  @NotNull
  private OffsetDateTime operationdata; 

}
