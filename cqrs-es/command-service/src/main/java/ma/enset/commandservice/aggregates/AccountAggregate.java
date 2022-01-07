package ma.enset.commandservice.aggregates;

import ma.enset.cqrses.commonapi.commands.AccountCreateCommand;
import ma.enset.cqrses.commonapi.commands.AccountCreditCommand;
import ma.enset.cqrses.commonapi.commands.AccountDebitCommand;
import ma.enset.cqrses.commonapi.enums.AccountStatus;
import ma.enset.cqrses.commonapi.events.AccountActivatedEvent;
import ma.enset.cqrses.commonapi.events.AccountCreatedEvent;
import ma.enset.cqrses.commonapi.events.AccountCreditedEvent;
import ma.enset.cqrses.commonapi.events.AccountDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;

    private double amount;
    private String currency;
    private AccountStatus status;

    // Required
    public AccountAggregate(){}

    @CommandHandler
    public AccountAggregate(AccountCreateCommand command) throws Exception {
        if(command.getInitialBalance() < 0) throw new Exception("Solde negatif !!!!");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                new Date(),
                command.getInitialBalance(),
                command.getCurrency(),
                AccountStatus.CREATED
        ));
    }

    public void on(AccountCreatedEvent event){
        this.accountId = event.getId();
        this.currency = event.getCurrency();
        this.status = event.getStatus();
        this.amount = event.getInitialBalance();

        //activer le compte après création
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                new Date(),
                AccountStatus.ACTIVATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        this.accountId = event.getId();
        this.status = event.getStatus();
    }

    @CommandHandler
    public void handle(AccountCreditCommand command) throws Exception {
        if (command.getBalance() < 0) throw new Exception("Solde negatif !!!!");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                new Date(),
                command.getBalance(),
                command.getCurrency(),
                AccountStatus.CREDITED
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.accountId = event.getId();
        this.currency = event.getCurrency();
        this.amount = this.amount + event.getBalance();
        this.status = event.getStatus();
    }

    @CommandHandler
    public void handle(AccountDebitCommand command) throws Exception {
        if (command.getBalance() < 0) throw new Exception("Solde negatif !!!!");
        if (command.getBalance() > this.amount) throw new Exception("Solde inssuffisant !!!!");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                new Date(),
                command.getBalance(),
                command.getCurrency(),
                AccountStatus.DEBITED

        ));
    }
    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.accountId = event.getId();
        this.currency = event.getCurrency();
        this.amount = this.amount - event.getBalance();
        this.status = event.getStatus();
    }

}
