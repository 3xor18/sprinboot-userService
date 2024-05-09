package com.gerson.users.utils.exception.handlers;

import com.gerson.users.utils.exception.dtos.AppExceptionBadRequest;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppExceptionBadRequest.class)
    @ResponseBody
    public ResponseEntity<?> handleAppExceptionBadRequest(AppExceptionBadRequest ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AppExceptionInternalServerError.class)
    @ResponseBody
    public ResponseEntity<?> handler(AppExceptionInternalServerError ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(AppExceptionNotFound.class)
    @ResponseBody
    public ResponseEntity<?> handler(AppExceptionNotFound ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<?> handler(HttpMediaTypeNotSupportedException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "El Content-Type no es soportado, debes incluir Json");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Error de validaciones o faltan campos en el request");

        List<String> errors =  ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
