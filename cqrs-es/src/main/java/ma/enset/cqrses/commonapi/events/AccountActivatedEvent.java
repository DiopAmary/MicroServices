package ma.enset.cqrses.commonapi.events;

import lombok.Getter;
import ma.enset.cqrses.commonapi.enums.AccountStatus;

import java.util.Date;

public class AccountActivatedEvent extends BaseEvent<String>{
    @Getter private AccountStatus status;
    public AccountActivatedEvent(String id, Date dateEvent, AccountStatus status) {
        super(id, dateEvent);
        this.status = status;
    }
}
