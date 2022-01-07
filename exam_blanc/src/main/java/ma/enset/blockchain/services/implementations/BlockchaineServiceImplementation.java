package ma.enset.blockchain.services.implementations;

import ma.enset.blockchain.dtos.BlockDto;
import ma.enset.blockchain.dtos.BlockchainDto;
import ma.enset.blockchain.entities.BlockchainEntity;
import ma.enset.blockchain.entities.BlockEntity;
import ma.enset.blockchain.repositories.BlockchainRepository;
import ma.enset.blockchain.repositories.BlockRepository;
import ma.enset.blockchain.services.BlockchainService;
import ma.enset.blockchain.services.utils.SHA256Util;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BlockchaineServiceImplementation implements BlockchainService {
    BlockchainRepository blockChainRepository;
    BlockRepository blockRepository;

    public BlockchaineServiceImplementation(BlockchainRepository blockChainRepository,
                                            BlockRepository blockRepository
    ){
        this.blockChainRepository = blockChainRepository;
        this.blockRepository = blockRepository;
    }

    @Override
    public BlockchainDto createBlockchain(BlockchainDto blockChainDto, BlockDto genisis) {
        ModelMapper modelMapper = new ModelMapper();
        BlockchainEntity blockChainEntity = modelMapper.map(blockChainDto, BlockchainEntity.class);
        blockChainEntity.setCodeBlockchain(SHA256Util.generateStringCode(15));
        BlockEntity blockEntity = blockRepository.findBlockEntityByCodeBlock(genisis.getCodeBlock());
        blockChainEntity.setBlocks(new ArrayList<>());
        blockChainEntity.getBlocks().add(blockEntity);
        BlockchainEntity savedBlockchain = blockChainRepository.save(blockChainEntity);
        blockEntity.setBlockchain(savedBlockchain);
        blockRepository.save(blockEntity);
        return modelMapper.map(savedBlockchain, BlockchainDto.class);
    }

    @Override
    public List<BlockchainDto> getBlockchains() {
        ModelMapper modelMapper = new ModelMapper();
        List<BlockchainEntity> blockChainEntities = blockChainRepository.findAll();
        List<BlockchainDto> blockChains = new ArrayList<>();
        for(BlockchainEntity blockchainEntity : blockChainEntities){
            blockChains.add(modelMapper.map(blockchainEntity, BlockchainDto.class));
        }
        return blockChains;
    }

    @Override
    public BlockchainDto getBlockchain(String codeBlockchain) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        BlockchainEntity blockchainEntity = blockChainRepository.findBlockchainEntityByCodeBlockchain(codeBlockchain);
        if(blockchainEntity == null){
            throw new Exception("Blockchain inexistant");
        }
        return modelMapper.map(blockchainEntity, BlockchainDto.class);
    }

    @Override
    public BlockchainDto matchBlockchainBlock(BlockchainDto blockchainDto, BlockDto blockDto) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        BlockchainEntity blockchainEntity = blockChainRepository.findBlockchainEntityByCodeBlockchain(blockchainDto.getCodeBlockchain());
        BlockEntity blockEntity = blockRepository.findBlockEntityByCodeBlock(blockDto.getCodeBlock());
        if(blockchainEntity == null){
            throw new Exception("Blockchain inexistant");
        }
        if(blockEntity == null){
            throw new Exception("Block inexistant");
        }
        blockchainEntity.getBlocks().add(blockEntity);
        BlockchainEntity savedBlockchain = blockChainRepository.save(blockchainEntity);
        blockEntity.setBlockchain(savedBlockchain);
        blockRepository.save(blockEntity);
        return modelMapper.map(savedBlockchain, BlockchainDto.class);
    }

    @Override
    public BlockDto miner(BlockDto blockDto) {
        ModelMapper modelMapper = new ModelMapper();
        BlockEntity blockEntity = blockRepository.findBlockEntityByCodeBlock(blockDto.getCodeBlock());

        String chaine = SHA256Util.createStringBuffer();
        String hash = SHA256Util.hash(chaine);
        String hashHeader = hash.substring(0, 3);
        int nonce = 0;
        while(!hashHeader.equals("000")){
            hash = SHA256Util.hash(chaine);
            System.out.println(hash);
            hashHeader = hash.substring(0, 3);
            chaine = SHA256Util.createStringBuffer();
            nonce++;
        }
        blockEntity.setHash(hash);
        blockEntity.setNonce(nonce);
        blockEntity.setMined(true);
        BlockEntity savedBlockEntity = blockRepository.save(blockEntity);
        return modelMapper.map(savedBlockEntity, BlockDto.class);
    }
}
