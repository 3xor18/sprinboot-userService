package com.gerson.users.user.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gerson.users.phone.domain.dtos.PhoneResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<PhoneResponseDTO> phones;

    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private String token;
}
