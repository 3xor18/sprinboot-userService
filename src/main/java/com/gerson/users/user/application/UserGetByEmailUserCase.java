package com.gerson.users.user.application;

import com.gerson.users.user.domain.entities.User;
import com.gerson.users.user.infra.inputport.UserGetByEmailInputPort;
import com.gerson.users.user.infra.outputport.UserPersistenceInputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@Slf4j
public class UserGetByEmailUserCase implements UserGetByEmailInputPort {

    private final  UserPersistenceInputPort repository;

    public UserGetByEmailUserCase(UserPersistenceInputPort repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> execute(String email)  {
        log.info("Consultando User by email: " + email);
        return repository.getByEmail(email);
    }
}
