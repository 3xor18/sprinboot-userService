package com.gerson.users.user.infra.outputadapter;

import com.gerson.users.user.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserH2Repository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email,String password);
}
