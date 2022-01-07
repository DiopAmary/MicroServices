package ma.enset.blockchain.controllers;

import ma.enset.blockchain.dtos.TransactionDto;
import ma.enset.blockchain.raquests.TransactionRequest;
import ma.enset.blockchain.responses.TransactionResponse;
import ma.enset.blockchain.services.BlockService;
import ma.enset.blockchain.services.BlockchainService;
import ma.enset.blockchain.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    TransactionService transactionService;
    BlockchainService blockchainService;
    BlockService blockService;

    public TransactionController(
            TransactionService transactionService
    ){
        this.transactionService = transactionService;
    }


    @PostMapping(
            path = "/add",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<TransactionResponse> createTransaction(
            @ModelAttribute TransactionRequest transactionRequest
            ) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        TransactionDto transactionDto = modelMapper.map(transactionRequest, TransactionDto.class);
        TransactionDto createdTransactionDto = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(modelMapper.map(createdTransactionDto, TransactionResponse.class), HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/",

            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<TransactionResponse> getTransaction(
            @RequestParam String codeTransaction
    ) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        TransactionDto transactionDto = transactionService.getTransaction(codeTransaction);
        return new ResponseEntity<>(modelMapper.map(transactionDto, TransactionResponse.class), HttpStatus.OK);
    }

    @GetMapping(
            path = "/all",

            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<List<TransactionResponse>> getTransaction() throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        List<TransactionDto> transactionDtos = transactionService.getTransactions();
        List<TransactionResponse> transactions = new ArrayList<>();
        for (TransactionDto transactionDto : transactionDtos){
            transactions.add(modelMapper.map(transactionDto, TransactionResponse.class));
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


}
