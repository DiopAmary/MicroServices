package ma.enset.blockchain.controllers;

import ma.enset.blockchain.dtos.BlockDto;
import ma.enset.blockchain.dtos.TransactionDto;
import ma.enset.blockchain.raquests.BlockRequest;
import ma.enset.blockchain.raquests.TransactionRequest;
import ma.enset.blockchain.responses.BlockResponse;
import ma.enset.blockchain.responses.TransactionResponse;
import ma.enset.blockchain.services.BlockService;
import ma.enset.blockchain.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/blocks")
public class BlockController {

    BlockService blockService;
    TransactionService transactionService;

    public BlockController(
            BlockService blockService,
            TransactionService transactionService
    ){
        this.blockService = blockService;
        this.transactionService =  transactionService;
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
    public ResponseEntity<BlockResponse> createBlock(
            @ModelAttribute BlockRequest blockRequest
    ) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        BlockDto blockDto = modelMapper.map(blockRequest, BlockDto.class);
        BlockDto createdBlockDto = blockService.createBlock(blockDto);
        return new ResponseEntity<>(modelMapper.map(createdBlockDto, BlockResponse.class), HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<BlockResponse> getTransaction(
            @RequestParam String codeBlock
    ) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        BlockDto blockDto = blockService.getBlock(codeBlock);
        return new ResponseEntity<>(modelMapper.map(blockDto, BlockResponse.class), HttpStatus.OK);
    }

    @GetMapping(
            path = "/all",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<List<BlockResponse>> getTransaction() throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        List<BlockDto> blockDtos = blockService.getBlocks();
        List<BlockResponse> blocks = new ArrayList<>();
        for (BlockDto blockDto : blockDtos){
            blocks.add(modelMapper.map(blockDto, BlockResponse.class));
        }
        return new ResponseEntity<>(blocks, HttpStatus.OK);
    }

    @PutMapping(
            path = "/match-block-transaction",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<BlockResponse> getTransaction(
            @ModelAttribute(name = "codeBlock") String codeBlock,
            @ModelAttribute(name = "codeTransaction") String codeTransaction
    ) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        TransactionDto transactionDto = transactionService.getTransaction(codeTransaction);
        BlockDto blockDto = blockService.getBlock(codeBlock);
        BlockDto updatedBlockDto = blockService.matchBlockTransaction(blockDto, transactionDto);
        return new ResponseEntity<>(modelMapper.map(updatedBlockDto, BlockResponse.class), HttpStatus.ACCEPTED);
    }
}
