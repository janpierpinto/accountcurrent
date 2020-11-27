package com.janpier.ca.api.domain.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * Modelo de entrada de dados.
 * @author Janpier
 *
 */
public class AccountOperationInputModel {
  
  @NotNull
  private BigDecimal operationvalue;

  public BigDecimal getOperationvalue() {
    return operationvalue;
  }

  public void setOperationvalue(BigDecimal operationvalue) {
    this.operationvalue = operationvalue;
  }

}
