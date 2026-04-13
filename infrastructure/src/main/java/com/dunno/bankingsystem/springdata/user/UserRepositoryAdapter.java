package com.dunno.bankingsystem.springdata.user;

import com.dunno.bankingsystem.models.User;
import com.dunno.bankingsystem.models.valueobjects.Email;
import com.dunno.bankingsystem.models.valueobjects.Password;
import com.dunno.bankingsystem.repositories.UserRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final R2dbcUserRepository userRepository;

    public UserRepositoryAdapter(R2dbcUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> save(User user) {

        UserEntity entity = new UserEntity(
                user.getId(),
                user.getEmail().getValue(),
                user.getPassword().getValue()
        );

        return userRepository.save(entity)
                .map(saved -> User.restore(
                        saved.getId(),
                        Email.fromStored(saved.getEmail()),
                        Password.fromEncoded(saved.getPassword())
                ));
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
