package com.gerson.users.utils.exception.dtos;

import org.springframework.http.HttpStatus;

public class AppExceptionBadRequest extends GlobalAppException {

    public AppExceptionBadRequest(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
