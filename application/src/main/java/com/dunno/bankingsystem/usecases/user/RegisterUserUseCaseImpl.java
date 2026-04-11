package com.dunno.bankingsystem.usecases.user;

import com.dunno.bankingsystem.commands.RegisterUserCommand;
import com.dunno.bankingsystem.exceptions.EmailAlreadyRegisteredException;
import com.dunno.bankingsystem.exceptions.InvalidFieldException;
import com.dunno.bankingsystem.models.Account;
import com.dunno.bankingsystem.models.User;
import com.dunno.bankingsystem.models.valueobjects.Email;
import com.dunno.bankingsystem.models.valueobjects.Password;
import com.dunno.bankingsystem.repositories.AccountRepository;
import com.dunno.bankingsystem.repositories.UserRepository;
import com.dunno.bankingsystem.services.PasswordEncoder;
import reactor.core.publisher.Mono;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCaseImpl(UserRepository userRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<User> execute(RegisterUserCommand command) {

        if (command.password() == null || command.passwordConfirmation() == null) {
            throw new InvalidFieldException("password", "Password and confirmation cannot be null.");
        }

        if (!command.password().equals(command.passwordConfirmation())) {
            throw new InvalidFieldException("passwordConfirmation", "Passwords do not match.");
        }

        Password.validate(command.password());

        Email email = Email.of(command.email());

        return userRepository.existsByEmail(email.getValue())
                .<User>handle((exists, sink) -> {
                    if(exists) {
                        sink.error(new EmailAlreadyRegisteredException("Email " + email.getValue() + " is already in used. Try with another one."));
                    } else {
                        sink.next(new User(email, Password.fromEncoded(passwordEncoder.encode(command.password()))));
                    }
                }).flatMap(userRepository::save)
                .flatMap(user -> {
                    return accountRepository.save(new Account(user.getId()))
                            .thenReturn(user);
                });
    }
}
