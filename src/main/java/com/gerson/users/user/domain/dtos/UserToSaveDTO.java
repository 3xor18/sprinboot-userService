package com.gerson.users.user.domain.dtos;

import com.gerson.users.phone.domain.dtos.PhoneToSaveDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.gerson.users.utils.AppConstants.*;

@Getter
@Setter
public class UserToSaveDTO {
    @NotNull(message = "name es requerido")
    @Pattern(regexp = REGEX_ONLY_LETTERS, message = "name  solo acepta letras")
    private String name;

    @NotNull(message = "email es requerido")
    @Pattern(regexp = REGEX_EMAIL, message = "email tiene error de formato")
    private String email;

    @NotNull(message = "password es requerido")
    private String password;

    @Valid
    @NotNull(message = "phones es requerido")
    private List<PhoneToSaveDTO> phones;
}
