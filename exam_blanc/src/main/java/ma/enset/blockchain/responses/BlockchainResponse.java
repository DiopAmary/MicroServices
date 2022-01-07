package ma.enset.blockchain.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.blockchain.dtos.BlockDto;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockchainResponse implements Serializable {

    private static final long serialVersionUID = -4211697066970850848L;

    private Long id;
    private String codeBlockchain;
    private String nom;
    private int difficulte;
    private double miningReward;
    private Collection<BlockResponse> blocks;
}
