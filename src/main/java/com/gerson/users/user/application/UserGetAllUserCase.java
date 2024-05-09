package com.gerson.users.user.application;

import com.gerson.users.user.domain.dtos.UserDto;
import com.gerson.users.user.domain.entities.User;
import com.gerson.users.user.infra.inputport.UserGetAllInputPort;
import com.gerson.users.user.infra.outputport.UserPersistenceInputPort;
import com.gerson.users.user.utils.UserMapper;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserGetAllUserCase implements UserGetAllInputPort {

    private final UserPersistenceInputPort userRepository;

    private final UserMapper userMapper;

    public UserGetAllUserCase(UserPersistenceInputPort userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserDto> getAll() throws AppExceptionInternalServerError, AppExceptionNotFound {
        List<User> users = userRepository.getAll();

        return users.stream()
                .map(userMapper::createDto)
                .toList();
    }
}
