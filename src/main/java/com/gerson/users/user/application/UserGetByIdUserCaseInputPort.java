package com.gerson.users.user.application;

import com.gerson.users.user.domain.dtos.UserDto;
import com.gerson.users.user.infra.inputport.UserGetByIdInputPort;
import com.gerson.users.user.infra.outputport.UserPersistenceInputPort;
import com.gerson.users.user.utils.UserMapper;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserGetByIdUserCaseInputPort implements UserGetByIdInputPort {

    private final UserPersistenceInputPort repository;
    private final UserMapper mapper;

    public UserGetByIdUserCaseInputPort(UserPersistenceInputPort repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDto getByID(Long id) throws AppExceptionInternalServerError, AppExceptionNotFound {
        return mapper.createDto(repository.getById(id));
    }
}
