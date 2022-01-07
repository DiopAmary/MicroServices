package ma.enset.blockchain.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity @AllArgsConstructor @NoArgsConstructor @Data
public class TransactionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeTransaction;
    @CreatedDate @Temporal(TemporalType.TIMESTAMP)
    private Date dateTransaction;
    private String adresseSource;
    private String adresseDestination;
    private double montantTransaction;
    @ManyToOne
    private BlockEntity block;


    @PrePersist
    public void setCreatedAt() {
        this.dateTransaction= new Date();
    }
}
