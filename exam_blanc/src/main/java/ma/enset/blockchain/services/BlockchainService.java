package ma.enset.blockchain.services;

import ma.enset.blockchain.dtos.BlockDto;
import ma.enset.blockchain.dtos.BlockchainDto;

import java.util.List;

public interface BlockchainService {
    BlockchainDto createBlockchain(BlockchainDto blockchainDto, BlockDto genisis);
    BlockDto miner(BlockDto blockDto);
    List<BlockchainDto> getBlockchains();
    BlockchainDto getBlockchain(String codeBlockchain) throws Exception;
    BlockchainDto matchBlockchainBlock(BlockchainDto blockchainDto, BlockDto blockDto) throws Exception;
}
