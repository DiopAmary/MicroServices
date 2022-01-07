package ma.enset.blockchain.repositories;

import ma.enset.blockchain.entities.BlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<BlockEntity, Long>{
    BlockEntity findBlockEntityByCodeBlock(String codeBlock);
}
