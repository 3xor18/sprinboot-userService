package com.gerson.users.user.application;

import com.gerson.users.phone.domain.entities.Phone;
import com.gerson.users.user.domain.dtos.UserDto;
import com.gerson.users.user.domain.dtos.UserToSaveDTO;
import com.gerson.users.user.domain.entities.User;
import com.gerson.users.user.infra.outputport.UserPersistenceInputPort;
import com.gerson.users.user.utils.UserMapper;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserUpdateUserCaseTest {

    @Mock
    UserPersistenceInputPort userRepository;
    @Mock
    UserMapper userMapper;
    @InjectMocks
    UserUpdateUserCase testClass;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void update_Error_Not_Found() throws AppExceptionInternalServerError, AppExceptionNotFound {
        Long id = 1L;
        UserToSaveDTO dto = new UserToSaveDTO();
        when(userRepository.update(id,dto)).thenThrow(AppExceptionNotFound.class);

        assertThrows(AppExceptionNotFound.class,()->{
            testClass.update(id,dto);
        });
    }

    @Test
    void update_Error_Internal() throws AppExceptionInternalServerError, AppExceptionNotFound {
        Long id = 1L;
        UserToSaveDTO dto = new UserToSaveDTO();
        when(userRepository.update(id,dto)).thenThrow(AppExceptionInternalServerError.class);

        assertThrows(AppExceptionInternalServerError.class,()->{
            testClass.update(id,dto);
        });
    }

    @Test
    void update_Error_OK() throws AppExceptionInternalServerError, AppExceptionNotFound {
        Long id = 1L;
        String name = "Gerson";
        LocalDateTime today = LocalDateTime.now();
        UserToSaveDTO dto = new UserToSaveDTO();
        Phone phone = Phone.builder()
                .id(1L)
                .citycode("ABC")
                .number("ABC")
                .contrycode("ABC")
                .build();
        List<Phone> phones = Arrays.asList(phone);

        User dtoResponse = User.builder()
                .id(id)
                .name(name)
                .password("12345")
                .email("ABC")
                .last_login(today)
                .modified(today)
                .created(today)
                .phone(phones)
                .build();

        UserDto dtoResult = UserDto.builder()
                .name(name)
                .password("12345")
                .email("ABC")
                .last_login(today)
                .modified(today)
                .created(today)
                .build();

        when(userRepository.update(id,dto)).thenReturn(dtoResponse);
        when(userMapper.createDto(dtoResponse)).thenReturn(dtoResult);

        UserDto result = testClass.update(id,dto);
        assertEquals(result.getName(),name);

    }
}