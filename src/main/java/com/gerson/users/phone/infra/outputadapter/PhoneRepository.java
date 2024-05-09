package com.gerson.users.phone.infra.outputadapter;

import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.phone.domain.dtos.PhoneToSaveDTO;
import com.gerson.users.phone.domain.entities.Phone;
import com.gerson.users.phone.infra.outputport.PhonePersistenceOutputPort;
import com.gerson.users.phone.utils.PhoneMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PhoneRepository implements PhonePersistenceOutputPort {
    private final PhoneH2Repository phoneH2Repository;
    private final PhoneMapper phoneMapper;

    public PhoneRepository(PhoneH2Repository phoneH2Repository, PhoneMapper phoneMapper) {
        this.phoneH2Repository = phoneH2Repository;
        this.phoneMapper = phoneMapper;
    }

    @Override
    @Transactional
    public Phone save(PhoneToSaveDTO dto) throws AppExceptionInternalServerError {
        try {
            log.info("Trying to save the phone: " + dto.getNumber());
            Phone notSaved = phoneMapper.createEntity(null, dto);
            Phone saved = phoneH2Repository.save(notSaved);
            log.info("A phone has been created with the ID:" + saved.getId());
            return saved;
        } catch (Exception e) {
            log.error("An error occurred while trying to save the phone...");
            log.error("ErrorClass:" + e.getClass());
            log.error("ErrorMsg" + e.getMessage());
            throw new AppExceptionInternalServerError("Ocurrio un Error al intentar guardar un Telefono");
        }

    }
}
