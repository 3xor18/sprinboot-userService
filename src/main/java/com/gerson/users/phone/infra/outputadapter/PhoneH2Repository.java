package com.gerson.users.phone.infra.outputadapter;

import com.gerson.users.phone.domain.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneH2Repository extends JpaRepository<Phone,Long> {
}
