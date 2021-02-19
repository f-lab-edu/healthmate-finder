package com.hamryt.helparty.interfaces;

import com.hamryt.helparty.application.UserService;
import com.hamryt.helparty.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(com.hamryt.helparty.interfaces.UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void create() throws Exception {

        Long id = 1004L;
        String email = "tester@example.com";
        String name = "tester";
        String password = "test";

        User mockUser = User.builder()
                .id(id)
                .email(email)
                .name(name)
                .password(password)
                .build();

        given(userService.registerUser(email, name, password)).willReturn(mockUser);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\", \"name\":\"tester\", \"password\":\"test\"}"))
                .andExpect(header().string("location", "/users/1004"))
                .andExpect(status().isCreated());

        verify(userService).registerUser(
                eq("tester@example.com"),
                eq("tester"),
                eq("test"));

    }

}