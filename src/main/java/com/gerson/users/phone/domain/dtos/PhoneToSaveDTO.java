package com.gerson.users.phone.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import static com.gerson.users.utils.AppConstants.REGEX_ONLY_NUMBERS;

@Getter
@Setter
public class PhoneToSaveDTO {

    @Pattern(regexp = REGEX_ONLY_NUMBERS, message = "phones.number  solo acepta números")
    @NotNull(message = "phones.number es requerido")
    private String number;

    @Pattern(regexp = REGEX_ONLY_NUMBERS, message = "phones.citycode solo acepta números")
    @NotNull(message = "phones.citycode es requerido")
    private String citycode;

    @Pattern(regexp = REGEX_ONLY_NUMBERS, message = "phones.contrycode  solo acepta números")
    @NotNull(message = "phones.contrycode es requerido")
    private String contrycode;
}
