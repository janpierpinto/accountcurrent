package com.janpier.ca.api.domain.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janpier.ca.api.domain.exception.ExceptionBusiness;
import com.janpier.ca.api.domain.exception.InsufficientFundsException;
import com.janpier.ca.api.domain.model.AccountTypeOperations;
import com.janpier.ca.api.domain.repository.ClientAccountTransactionsRepository;
import com.janpier.ca.api.domain.repository.ClientAccountRepository;
import com.janpier.ca.api.domain.repository.ClientRepository;
import com.janpier.ca.api.models.Account;
import com.janpier.ca.api.models.AccountTransactions;
import com.janpier.ca.api.models.Client;

/**
 * Responsável por executar regras de negócios da API. * 
 * @author janpier
 *
 */

@Service
public class CreateAccountService {
  
  @Autowired
  private ClientRepository clientRepository;
  
  @Autowired
  private ClientAccountRepository clientAccountRepository;
  
  @Autowired
  private ClientAccountTransactionsRepository clientAccountTransactionsRepository;
  
  /**
   * Armazena as informações do cliente para a geração da conta corrente.
   * @param client
   * @return
   */
  public Client save (Client client) {
    //Verifica se o CPF informado possui uma conta corrente associada.
    Client cExists = clientRepository.findByCpf(client.getCpf());
    if (cExists != null) {
      throw new ExceptionBusiness("Cliente já possui uma conta corrente!");
    }
    return clientRepository.save(client)
;
  }
  
  /**
   * Cria uma nova conta corrente para o usuário.
   * @param client
   * @return
   */
  public Account create (Client client) {
    //Gera um número aleatório para a conta do usuário.
    long nConta = (long) (Math.random() * ((100001 - 10) + 1) + client.getId());
    
    //Instância um novo objeto para a criação da conta corrente.
    Account account = new Account();
    account.setClient(client);
    account.setHolder(client.getName());
    account.setBalance(new BigDecimal("0.0"));
    account.setAccountnumber(nConta);
    
    return clientAccountRepository.save(account);
  }
  
  /**
   * Executa um tipo de transação na conta corrente do usuário.
   * @param account
   * @param operationtype
   * @param value
   * @return
   */
  public AccountTransactions execute (Account account, AccountTypeOperations operationtype, BigDecimal value) {
    Account aExists = clientAccountRepository.findByAccountnumber(account.getAccountnumber());
    if (aExists == null) {
      throw new ExceptionBusiness("A conta informada não existe!");
    }
    
    BigDecimal balance = account.getBalance();
    
    if (operationtype.equals(AccountTypeOperations.DRAWOUT)) {
      if(value.doubleValue() > balance.doubleValue()) {
        throw new InsufficientFundsException("Saldo insuficiente!");
      }
    }
    
    if (operationtype.equals(AccountTypeOperations.PLACE) && value.doubleValue() < 0) {
      throw new ExceptionBusiness("Não é possívelr realizar o depósito de um valor negativo");
    }
    
    AccountTransactions accounttransaction = new AccountTransactions();
    accounttransaction.setAccount(account);
    accounttransaction.setOperationvalue(value);
    accounttransaction.setTypeoperation(operationtype);
    accounttransaction.setOperationdata(OffsetDateTime.now());
    
    clientAccountTransactionsRepository.save(accounttransaction);
    
    if(operationtype.equals(AccountTypeOperations.PLACE)) account.setBalance(balance.add(value));
    else account.setBalance(balance.subtract(value));
    
    // Atualiza o saldo disponível no banco de dados.
    clientAccountRepository.save(account);
    
    return accounttransaction;
  }
}
