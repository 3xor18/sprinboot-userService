package com.gerson.users.user.infra.inputport;

import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;

public interface UserDeleteInputPort {
    void delete(Long id) throws AppExceptionInternalServerError, AppExceptionNotFound;
}
