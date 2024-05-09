package com.gerson.users.phone.infra.outputport;

import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.phone.domain.dtos.PhoneToSaveDTO;
import com.gerson.users.phone.domain.entities.Phone;

public interface PhonePersistenceOutputPort {

    Phone save(PhoneToSaveDTO dto) throws AppExceptionInternalServerError;
}
