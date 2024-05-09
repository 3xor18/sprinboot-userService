package com.gerson.users.user.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserWrapper {

    private String mensaje;
    private UserDto data;
}
