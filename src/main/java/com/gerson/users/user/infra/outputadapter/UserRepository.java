package com.gerson.users.user.infra.outputadapter;

import com.gerson.users.phone.domain.entities.Phone;
import com.gerson.users.phone.infra.outputport.PhonePersistenceOutputPort;
import com.gerson.users.user.domain.dtos.UserToSaveDTO;
import com.gerson.users.user.domain.entities.User;
import com.gerson.users.user.infra.outputport.UserPersistenceInputPort;
import com.gerson.users.user.utils.UserMapper;
import com.gerson.users.utils.exception.dtos.AppExceptionBadRequest;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@Slf4j
public class UserRepository implements UserPersistenceInputPort {

    private final UserH2Repository h2Repository;

    private final UserMapper userMapper;

    private final PhonePersistenceOutputPort phoneRepository;


    public UserRepository(UserH2Repository h2Repository, UserMapper userMapper, PhonePersistenceOutputPort phoneRepository) {
        this.h2Repository = h2Repository;
        this.userMapper = userMapper;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public User save(UserToSaveDTO dto) throws AppExceptionInternalServerError, AppExceptionBadRequest {
        try {
            log.info("Trying to save a User");

            log.info("Validating user is not saved");
            boolean isEmailSaved = h2Repository.findByEmail(dto.getEmail()).isPresent();
            if (isEmailSaved) {
                throw new AppExceptionBadRequest("El Email ya se encuentra registrado");
            }

            List<Phone> phones = saveAllPhones(dto);
            String token = getToken();
            User userToSave = userMapper.createEntity(
                    null,
                    dto,
                    phones,
                    token,
                    null,
                    getToday());

            User newUser = h2Repository.save(userToSave);
            log.info("A new User was created with ID: " + newUser.getId());

            return newUser;
        } catch (AppExceptionBadRequest e) {
            log.error("An error occurred while trying to save the User...");
            log.error("ErrorMsg: " + e.getMessage());
            throw new AppExceptionBadRequest(e.getMessage());
        }catch (Exception e) {
            log.error("An error occurred while trying to save the User...");
            log.error("ErrorClass:" + e.getClass());
            log.error("ErrorMsg: " + e.getMessage());
            throw new AppExceptionInternalServerError("Ocurrio un Error al intentar guardar un usuario");
        }
    }

    @Override
    public List<User> getAll() throws AppExceptionInternalServerError, AppExceptionNotFound {
        try {
            log.info("Trying to get all Users");

            List<User> users = h2Repository.findAll();

            if(users.isEmpty()) {
                throw new AppExceptionNotFound("No existen usuarios");
            }

            return users.stream()
                    .map(user -> {
                        user.setLast_login(getToday());
                        return h2Repository.save(user);
                    }).toList();
        }  catch (AppExceptionNotFound e) {
            log.error("An error occurred while trying to get all Users...");
            log.error("ErrorMsg" + e.getMessage());
            throw new AppExceptionNotFound(e.getMessage());
        }catch (Exception e) {
            log.error("An error occurred while trying to get all Users...");
            log.error("ErrorClass:" + e.getClass());
            log.error("ErrorMsg: " + e.getMessage());
            throw new AppExceptionInternalServerError("Ocurrio un Error al intentar consultar todos los usuarios");
        }
    }

    @Override
    public User getById(Long id) throws AppExceptionInternalServerError, AppExceptionNotFound {
        try {
            log.info("Trying to get a User");

            User user = h2Repository.findById(id)
                    .orElseThrow(()->new AppExceptionNotFound("No existe el usuario: " + id));

            user.setLast_login(getToday());

            return h2Repository.save(user);
        } catch (AppExceptionNotFound e) {
            log.error("An error occurred while trying to get the User...");
            log.error("ErrorMsg: " + e.getMessage());
            throw new AppExceptionNotFound(e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while trying to get the User...");
            log.error("ErrorClass:" + e.getClass());
            log.error("ErrorMsg: " + e.getMessage());
            throw new AppExceptionInternalServerError("Ocurrio un Error al intentar consultar un usuario");
        }
    }

    @Override
    public User update(Long id,UserToSaveDTO dto) throws AppExceptionInternalServerError, AppExceptionNotFound {
        try {
            log.info("Trying to Update a User");

            User user = h2Repository.findById(id)
                    .orElseThrow(()->new AppExceptionNotFound("No existe el usuario: " + id));


            List<Phone> phones = saveAllPhones(dto);
            String token = getToken();
            User userToSave = userMapper.createEntity(
                    null,
                    dto,
                    phones,
                    token,
                    getToday(),
                    getToday());

            userToSave.setId(user.getId());
            userToSave.setUuid(user.getUuid());

            return h2Repository.save(userToSave);
        } catch (AppExceptionNotFound e) {
            log.error("An error occurred while trying to Uptade the User...");
            log.error("ErrorMsg: " + e.getMessage());
            throw new AppExceptionNotFound(e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while trying to Uptade the User...");
            log.error("ErrorClass:" + e.getClass());
            log.error("ErrorMsg: " + e.getMessage());
            throw new AppExceptionInternalServerError("Ocurrio un Error al intentar modificar un usuario");
        }
    }

    @Override
    public void delete(Long id) throws AppExceptionNotFound, AppExceptionInternalServerError {
        try {
            log.info("Trying to delete a User");

            h2Repository.findById(id)
                    .orElseThrow(()->new AppExceptionNotFound("No existe el usuario: " + id));

            h2Repository.deleteById(id);
        } catch (AppExceptionNotFound e) {
            log.error("An error occurred while trying to delete the User...");
            log.error("ErrorMsg: " + e.getMessage());
            throw new AppExceptionNotFound(e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while trying to delete the User...");
            log.error("ErrorClass:" + e.getClass());
            log.error("ErrorMsg: " + e.getMessage());
            throw new AppExceptionInternalServerError("Ocurrio un Error al intentar consultar un usuario");
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return h2Repository.findByEmail(email);
    }


    private List<Phone> saveAllPhones(UserToSaveDTO dto) {
        return dto.getPhones().stream()
                .map(phone -> {
                    try {
                        return phoneRepository.save(phone);
                    } catch (AppExceptionInternalServerError e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }


    private String getToken() {
        return "ABC";
    }

    private LocalDateTime getToday() {
        return LocalDateTime.now();
    }
}
