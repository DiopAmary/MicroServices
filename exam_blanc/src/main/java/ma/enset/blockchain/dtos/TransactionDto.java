package ma.enset.blockchain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto implements Serializable {

    private Long id;
    private String codeTransaction;
    private Date dateTransaction;
    private String adresseSource;
    private String adresseDestination;
    private double montantTransaction;
    private BlockDto block;
}
