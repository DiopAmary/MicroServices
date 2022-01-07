package ma.enset.blockchain.services.implementations;

import ma.enset.blockchain.dtos.TransactionDto;
import ma.enset.blockchain.entities.TransactionEntity;
import ma.enset.blockchain.repositories.TransactionRepository;
import ma.enset.blockchain.services.TransactionService;
import ma.enset.blockchain.services.utils.SHA256Util;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImplementation implements TransactionService {
    TransactionRepository transactionRepository;
    public TransactionServiceImplementation(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        ModelMapper modelMapper = new ModelMapper();
        TransactionEntity transactionEntity = modelMapper.map(transactionDto, TransactionEntity.class);
        transactionEntity.setCodeTransaction(SHA256Util.generateStringCode(15));
        TransactionEntity savedTransaction = transactionRepository.save(transactionEntity);
        return modelMapper.map(savedTransaction, TransactionDto.class);
    }

    @Override
    public TransactionDto getTransaction(String codeTransaction) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        TransactionEntity transactionEntity = transactionRepository.findTransactionEntityByCodeTransaction(codeTransaction);
        if(transactionEntity == null){
            throw new Exception("Transaction inexistante");
        }
        return modelMapper.map(transactionEntity, TransactionDto.class);
    }

    @Override
    public List<TransactionDto> getTransactions() {
        ModelMapper modelMapper = new ModelMapper();
        List<TransactionEntity> transactionEntities = transactionRepository.findAll();
        List<TransactionDto> transactions = new ArrayList<TransactionDto>();
        for(TransactionEntity transactionEntity : transactionEntities){
            transactions.add(modelMapper.map(transactionEntity, TransactionDto.class));
        }
        return transactions;
    }
}
