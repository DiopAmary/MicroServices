package ma.enset.blockchain.controllers;

import ma.enset.blockchain.dtos.BlockDto;
import ma.enset.blockchain.dtos.BlockchainDto;
import ma.enset.blockchain.raquests.BlockChainRequest;
import ma.enset.blockchain.responses.BlockchainResponse;
import ma.enset.blockchain.services.BlockService;
import ma.enset.blockchain.services.BlockchainService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/blockchains")
public class BlockchainController {

    BlockService blockService;
    BlockchainService blockchainService;

    public BlockchainController(
            BlockService blockService,
            BlockchainService blockchainService
    ){
        this.blockService = blockService;
        this.blockchainService =  blockchainService;
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
    public ResponseEntity<BlockchainResponse> createBlock(
            @ModelAttribute BlockChainRequest blockChainRequest
    ) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        BlockchainDto blockchainDto = modelMapper.map(blockChainRequest, BlockchainDto.class);
        BlockDto genisis = blockService.getBlock(blockChainRequest.getCodeGenisis());
        BlockchainDto createdBlockchainDto = blockchainService.createBlockchain(blockchainDto, genisis);
        return new ResponseEntity<>(modelMapper.map(createdBlockchainDto, BlockchainResponse.class), HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<BlockchainResponse> getTransaction(
            @RequestParam(name = "codeBlockchain") String codeBlockchain
    ) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        BlockchainDto blockchainDto = blockchainService.getBlockchain(codeBlockchain);
        return new ResponseEntity<>(modelMapper.map(blockchainDto, BlockchainResponse.class), HttpStatus.OK);
    }

    @GetMapping(
            path = "/all",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<List<BlockchainResponse>> getBlockchains(){
        ModelMapper modelMapper = new ModelMapper();
        List<BlockchainDto> blockchainDtos = blockchainService.getBlockchains();
        List<BlockchainResponse> blockchainResponses = new ArrayList<>();
        for(BlockchainDto blockchainDto : blockchainDtos){
            blockchainResponses.add(modelMapper.map(blockchainDto, BlockchainResponse.class));
        }
        return new ResponseEntity<>(blockchainResponses,  HttpStatus.OK);
    }

    //put
    @PutMapping(
            path = "/miner",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<BlockchainResponse> minerBlock(
            @RequestParam(name = "codeBlockchain") String codeBlockchain,
            @RequestParam(name = "codeBlock") String codeBlock
    ) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        BlockDto blockDto = blockService.getBlock(codeBlock);
        BlockDto minedBlock = blockchainService.miner(blockDto);
        BlockchainDto blockchainDto = blockchainService.getBlockchain(codeBlockchain);
        BlockchainDto matchedBlockchain = blockchainService.matchBlockchainBlock(blockchainDto, minedBlock);
        return new ResponseEntity<>(modelMapper.map(matchedBlockchain, BlockchainResponse.class), HttpStatus.ACCEPTED);
    }
}
