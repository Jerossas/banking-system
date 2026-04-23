package com.dunno.bankingsystem.springdata.account;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface R2dbcAccountRepository extends R2dbcRepository<AccountEntity, Long> {
    Mono<AccountEntity> findByUserId(Long userId);
}
