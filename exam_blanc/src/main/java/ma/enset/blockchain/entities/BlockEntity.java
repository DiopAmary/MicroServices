package ma.enset.blockchain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class BlockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeBlock;
    @CreatedDate @Temporal(TemporalType.TIMESTAMP)
    private Date dateBlock;
    private String hash;
    private String hashPrevious;
    private int nonce;
    private boolean mined;
    @OneToMany(mappedBy = "block", fetch = FetchType.EAGER)
    private Collection<TransactionEntity> transactions;
    @ManyToOne
    private BlockchainEntity blockchain;

    public BlockEntity(String hash, String hashP, int nonce){
        this.hash = hash;
        this.hashPrevious = hashP;
        this.nonce = nonce;
    }

    @PrePersist
    public void setCreatedAt() {
        this.dateBlock = new Date();
    }
}
