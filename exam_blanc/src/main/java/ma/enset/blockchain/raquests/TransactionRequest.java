package ma.enset.blockchain.raquests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest implements Serializable {
    private static final long serialVersionUID = -4211697056270850848L;

    private String adresseSource;
    private String adresseDestination;
    private double montantTransaction;
}
