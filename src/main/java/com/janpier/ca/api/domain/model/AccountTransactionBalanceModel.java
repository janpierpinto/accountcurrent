package com.janpier.ca.api.domain.model;

import java.math.BigDecimal;

/**
 * Modelo para ação de consulta de saldo
 * @author janpi
 *
 */
public class AccountTransactionBalanceModel {
  
  private AccountModel account;

  public AccountModel getAccount() {
    return account;
  }

  public void setAccount(AccountModel account) {
    this.account = account;
  }

}
