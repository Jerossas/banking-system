package com.dunno.bankingsystem.usecases.user;

import com.dunno.bankingsystem.UseCase;
import com.dunno.bankingsystem.commands.RegisterUserCommand;
import com.dunno.bankingsystem.models.User;
import reactor.core.publisher.Mono;

public interface RegisterUserUseCase extends UseCase<RegisterUserCommand, Mono<User>> {}
