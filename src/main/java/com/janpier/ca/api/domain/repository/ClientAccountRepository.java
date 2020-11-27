package com.janpier.ca.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janpier.ca.api.models.Account;

/**
 * Repositório para ações da entidade Conta.
 * @author Janpier
 *
 */
@Repository
public interface ClientAccountRepository extends JpaRepository<Account, Long> {
  
  Account findByAccountnumber (Long accountnumber);

}
