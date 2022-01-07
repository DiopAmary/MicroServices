package ma.enset.blockchain.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse implements Serializable {

    private static final long serialVersionUID = -4211697079970850848L;

    private Long id;
    private String codeTransaction;
    private Date dateTransaction;
    private String adresseSource;
    private String adresseDestination;
    private double montantTransaction;
}
