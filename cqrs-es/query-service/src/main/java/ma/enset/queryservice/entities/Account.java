package ma.enset.queryservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.cqrses.commonapi.enums.AccountStatus;

import javax.persistence.*;
import java.util.Collection;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    private String id;
    private double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
    private Collection<Operation> operations;

}
