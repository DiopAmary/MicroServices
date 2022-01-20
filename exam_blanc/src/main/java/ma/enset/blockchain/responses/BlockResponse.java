package ma.enset.blockchain.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.blockchain.dtos.TransactionDto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class BlockResponse implements Serializable {

    private static final long serialVersionUID = -4211692279970850848L;

    private Long id;
    private String codeBlock;
    private Date dateBlock;
    private String hash;
    private String hashPrevious;
    private int nonce;
    private boolean mined;
    private Collection<TransactionResponse> transactions;
}
