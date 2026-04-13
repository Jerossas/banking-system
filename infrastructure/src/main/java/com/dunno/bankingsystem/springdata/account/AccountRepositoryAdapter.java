package com.dunno.bankingsystem.springdata.account;

import com.dunno.bankingsystem.models.Account;
import com.dunno.bankingsystem.repositories.AccountRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class AccountRepositoryAdapter implements AccountRepository {

    private final R2dbcAccountRepository accountRepository;

    public AccountRepositoryAdapter(R2dbcAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Mono<Account> save(Account account) {

        AccountEntity entity = new AccountEntity(account.getId(), account.getAccountHolder(), account.getBalance());

        return accountRepository.save(entity)
                .map(saved -> Account.restore(
                        saved.getId(),
                        saved.getUserId(),
                        saved.getBalance()
                ));
    }
}
