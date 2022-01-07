package ma.enset.blockchain.repositories;

import ma.enset.blockchain.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    TransactionEntity findTransactionEntityByCodeTransaction(String codeTransaction);
}
