package com.gerson.users.user.application;

import com.gerson.users.user.infra.inputport.UserDeleteInputPort;
import com.gerson.users.user.infra.outputport.UserPersistenceInputPort;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDeleteUserCase implements UserDeleteInputPort {

    private final UserPersistenceInputPort userRepository;

    public UserDeleteUserCase(UserPersistenceInputPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void delete(Long id) throws AppExceptionInternalServerError, AppExceptionNotFound {
        userRepository.delete(id);
    }
}
