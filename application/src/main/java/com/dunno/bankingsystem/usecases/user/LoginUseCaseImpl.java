package com.dunno.bankingsystem.usecases.user;

import com.dunno.bankingsystem.commands.LoginCommand;
import com.dunno.bankingsystem.exceptions.InvalidCredentialsException;
import com.dunno.bankingsystem.exceptions.InvalidFieldException;
import com.dunno.bankingsystem.models.User;
import com.dunno.bankingsystem.models.valueobjects.Email;
import com.dunno.bankingsystem.repositories.UserRepository;
import com.dunno.bankingsystem.services.PasswordEncoder;
import reactor.core.publisher.Mono;

public class LoginUseCaseImpl implements LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<User> execute(LoginCommand command) {

        if(command.password() == null) {
            throw new InvalidFieldException("password", "Password cannot be null.");
        }

        Email email = Email.of(command.email());

        return userRepository.findByEmail(email.getValue())
                .handle((user, sink) -> {
                    if((user == null) || !this.passwordEncoder.matches(command.password(), user.getPassword().getValue())) {
                        throw new InvalidCredentialsException("Invalid email or password");
                    } else {
                        sink.next(user);
                    }
                });
    }
}
