package com.dunno.bankingsystem.repositories;

import com.dunno.bankingsystem.models.User;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserRepository {

    Mono<User> save(User user);
    Mono<Boolean> existsByEmail(String email);
}
