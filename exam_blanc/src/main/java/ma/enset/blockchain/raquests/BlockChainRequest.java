package ma.enset.blockchain.raquests;

import lombok.*;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class BlockChainRequest implements Serializable {

    private static final long serialVersionUID = -4211697079270850828L;

    private String nom;
    private int difficulte;
    private double miningReward;
    private String codeGenisis;
}
