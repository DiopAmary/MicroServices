package ma.enset.blockchain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockDto implements Serializable {
    private static final long serialVersionUID = -4211697070270850848L;
    private Long id;
    private String codeBlock;
    private Date dateBlock;
    private String hash;
    private String hashPrevious;
    private int nonce;
    private boolean mined;
    private Collection<TransactionDto> transactions;
    private BlockchainDto blockchain;
}
