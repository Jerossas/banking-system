package com.dunno.bankingsystem.config;

import com.dunno.bankingsystem.repositories.AccountRepository;
import com.dunno.bankingsystem.repositories.UserRepository;
import com.dunno.bankingsystem.services.PasswordEncoder;
import com.dunno.bankingsystem.usecases.user.LoginUseCase;
import com.dunno.bankingsystem.usecases.user.LoginUseCaseImpl;
import com.dunno.bankingsystem.usecases.user.RegisterUserUseCase;
import com.dunno.bankingsystem.usecases.user.RegisterUserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public RegisterUserUseCase registerUserUseCase(
            UserRepository userRepository,
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder
    ){
        return new RegisterUserUseCaseImpl(userRepository, accountRepository, passwordEncoder);
    }

    @Bean
    public LoginUseCase loginUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){
        return new LoginUseCaseImpl(userRepository, passwordEncoder);
    }

}
