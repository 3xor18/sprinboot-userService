package com.gerson.users.user.infra.inputadapter;

import com.gerson.users.user.application.UserGetByIdUserCaseInputPort;
import com.gerson.users.user.domain.dtos.UserDto;
import com.gerson.users.user.infra.inputport.UserCreateInputPort;
import com.gerson.users.user.infra.inputport.UserDeleteInputPort;
import com.gerson.users.user.infra.inputport.UserGetAllInputPort;
import com.gerson.users.user.infra.inputport.UserUpdateInputPort;
import com.gerson.users.utils.exception.dtos.AppExceptionNotFound;
import com.gerson.users.utils.jwt.JwtAuthenticationFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @MockBean
    UserCreateInputPort createUser;
    @MockBean
    UserGetAllInputPort getAllUsers;
    @MockBean
    UserGetByIdUserCaseInputPort getById;
    @MockBean
    UserDeleteInputPort delete;
    @MockBean
    UserUpdateInputPort update;
    @MockBean
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllUser_OK() throws Exception
    {
        UserDto userMock = UserDto.builder().email("EmailMock").build();
        List<UserDto> users = Arrays.asList(userMock);

        when(getAllUsers.getAll()).thenReturn(users);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[*]").isNotEmpty());
    }

    @Test
    public void getAllUser_error_content_type() throws Exception
    {
        UserDto userMock = UserDto.builder().email("EmailMock").build();
        List<UserDto> users = Arrays.asList(userMock);

        when(getAllUsers.getAll()).thenReturn(users);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/user")
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").exists());
    }

    @Test
    public void getAllUser_error_Not_Found() throws Exception
    {
        when(getAllUsers.getAll()).thenThrow(AppExceptionNotFound.class);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}