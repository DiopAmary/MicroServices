package ma.enset.cqrses.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditAccountRequestDto {
    private String accountId;
    private double balance;
    private String currency;
}
