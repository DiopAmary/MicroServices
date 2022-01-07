package ma.enset.blockchain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class BlockchainEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeBlockchain;
    private String nom;
    private int difficulte;
    private double miningReward;
    @OneToMany(mappedBy = "blockchain", fetch = FetchType.EAGER)
    private Collection<BlockEntity> blocks;

    public BlockchainEntity(String nom, int diff, double mR){
        this.nom = nom;
        this.difficulte = diff;
        this.miningReward = mR;
    }
}
