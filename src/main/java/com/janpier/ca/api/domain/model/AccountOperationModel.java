package com.janpier.ca.api.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Modelo de transações para a conta corrente do usuário.
 * @author Janpier
 *
 */
public class AccountOperationModel {
  
  private AccountModel account;
  
  private AccountTypeOperations typeoperation;
  
  @NotNull
  private BigDecimal operationvalue;
  
  @NotNull
  private OffsetDateTime operationdata; 

  public AccountModel getAccount() {
    return account;
  }

  public void setAccount(AccountModel account) {
    this.account = account;
  }

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
}
