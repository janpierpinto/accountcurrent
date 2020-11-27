package com.janpier.ca.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janpier.ca.api.models.AccountTransactions;

/**
 * Repositório para operações na entidade de Transações da Conta.
 * @author Janpier
 *
 */
@Repository
public interface ClientAccountTransactionsRepository extends JpaRepository<AccountTransactions, Long>{}
