package com.dunno.bankingsystem.usecases.account;

import com.dunno.bankingsystem.UseCase;
import com.dunno.bankingsystem.commands.GetAccountBalanceCommand;
import com.dunno.bankingsystem.models.Account;
import reactor.core.publisher.Mono;

public interface GetAccountBalanceUseCase extends UseCase<GetAccountBalanceCommand, Mono<Account>> {
}
