package ma.enset.cqrses.commonapi.commands;

import lombok.Getter;

public class AccountCreditCommand extends BaseCommand<String>{
    @Getter
    private String currency;
    @Getter
    private double balance;

    public AccountCreditCommand(String id, String currency, double balance) {
        super(id);
        this.currency = currency;
        this.balance = balance;
    }
}
