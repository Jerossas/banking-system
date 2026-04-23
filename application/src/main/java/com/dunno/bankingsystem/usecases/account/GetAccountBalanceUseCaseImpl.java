package com.dunno.bankingsystem.usecases.account;

import com.dunno.bankingsystem.commands.GetAccountBalanceCommand;
import com.dunno.bankingsystem.exceptions.UserNotFoundException;
import com.dunno.bankingsystem.models.Account;
import com.dunno.bankingsystem.repositories.AccountRepository;
import com.dunno.bankingsystem.repositories.UserRepository;
import reactor.core.publisher.Mono;

public class GetAccountBalanceUseCaseImpl implements GetAccountBalanceUseCase {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public GetAccountBalanceUseCaseImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Mono<Account> execute(GetAccountBalanceCommand command) {

        return userRepository.findByEmail(command.userEmail())
                .switchIfEmpty(Mono.error(new UserNotFoundException("User with email " + command.userEmail() + " not found.")))
                .flatMap(user -> {
                    return accountRepository.findByUserId(user.getId());
                });
    }
}
