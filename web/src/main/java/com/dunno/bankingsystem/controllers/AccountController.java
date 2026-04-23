package com.dunno.bankingsystem.controllers;

import com.dunno.bankingsystem.commands.GetAccountBalanceCommand;
import com.dunno.bankingsystem.dtos.AccountResponse;
import com.dunno.bankingsystem.exceptions.UserNotAuthenticatedException;
import com.dunno.bankingsystem.usecases.account.GetAccountBalanceUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final GetAccountBalanceUseCase getAccountBalanceUseCase;

    public AccountController(GetAccountBalanceUseCase getAccountBalanceUseCase) {
        this.getAccountBalanceUseCase = getAccountBalanceUseCase;
    }

    @GetMapping("/balance")
    public Mono<ResponseEntity<AccountResponse>> getAccountBalance(){

            return ReactiveSecurityContextHolder.getContext()
                    .map(ctx -> (UserDetails) ctx.getAuthentication().getPrincipal())
                    .switchIfEmpty(Mono.error(new UserNotAuthenticatedException("No authenticated user was found in the security context.")))
                    .map(ctx -> new GetAccountBalanceCommand(ctx.getUsername()))
                    .flatMap(getAccountBalanceUseCase::execute)
                    .map(account -> ResponseEntity.status(HttpStatus.OK).body(new AccountResponse(account.getId(), account.getBalance())));
    }
}
