package com.gerson.users.utils.Auth;

import com.gerson.users.utils.Auth.dtos.AuthResponse;
import com.gerson.users.utils.Auth.dtos.LoginRequest;
import com.gerson.users.utils.exception.dtos.AppExceptionBadRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) throws AppExceptionBadRequest {
        log.info("Realizando peticion login");
        return ResponseEntity.ok(authService.login(request));
    }

}
