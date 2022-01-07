package ma.enset.blockchain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.blockchain.entities.BlockEntity;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockchainDto implements Serializable {
    private static final long serialVersionUID = -4211697070270850828L;

    private Long id;
    private String codeBlockchain;
    private String nom;
    private int difficulte;
    private double miningReward;
    private Collection<BlockDto> blocks;
}
