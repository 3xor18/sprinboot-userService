package com.gerson.users.phone.utils;

import com.gerson.users.phone.domain.dtos.PhoneResponseDTO;
import com.gerson.users.phone.domain.dtos.PhoneToSaveDTO;
import com.gerson.users.phone.domain.entities.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {

    public Phone createEntity(
            Long id,
            PhoneToSaveDTO dto) {

        return Phone.builder()
                .id(id)
                .citycode(dto.getCitycode())
                .contrycode(dto.getContrycode())
                .number(dto.getNumber())
                .build();
    }

    public PhoneResponseDTO createDTO(Phone entity) {
        return PhoneResponseDTO.builder()
                .citycode(entity.getCitycode())
                .contrycode(entity.getContrycode())
                .number(entity.getNumber())
                .build();
    }


}
