package com.dunno.bankingsystem.controllers;

import com.dunno.bankingsystem.commands.LoginCommand;
import com.dunno.bankingsystem.commands.RegisterUserCommand;
import com.dunno.bankingsystem.dtos.LoginRequest;
import com.dunno.bankingsystem.dtos.RegisterUserRequest;
import com.dunno.bankingsystem.dtos.TokenResponse;
import com.dunno.bankingsystem.services.JwtService;
import com.dunno.bankingsystem.usecases.user.LoginUseCase;
import com.dunno.bankingsystem.usecases.user.RegisterUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;
    private final JwtService jwtService;

    public AuthController(
            RegisterUserUseCase registerUserUseCase,
            LoginUseCase loginUseCase,
            JwtService jwtService
    ) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUseCase = loginUseCase;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<TokenResponse>> registerUser(@RequestBody RegisterUserRequest request) {
        RegisterUserCommand command = new RegisterUserCommand(
                request.email(),
                request.password(),
                request.passwordConfirmation()
        );

        return registerUserUseCase.execute(command)
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(
                        new TokenResponse(jwtService.generate(user))
                ));
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<TokenResponse>> loginUser(@RequestBody LoginRequest request){

        LoginCommand command = new LoginCommand(request.email(), request.password());

        return loginUseCase.execute(command).
                map(user -> ResponseEntity.status(HttpStatus.OK).body(
                        new TokenResponse(jwtService.generate(user))
                ));
    }

}
