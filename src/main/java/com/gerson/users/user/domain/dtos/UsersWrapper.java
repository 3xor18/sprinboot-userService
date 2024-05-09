package com.gerson.users.user.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UsersWrapper {


    private String mensaje;
    private List<UserDto> data;
}
