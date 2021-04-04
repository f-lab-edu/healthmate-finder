package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.user.request.SignUpRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserReqeust;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.login.LoginService;
import com.hamryt.helparty.service.user.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signUpUser(
        @Valid @RequestBody SignUpRequest resource
    ) {
        userService.insertUser(resource);
    }

    @LoginValidation
    @PutMapping("{id}")
    public UpdateUserResponse updateUser(
        @PathVariable("id") Long id,
        @Valid @RequestBody UpdateUserReqeust updateUserRequest
    ) {
        loginService.validateUser(id);
        return userService.updateUser(id, updateUserRequest);
    }

}
