package com.dunno.bankingsystem.springdata.user;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsServiceAdapter implements ReactiveUserDetailsService {

    private final R2dbcUserRepository userRepository;

    public UserDetailsServiceAdapter(R2dbcUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(user -> (UserDetails) user);
    }
}
