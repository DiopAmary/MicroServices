package ma.enset.commandservice.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commandservice.services.CommandService;
import ma.enset.cqrses.commonapi.dtos.CreateAccountRequestDto;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

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

    @GetMapping(
            path = "/get",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public CompletableFuture<String> createAccount(
    ){
        return new CompletableFuture<String>();
    }
}
