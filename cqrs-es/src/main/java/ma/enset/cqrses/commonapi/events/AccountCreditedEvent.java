package ma.enset.cqrses.commonapi.events;

import lombok.Getter;
import ma.enset.cqrses.commonapi.enums.AccountStatus;

import java.util.Date;

public class AccountCreditedEvent extends BaseEvent<String>{
    @Getter private double balance;
    @Getter private String currency;
    @Getter private AccountStatus status;
    public AccountCreditedEvent(String id, Date dateEvent, double balance, String currency, AccountStatus status) {
        super(id, dateEvent);
        this.balance = balance;
        this.currency = currency;
        this.status = status;
    }
}
