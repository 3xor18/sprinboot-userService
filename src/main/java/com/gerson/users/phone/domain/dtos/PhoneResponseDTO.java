package com.gerson.users.phone.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneResponseDTO {

    private String number;
    private String citycode;
    private String contrycode;
}
