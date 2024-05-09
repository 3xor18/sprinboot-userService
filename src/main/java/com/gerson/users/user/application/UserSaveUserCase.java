package com.gerson.users.user.application;

import com.gerson.users.user.domain.dtos.UserSavedResponseDTO;
import com.gerson.users.user.domain.dtos.UserToSaveDTO;
import com.gerson.users.user.domain.entities.User;
import com.gerson.users.user.infra.inputport.UserCreateInputPort;
import com.gerson.users.user.infra.outputport.UserPersistenceInputPort;
import com.gerson.users.user.utils.UserMapper;
import com.gerson.users.utils.exception.dtos.AppExceptionBadRequest;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserSaveUserCase implements UserCreateInputPort {

    private final UserPersistenceInputPort userPersistence;
    private final UserMapper userMapper;

    public UserSaveUserCase(UserPersistenceInputPort userPersistence, UserMapper userMapper) {
        this.userPersistence = userPersistence;
        this.userMapper = userMapper;
    }

    @Override
    public UserSavedResponseDTO persist(UserToSaveDTO dto) throws AppExceptionInternalServerError, AppExceptionBadRequest {
        User newUser = userPersistence.save(dto);
        return userMapper.createSaveResponse(newUser,true);
    }
}
