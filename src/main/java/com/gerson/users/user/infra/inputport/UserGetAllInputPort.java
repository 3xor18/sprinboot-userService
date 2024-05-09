package com.gerson.users.user.infra.inputport;

import com.gerson.users.user.domain.dtos.UserDto;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;

import java.util.List;

public interface UserGetAllInputPort {

    List<UserDto> getAll() throws AppExceptionInternalServerError, AppExceptionNotFound;
}
