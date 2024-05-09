package com.gerson.users.user.infra.inputadapter;

import com.gerson.users.user.application.UserGetByIdUserCaseInputPort;
import com.gerson.users.user.domain.dtos.UserSaveWrapper;
import com.gerson.users.user.domain.dtos.UserToSaveDTO;
import com.gerson.users.user.domain.dtos.UserWrapper;
import com.gerson.users.user.domain.dtos.UsersWrapper;
import com.gerson.users.user.infra.inputport.UserCreateInputPort;
import com.gerson.users.user.infra.inputport.UserDeleteInputPort;
import com.gerson.users.user.infra.inputport.UserGetAllInputPort;
import com.gerson.users.user.infra.inputport.UserUpdateInputPort;
import com.gerson.users.utils.exception.dtos.AppExceptionBadRequest;
import com.gerson.users.utils.exception.dtos.AppExceptionInternalServerError;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserCreateInputPort createUser;

    private final UserGetAllInputPort getAllUsers;

    private final UserGetByIdUserCaseInputPort getById;

    private final UserDeleteInputPort delete;
    private final UserUpdateInputPort update;

    public UserController(UserCreateInputPort createUser, UserGetAllInputPort getAllUsers, UserGetByIdUserCaseInputPort getById, UserDeleteInputPort delete, UserUpdateInputPort update) {
        this.createUser = createUser;
        this.getAllUsers = getAllUsers;
        this.getById = getById;
        this.delete = delete;
        this.update = update;
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersWrapper> getAll() throws AppExceptionInternalServerError, AppExceptionNotFound {
        UsersWrapper response = UsersWrapper.builder()
                .mensaje("Usuarios encontrados")
                .data(getAllUsers.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }


    @GetMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserWrapper> getByID(@PathVariable Long id) throws AppExceptionInternalServerError, AppExceptionNotFound {
        UserWrapper response = UserWrapper.builder()
                .mensaje("Usuario " + id + " Encontrado")
                .data(getById.getByID(id))
                .build();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserSaveWrapper> crear(@Valid @RequestBody UserToSaveDTO dto) throws AppExceptionInternalServerError, AppExceptionBadRequest {
        UserSaveWrapper response = UserSaveWrapper.builder()
                .mensaje("Usuario creado con Exito")
                .data(createUser.persist(dto))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserWrapper> modificar(@PathVariable Long id, @RequestBody UserToSaveDTO dto) throws AppExceptionInternalServerError, AppExceptionNotFound {
        UserWrapper response = UserWrapper.builder()
                .mensaje("Usuario " + id + " modificado")
                .data(update.update(id, dto))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserWrapper> borrar(@PathVariable Long id) throws AppExceptionInternalServerError, AppExceptionNotFound {
        delete.delete(id);

        UserWrapper response = UserWrapper.builder()
                .mensaje("Usuario " + id + " Eliminado")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

}
