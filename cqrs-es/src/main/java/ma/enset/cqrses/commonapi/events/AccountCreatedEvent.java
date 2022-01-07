package ma.enset.cqrses.commonapi.events;

import lombok.Getter;
import ma.enset.cqrses.commonapi.enums.AccountStatus;

import java.util.Date;

public class AccountCreatedEvent extends BaseEvent<String>{
    @Getter private  double initialBalance;
    @Getter private  String currency;
    @Getter private AccountStatus status;
    public AccountCreatedEvent(String id, Date dateEvent, double initialBalance, String currency, AccountStatus status) {
        super(id, dateEvent);
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.status = status;
    }
}
