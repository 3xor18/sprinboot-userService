package com.gerson.users.user.infra.inputport;

import com.gerson.users.user.domain.entities.User;

import java.util.Optional;

public interface UserGetByEmailInputPort {

    Optional<User> execute(String email);
}
