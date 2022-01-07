package ma.enset.blockchain.services;

import ma.enset.blockchain.dtos.BlockDto;
import ma.enset.blockchain.dtos.TransactionDto;

import java.util.List;

public interface BlockService {
    BlockDto createBlock(BlockDto blockDto);
    BlockDto updateBlock(BlockDto blockDto, int nonce);
    List<BlockDto> getBlocks();
    BlockDto getBlock(String codeBlock) throws Exception;
    BlockDto matchBlockTransaction(BlockDto blockDto, TransactionDto transactionDto) throws Exception;
}
