package ma.enset.blockchain;

import ma.enset.blockchain.entities.BlockchainEntity;
import ma.enset.blockchain.entities.BlockEntity;
import ma.enset.blockchain.entities.TransactionEntity;
import ma.enset.blockchain.repositories.BlockchainRepository;
import ma.enset.blockchain.repositories.BlockRepository;
import ma.enset.blockchain.repositories.TransactionRepository;
import ma.enset.blockchain.services.BlockService;
import ma.enset.blockchain.services.BlockchainService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
@Transactional
public class BlockchainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainApplication.class, args);
    }
    @Bean
    CommandLineRunner start(TransactionRepository transactionRepository,
                            BlockchainRepository blockChainRepository,
                            BlockRepository blockRepository,
                            BlockService blockService,
                            BlockchainService blockchainService
    ){
        return arg -> {
            /*transactionRepository.save(new TransactionEntity("source1", "destination1", 1000));
            transactionRepository.save(new TransactionEntity("source2", "destination2", 2000));
            List<TransactionEntity> transactions = transactionRepository.findAll();

            blockService.createBlock("hashPrevious", transactions);
            BlockEntity blockEntity = blockRepository.findById(1L).get();


            BlockchaineDto blockchaineDto = new BlockchaineDto("My-blockchain", 3, 500.0);
            blockchainService.createBlockchain(blockchaineDto, blockEntity);
            BlockchainEntity blockChainEntity = blockChainRepository.findById(1L).get();
            blockchainService.miner(1L);

            BlockEntity blockEntityMined = blockRepository.findById(1L).get();
            System.out.println(blockEntityMined);
            System.out.println("----------hash---->  " + blockEntityMined.getHash());
            System.out.println("----------nonce---->  " + blockEntityMined.getNonce());
*/



            /*String buff = createStringBuffer(1L, new Date(), "hashP", 1, transactions);
            System.out.println(buff);*/

            /*BlockEntity blockEntity = new BlockEntity("hash", "hashPrevious", 5);
            for(TransactionEntity transaction : transactions){
                blockEntity.getListeTransactions().add(transaction);
            }
            blockRepository.save(blockEntity);

            List<BlockEntity> blocks = blockRepository.findAll();

            BlockChainEntity blockChainEntity = new BlockChainEntity("Blockchain", 4, 200.0);
            for(BlockEntity block : blocks){
                blockChainEntity.getBlocks().add(block);
            }
            blockChainRepository.save(blockChainEntity);

            System.out.println(blockChainEntity);*/

        };
    }
}
