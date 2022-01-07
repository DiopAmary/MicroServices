package ma.enset.blockchain.services;

import ma.enset.blockchain.dtos.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);
    TransactionDto getTransaction(String codeTransaction) throws Exception;
    List<TransactionDto> getTransactions();
}
