package ma.enset.cqrses.commonapi.commands;

import lombok.Getter;

public class AccountCreateCommand extends BaseCommand<String>{
    @Getter private double initialBalance;
    @Getter private String currency;

    public AccountCreateCommand(String id, String currency, double initialBalance) {
        super(id);
        this.currency = currency;
        this.initialBalance = initialBalance;
    }
}
