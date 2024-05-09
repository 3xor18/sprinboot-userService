package com.gerson.users.user.utils;

import com.gerson.users.phone.domain.dtos.PhoneResponseDTO;
import com.gerson.users.phone.utils.PhoneMapper;
import com.gerson.users.user.domain.dtos.UserDto;
import com.gerson.users.user.domain.dtos.UserSavedResponseDTO;
import com.gerson.users.user.domain.dtos.UserToSaveDTO;
import com.gerson.users.phone.domain.entities.Phone;
import com.gerson.users.user.domain.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final PhoneMapper phoneMapper;

    public UserMapper(PasswordEncoder passwordEncoder, PhoneMapper phoneMapper) {
        this.passwordEncoder = passwordEncoder;
        this.phoneMapper = phoneMapper;
    }

    public User createEntity(
            Long id,
            UserToSaveDTO dto,
            List<Phone> phones,
            String token,
            LocalDateTime modified,
            LocalDateTime last_login) {

        return User.builder()
                .id(id)
                .email(dto.getEmail())
                .name(dto.getName())
                .phone(phones)
                .password(passwordEncoder.encode(dto.getPassword()))
                .uuid(UUID.randomUUID())
                .token(token)
                .created(LocalDateTime.now())
                .modified(modified)
                .last_login(last_login)
                .build();
    }

    public UserSavedResponseDTO createSaveResponse(User user,boolean isActive) {
        return UserSavedResponseDTO.builder()
                .id(user.getUuid())
                .created(user.getCreated())
                .modified(user.getModified())
                .last_login(user.getLast_login())
                .token(user.getToken())
                .isactive(isActive)
                .build();
    }

    public UserDto createDto(User user) {
        List<PhoneResponseDTO> phones = user.getPhone().stream()
                .map(phoneMapper::createDTO)
                .toList();

        return UserDto.builder()
                .id(user.getUuid())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .phones(phones)
                .created(user.getCreated())
                .modified(user.getModified())
                .last_login(user.getLast_login())
                .build();
    }


}
