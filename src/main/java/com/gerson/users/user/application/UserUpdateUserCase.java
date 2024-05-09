package com.gerson.users.user.application;

import com.gerson.users.user.domain.dtos.UserDto;
import com.gerson.users.user.domain.dtos.UserToSaveDTO;
import com.gerson.users.user.domain.entities.User;
import com.gerson.users.user.infra.inputport.UserUpdateInputPort;
import com.gerson.users.user.infra.outputport.UserPersistenceInputPort;
import com.gerson.users.user.utils.UserMapper;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserUpdateUserCase implements UserUpdateInputPort {

    private final UserPersistenceInputPort userRepository;

    private final UserMapper userMapper;

    public UserUpdateUserCase(UserPersistenceInputPort userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto update(Long id, UserToSaveDTO dto) throws AppExceptionInternalServerError, AppExceptionNotFound {
        User user = userRepository.update(id,dto);
        return userMapper.createDto(user);
    }
}
