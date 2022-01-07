package ma.enset.commandservice.services.implementations;

import ma.enset.commandservice.services.CommandService;
import ma.enset.cqrses.commonapi.commands.AccountCreateCommand;
import ma.enset.cqrses.commonapi.commands.AccountCreditCommand;
import ma.enset.cqrses.commonapi.commands.AccountDebitCommand;
import ma.enset.cqrses.commonapi.dtos.CreateAccountRequestDto;
import ma.enset.cqrses.commonapi.dtos.CreditAccountRequestDto;
import ma.enset.cqrses.commonapi.dtos.DebitAccountRequestDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CommandServiceImplementation implements CommandService {

    private CommandGateway commandGateway;

    public CommandServiceImplementation(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(CreateAccountRequestDto accountRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new AccountCreateCommand(
                UUID.randomUUID().toString(),
                accountRequestDTO.getCurrency(),
                accountRequestDTO.getInitialBalance()
        ));
        return response;
    }

    @Override
    public CompletableFuture<String> debitAccount(DebitAccountRequestDto debitAccountRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new AccountDebitCommand(
                debitAccountRequestDTO.getAccountId(),
                debitAccountRequestDTO.getCurrency(),
                debitAccountRequestDTO.getBalance()
        ));
        return response;
    }

    @Override
    public CompletableFuture<String> creditAccount(CreditAccountRequestDto creditAccountRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new AccountCreditCommand(
                creditAccountRequestDTO.getAccountId(),
                creditAccountRequestDTO.getCurrency(),
                creditAccountRequestDTO.getBalance()
        ));
        return response;
    }
}
