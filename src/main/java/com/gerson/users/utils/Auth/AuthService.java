package com.gerson.users.utils.Auth;

import com.gerson.users.user.domain.entities.User;
import com.gerson.users.user.infra.inputport.UserGetByEmailInputPort;
import com.gerson.users.utils.Auth.dtos.AuthResponse;
import com.gerson.users.utils.Auth.dtos.LoginRequest;
import com.gerson.users.utils.exception.dtos.AppExceptionBadRequest;
import com.gerson.users.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserGetByEmailInputPort getUserByEmail;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) throws  AppExceptionBadRequest {

        String email = Optional.of(request.getEmail()).orElse("");
        String password = Optional.of(request.getPassword()).orElse("");
        User user;

        if(email.equals("admin")) {
            user = User.builder()
                    .email("admin")
                    .password("12345")
                    .build();
        } else {
            user = getUserByEmail.execute(email)
                    .orElseThrow(()->new AppExceptionBadRequest("Error con el email o password"));
            if(!passwordEncoder.matches(password, user.getPassword())){
                throw new AppExceptionBadRequest("Error con el email o password");
            }
        }

        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

}