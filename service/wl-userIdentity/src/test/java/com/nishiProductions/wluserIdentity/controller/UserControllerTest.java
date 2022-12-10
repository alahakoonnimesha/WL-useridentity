package com.nishiProductions.wluserIdentity.controller;

import com.google.gson.Gson;
import com.nishiProductions.wluserIdentity.WlUserIdentityApplication;
import com.nishiProductions.wluserIdentity.dto.ResponseDto;
import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.dto.requestDtos.LoginRequestDto;
import com.nishiProductions.wluserIdentity.service.UserService;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc()
public class UserControllerTest extends WlUserIdentityApplication {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    UserService userService;

    UserDto userDto = null;

    @Autowired
    private Gson gson;

    ResponseDto responseDto = null;

    LoginRequestDto loginRequestDto = null;

    @BeforeEach
    public void setUp() {
        userDto = new UserDto();
        userDto.setUserRegNo("U01");
        userDto.setUserName("abc test");
        userDto.setContactNo("+94779035729");
        userDto.setEmail("abc@gmail.com");
        userDto.setPassword("12345");
        userDto.setIsActive(Boolean.TRUE);

        responseDto = new ResponseDto();
        responseDto.setResponseDto(userDto);


        loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail("gfr@02.com");
        loginRequestDto.setPassword("12345");
    }

    @AfterEach
    public void tearDown() {
        userDto = null;
        responseDto = null;
        loginRequestDto = null;
    }

    @Test
    public void testSaveUserSuccess() throws Exception {

        // userService.saveUser to respond back with responseDto
//        Mockito.when(userService.saveUser(Mockito.any(UserDto.class))).thenReturn(new ResponseDto());

        // Send course as body to /students/Student1/courses
        RequestBuilder request = MockMvcRequestBuilders.post("/v1/user/save").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(gson.toJson(userDto));
        MvcResult result = mockMvc.perform(request).andDo(print())
                .andExpect(status().isOk()).andReturn();
//                .andExpect(jsonPath("$.responseDto.email").value(userDto.getEmail())).andReturn();

        Assertions.assertThat(result).isNotNull();

    }

    @Test
    public void testGetAllUsersSuccess() throws Exception {

//        Mockito.when(userService.getAllUsers()).thenReturn(new ResponseDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/user/allUsers").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseDto", Matchers.hasSize(Matchers.greaterThan(0))));

    }

    @Test
    public void testLoginWithoutAuthUserSuccess() throws Exception {

//        Mockito.when(userService.getAllUsers()).thenReturn(new ResponseDto());

        // Send course as body to /students/Student1/courses
        RequestBuilder request = MockMvcRequestBuilders.post("/v1/user/loginWithoutAuth").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(gson.toJson(loginRequestDto));
        MvcResult result = mockMvc.perform(request).andDo(print())
                .andExpect(status().isOk()).andReturn();

        Assertions.assertThat(result).isNotNull();

    }
}
