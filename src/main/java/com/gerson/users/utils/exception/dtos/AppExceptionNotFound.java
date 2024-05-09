package com.gerson.users.utils.exception.dtos;

import org.springframework.http.HttpStatus;

public class AppExceptionNotFound extends GlobalAppException {

    public AppExceptionNotFound(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }


}
