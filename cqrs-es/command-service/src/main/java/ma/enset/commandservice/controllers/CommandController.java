package ma.enset.commandservice.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commandservice.services.CommandService;
import ma.enset.cqrses.commonapi.dtos.CreateAccountRequestDto;
import ma.enset.cqrses.commonapi.dtos.CreditAccountRequestDto;
import ma.enset.cqrses.commonapi.dtos.DebitAccountRequestDto;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/commands/account")
@AllArgsConstructor
public class CommandController {


    private CommandService commandService;
    private EventStore eventStore;

    @PostMapping(
        path = "/create",
        consumes = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.MULTIPART_FORM_DATA_VALUE
        }
    )
    public CompletableFuture<String> createAccount(
            @ModelAttribute CreateAccountRequestDto request
    ){
        CompletableFuture<String> response = commandService.createAccount(request);
        return response;
    }

    @GetMapping(path = "/event-store/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }

    @PutMapping(
        path = "/debit",
        consumes = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.MULTIPART_FORM_DATA_VALUE
        }
    )
    public CompletableFuture<String> debitAccount(
            @ModelAttribute DebitAccountRequestDto request
    ){
        CompletableFuture<String> response = commandService.debitAccount(request);
        return response;
    }

    @PutMapping(
        path = "/credit",
        consumes = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.MULTIPART_FORM_DATA_VALUE
        }
    )
    public CompletableFuture<String> creditAccount(
            @ModelAttribute CreditAccountRequestDto request
    ){
        CompletableFuture<String> response = commandService.creditAccount(request);
        return response;
    }
}
