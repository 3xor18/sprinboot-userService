package com.gerson.users.utils.exception.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GlobalAppException extends Exception {

    private final String message;

    public GlobalAppException(HttpStatus httpStatus, String message) {
        super(message);
        this.message = message;
    }

}
