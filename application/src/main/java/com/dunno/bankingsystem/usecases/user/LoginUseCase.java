package com.dunno.bankingsystem.usecases.user;

import com.dunno.bankingsystem.UseCase;
import com.dunno.bankingsystem.commands.LoginCommand;
import com.dunno.bankingsystem.models.User;
import reactor.core.publisher.Mono;

public interface LoginUseCase extends UseCase<LoginCommand, Mono<User>> {}
