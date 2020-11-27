package com.janpier.ca.api.domain.model;

import java.math.BigDecimal;

/**
 * Modelo de uma conta corrente.
 * @author Janpier
 *
 */
public class AccountModel {
  
  private String holder;
  private Long accountnumber;
  private BigDecimal balance;

  public BigDecimal getBalance() {
    return balance;
  }
  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
  public String getHolder() {
    return holder;
  }
  public void setHolder(String name) {
    this.holder = name;
  }
  public Long getAccountnumber() {
    return accountnumber;
  }
  public void setAccountnumber(Long accountnumber) {
    this.accountnumber = accountnumber;
  }
}
