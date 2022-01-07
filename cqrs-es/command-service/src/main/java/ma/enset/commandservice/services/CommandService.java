package ma.enset.commandservice.services;

import ma.enset.cqrses.commonapi.dtos.*;

import java.util.concurrent.CompletableFuture;

public interface CommandService {
    CompletableFuture<String> createAccount(CreateAccountRequestDto accountRequestDTO);
    CompletableFuture<String> debitAccount(DebitAccountRequestDto debitAccountRequestDTO);
    CompletableFuture<String> creditAccount(CreditAccountRequestDto creditAccountRequestDTO);
}
