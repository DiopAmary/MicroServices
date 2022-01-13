package ma.enset.queryservice.service;

import lombok.AllArgsConstructor;
import ma.enset.cqrses.commonapi.enums.OpetationType;
import ma.enset.cqrses.commonapi.events.AccountActivatedEvent;
import ma.enset.cqrses.commonapi.events.AccountCreatedEvent;
import ma.enset.cqrses.commonapi.events.AccountCreditedEvent;
import ma.enset.cqrses.commonapi.events.AccountDebitedEvent;
import ma.enset.cqrses.commonapi.queries.GetAccountQuery;
import ma.enset.cqrses.commonapi.queries.GetAllAccountsQuery;
import ma.enset.queryservice.entities.Account;
import ma.enset.queryservice.entities.Operation;
import ma.enset.queryservice.repositories.AccountRepository;
import ma.enset.queryservice.repositories.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @EventHandler
    public void on(AccountCreatedEvent event){
        // Create Account
        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getInitialBalance());
        account.setCurrency(event.getCurrency());
        account.setStatus(event.getStatus());

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event){
        Account account = accountRepository.getById(event.getId());
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountCreditedEvent event){

        // update account's balance
        Account account = accountRepository.getById(event.getId());
        account.setStatus(event.getStatus());
        account.setBalance(account.getBalance() + event.getBalance());

        // Create Operation
        Operation operation = new Operation();
        operation.setAmount(event.getBalance());
        operation.setCreatedAt(event.getDateEvent());
        operation.setType(OpetationType.CREDIT);
        operation.setAccount(account);

        operationRepository.save(operation);
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountDebitedEvent event){
        // update account's balance
        Account account = accountRepository.getById(event.getId());
        account.setStatus(event.getStatus());
        account.setBalance(account.getBalance() - event.getBalance());

        // Create Operation
        Operation operation = new Operation();
        operation.setAmount(event.getBalance());
        operation.setCreatedAt(event.getDateEvent());
        operation.setType(OpetationType.DEBIT);
        operation.setAccount(account);

        operationRepository.save(operation);
        accountRepository.save(account);
    }

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        return accountRepository.findAll();
    }
    @QueryHandler
    public Account on(GetAccountQuery query){
        return accountRepository.findById(query.getAccountId()).get();
    }
}
