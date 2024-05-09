package com.gerson.users.user.infra.outputport;

import com.gerson.users.user.domain.dtos.UserToSaveDTO;
import com.gerson.users.user.domain.entities.User;
import com.gerson.users.utils.exception.dtos.AppExceptionBadRequest;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;

import java.util.List;
import java.util.Optional;

public interface UserPersistenceInputPort {

    User save(UserToSaveDTO dto) throws AppExceptionInternalServerError, AppExceptionBadRequest;

    List<User> getAll() throws AppExceptionInternalServerError, AppExceptionNotFound;

    User getById(Long id) throws AppExceptionInternalServerError, AppExceptionNotFound;

    User update(Long id,UserToSaveDTO dto) throws AppExceptionInternalServerError, AppExceptionNotFound;

    void delete(Long id) throws AppExceptionNotFound, AppExceptionInternalServerError;

    Optional<User> getByEmail(String email);

}
