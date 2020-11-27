package com.janpier.ca.api.domain.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Modelo de uma conta corrente de um usuário.
 * @author Janpier
 *
 */
public class AccountClientModel {  
  
  private ClientModel client;
  @NotNull
  private Long accountnumber;
  @NotBlank
  private String holder;
  @NotNull
  private BigDecimal balance;  
  
  public ClientModel getClient() {
    return client;
  }
  public void setClient(ClientModel client) {
    this.client = client;
  }
  public Long getAccountnumber() {
    return accountnumber;
  }
  public void setAccountnumber(Long accountnumber) {
    this.accountnumber = accountnumber;
  }
  public String getHolder() {
    return holder;
  }
  public void setHolder(String holder) {
    this.holder = holder;
  }
  public BigDecimal getBalance() {
    return balance;
  }
  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
