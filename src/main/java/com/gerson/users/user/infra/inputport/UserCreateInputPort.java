package com.gerson.users.user.infra.inputport;

import com.gerson.users.user.domain.dtos.UserSavedResponseDTO;
import com.gerson.users.user.domain.dtos.UserToSaveDTO;
import com.gerson.users.utils.exception.dtos.AppExceptionBadRequest;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;

public interface UserCreateInputPort {

    UserSavedResponseDTO persist(UserToSaveDTO dto) throws AppExceptionInternalServerError, AppExceptionBadRequest;
}
