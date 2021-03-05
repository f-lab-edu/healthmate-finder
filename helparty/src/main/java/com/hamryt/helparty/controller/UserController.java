package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.LoginRequest;
import com.hamryt.helparty.dto.LoginResponse;
import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(
        @Valid @RequestBody UserDto resource
    ) {

        userService.insertUser(resource);
    }

    @PostMapping("login")
    public LoginResponse login(
        @Valid @RequestBody LoginRequest LoginRequest,
        HttpSession session
    ) {

        String email = LoginRequest.getEmail();
        String password = LoginRequest.getPassword();
        LoginResponse loginResponse = userService.loginUser(email, password, session);

        return loginResponse;
    }

}
