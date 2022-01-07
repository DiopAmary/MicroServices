package ma.enset.blockchain.services.implementations;

import lombok.AllArgsConstructor;
import ma.enset.blockchain.dtos.BlockDto;
import ma.enset.blockchain.dtos.TransactionDto;
import ma.enset.blockchain.entities.BlockEntity;
import ma.enset.blockchain.entities.TransactionEntity;
import ma.enset.blockchain.repositories.BlockRepository;
import ma.enset.blockchain.repositories.TransactionRepository;
import ma.enset.blockchain.services.BlockService;
import ma.enset.blockchain.services.utils.SHA256Util;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class BlockServiceImplementation implements BlockService {
    BlockRepository blockRepository;
    TransactionRepository transactionRepository;


    @Override
    public BlockDto createBlock(BlockDto blockDto) {
        ModelMapper modelMapper = new ModelMapper();
        BlockEntity blockEntity = modelMapper.map(blockDto, BlockEntity.class);
        blockEntity.setCodeBlock(SHA256Util.generateStringCode(15));
        String chaine = SHA256Util.createStringBuffer();
        blockEntity.setHash(SHA256Util.hash(chaine));
        BlockEntity savedBlock = blockRepository.save(blockEntity);
        return modelMapper.map(savedBlock, BlockDto.class);
    }

    @Override
    public BlockDto updateBlock(BlockDto blockDto, int nonce) {
        ModelMapper modelMapper = new ModelMapper();
        BlockEntity blockEntity = blockRepository.findBlockEntityByCodeBlock(blockDto.getCodeBlock());
        blockEntity.setNonce(nonce);
        BlockEntity savedBlock = blockRepository.save(blockEntity);
        return modelMapper.map(savedBlock, BlockDto.class);
    }

    @Override
    public List<BlockDto> getBlocks() {
        List<BlockEntity> blocksEntities = blockRepository.findAll();
        List<BlockDto> blocks = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(BlockEntity blockEntity : blocksEntities){
            blocks.add(modelMapper.map(blockEntity, BlockDto.class));
        }
        return blocks;
    }

    @Override
    public BlockDto getBlock(String codeBlock) throws Exception {
        BlockEntity blockEntity = blockRepository.findBlockEntityByCodeBlock(codeBlock);
        ModelMapper modelMapper = new ModelMapper();
        if(blockEntity == null){
            throw new Exception("Block inexistant");
        }
        return modelMapper.map(blockEntity, BlockDto.class);
    }

    @Override
    public BlockDto matchBlockTransaction(BlockDto blockDto, TransactionDto transactionDto) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        BlockEntity blockEntity = blockRepository.findBlockEntityByCodeBlock(blockDto.getCodeBlock());
        TransactionEntity transactionEntity = transactionRepository.findTransactionEntityByCodeTransaction(transactionDto.getCodeTransaction());
        if(transactionEntity == null){
            throw new Exception("Transaction inexistante");
        }
        if(blockEntity == null){
            throw new Exception("Block inexistant");
        }
        blockEntity.getTransactions().add(transactionEntity);
        transactionEntity.setBlock(blockEntity);
        transactionRepository.save(transactionEntity);
        BlockEntity savedBlockEntity = blockRepository.save(blockEntity);
        return modelMapper.map(savedBlockEntity, BlockDto.class);
    }

}
