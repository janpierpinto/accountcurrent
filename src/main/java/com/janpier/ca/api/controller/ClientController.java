package com.janpier.ca.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.janpier.ca.api.domain.exception.ExceptionBusiness;
import com.janpier.ca.api.domain.model.AccountClientModel;
import com.janpier.ca.api.domain.model.AccountExtractTransactionsModel;
import com.janpier.ca.api.domain.model.AccountOperationInputModel;
import com.janpier.ca.api.domain.model.AccountOperationModel;
import com.janpier.ca.api.domain.model.AccountTransactionBalanceModel;
import com.janpier.ca.api.domain.model.AccountTypeOperations;
import com.janpier.ca.api.domain.repository.ClientAccountRepository;
import com.janpier.ca.api.domain.service.CreateAccountService;
import com.janpier.ca.api.models.Account;
import com.janpier.ca.api.models.AccountTransactions;
import com.janpier.ca.api.models.Client;

/**
 * Classe responsável por realizar o cadastro de clientes na base de dados.
 * @author Janpier
 */

@RestController
@RequestMapping("/account")
public class ClientController {
  
  @Autowired
  private ModelMapper modelMapper;
  
  @Autowired
  private CreateAccountService cas;
  
  @Autowired
  private ClientAccountRepository car;
  
  /**
   * Cria uma conta corrente do usuário.
   * @param client
   * @return
   */
  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public AccountClientModel add (@Valid @RequestBody Client client) {
     Client nClient = cas.save(client);
     return toModel(cas.create(nClient));
  }
  
  /**
   * Realiza um depósito na conta corrente do usuário.
   * @param accountnumber
   * @param value
   * @return
   */
  @PutMapping("/transaction/{accountnumber}/place")
  @ResponseStatus(HttpStatus.OK)
  public AccountOperationModel place(@PathVariable Long accountnumber, @Valid @RequestBody AccountOperationInputModel value) {
    Account account = car.findByAccountnumber(accountnumber);
    if (account == null) {
      throw new ExceptionBusiness("A conta informada não existe!");
    }   
    
    return toModelOperation(cas.execute(account, AccountTypeOperations.PLACE , value.getOperationvalue()));
  }
  
  /**
   * Realiza um saque na conta corrente do usuário
   * @param accountnumber
   * @param value
   * @return
   */
  @PutMapping("/transaction/{accountnumber}/drawout")
  @ResponseStatus(HttpStatus.OK)
  public AccountOperationModel drowout(@PathVariable Long accountnumber, @Valid @RequestBody AccountOperationInputModel value) {
    Account account = car.findByAccountnumber(accountnumber);
    if (account == null) {
      throw new ExceptionBusiness("A conta informada não existe!");
    }
    
    AccountTransactions accounttransaction = cas.execute(account, AccountTypeOperations.DRAWOUT, value.getOperationvalue());
    return toModelOperation(accounttransaction);
  }
  
  /**
   * Retorna o extrato de transações de uma conta corrente.
   * @param accountnumber
   * @return
   */
  @GetMapping("/transaction/{accountnumber}")
  @ResponseStatus(HttpStatus.OK)
  public HashMap<String, Object> getAllTransactions(@Valid @PathVariable Long accountnumber) {
    Account account = car.findByAccountnumber(accountnumber);
    if (account == null) {
      throw new ExceptionBusiness("A conta informada não existe!");
    }
    List<AccountExtractTransactionsModel> extract = toModelExtractList(account.getTransactions());
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("holder", account.getHolder());
    map.put("accountnumber", account.getAccountnumber());
    map.put("balance", account.getBalance());
    map.put("transactions", extract);
    
    return map;    
  }
  
  /**
   * Realiza a consulta a saldo disponível na conta do usuário.
   * @param accountnumber
   * @return
   */
  @GetMapping("/transaction/{accountnumber}/balance")
  @ResponseStatus(HttpStatus.OK)
  public AccountTransactionBalanceModel getBalance(@Valid @PathVariable Long accountnumber) {
    Account account = car.findByAccountnumber(accountnumber);
    if (account == null) {
      throw new ExceptionBusiness("A conta informada não existe!");
    }
    return toModelBalance(account);
  }
  
  /**
   * Mapeamento de modelo de saída após criar uma conta corrente para o usuário.
   * @param account
   * @return
   */
  private AccountClientModel toModel(Account account) {
    return modelMapper.map(account, AccountClientModel.class);
  }
  
  /**
   * Mapeamento de modelo de saída após realizar uma transação na conta corrente de um usuário.
   * @param accounttransaction
   * @return
   */
  private AccountOperationModel toModelOperation(AccountTransactions accounttransaction) {
    return modelMapper.map(accounttransaction, AccountOperationModel.class);
  }
  
  /**
   * Mapeamento de modelo de saída após realizar uma consulta ao saldo da conta corrente de um usuário.
   * @param accounttransaction
   * @return
   */
  private AccountTransactionBalanceModel toModelBalance(Account account) {
    return modelMapper.map(account, AccountTransactionBalanceModel.class);
  }
  
  /**
   * Mapeamento de modelo de saída para uma extrato de conta.
   * @param transaction
   * @return
   */
  private AccountExtractTransactionsModel toModelExtract(AccountTransactions transaction) {
    return modelMapper.map(transaction, AccountExtractTransactionsModel.class);
  }
  
  /**
   * Mapeamento de modelo de saída após solicitar o extrato da conta corrente de um usuário.
   * @param accounttransaction
   * @return
   * 
   */  
  private List<AccountExtractTransactionsModel> toModelExtractList (List<AccountTransactions> transactions) {
    return transactions.stream()
        .map((transaction) -> toModelExtract(transaction))
        .collect(Collectors.toList());
  }
}
