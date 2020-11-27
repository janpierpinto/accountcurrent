package com.janpier.ca.api.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.janpier.ca.api.domain.model.AccountTypeOperations;

/**
 * Entidade que representa a tabela AccountTransactions no banco de dados.
 * @author Janpier
 *
 */
@Entity
public class AccountTransactions {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  @NotNull
  private Account account;  
  
  @Enumerated(EnumType.STRING)
  private AccountTypeOperations typeoperation;
  
  @NotNull
  private BigDecimal operationvalue;
  
  @NotNull
  private OffsetDateTime operationdata;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
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
    AccountTransactions other = (AccountTransactions) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
