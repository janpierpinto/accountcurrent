package com.janpier.ca.api.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade que representa a tabela Account no banco de dados.
 * @author janpi
 *
 */
@Entity
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @OneToOne
  private Client client;

  @NotNull
  private Long accountnumber;
  
  @NotBlank
  @Size(max = 60)  
  private String holder;
  
  @NotNull
  private BigDecimal balance;
  
  @OneToMany(mappedBy = "account")
  private List<AccountTransactions> transactions = new ArrayList<AccountTransactions>();  

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  
  public Client getClient() {
    return client;
  }
  public void setClient(Client client) {
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
  public List<AccountTransactions> getTransactions() {
    return transactions;
  }
  public void setTransactions(List<AccountTransactions> transactions) {
    this.transactions = transactions;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Account other = (Account) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }  
}
