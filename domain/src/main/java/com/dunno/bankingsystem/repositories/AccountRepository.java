package com.dunno.bankingsystem.repositories;

import com.dunno.bankingsystem.models.Account;
import reactor.core.publisher.Mono;

public interface AccountRepository {
    Mono<Account> save(Account account);
}
