package com.gerson.users.utils.exception.dtos;

import org.springframework.http.HttpStatus;

public class AppExceptionInternalServerError extends GlobalAppException {

    public AppExceptionInternalServerError(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
