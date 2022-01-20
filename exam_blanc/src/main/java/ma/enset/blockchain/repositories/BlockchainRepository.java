package ma.enset.blockchain.repositories;

import ma.enset.blockchain.entities.BlockchainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockchainRepository extends JpaRepository<BlockchainEntity, Long> {
    BlockchainEntity findBlockchainEntityByCodeBlockchain(String codeBlockchain);
}



