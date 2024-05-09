package com.gerson.users.user.infra.inputport;

import com.gerson.users.user.domain.dtos.UserDto;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;

public interface UserGetByIdInputPort {

    UserDto getByID(Long id) throws AppExceptionInternalServerError, AppExceptionNotFound;
}
