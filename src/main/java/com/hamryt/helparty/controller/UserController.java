package com.hamryt.helparty.controller;

import com.hamryt.helparty.argumentresolver.LoginId;
import com.hamryt.helparty.dto.user.request.SignUpUserRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserRequest;
import com.hamryt.helparty.dto.user.response.SignUpUserResponse;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.session.SessionService;
import com.hamryt.helparty.service.user.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    
    private final SessionService sessionService;
    private final UserService userService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpUserResponse signUpUser(
        @Valid @RequestBody SignUpUserRequest resource
    ) {
        return userService.insertUser(resource);
    }
    
    @LoginValidation
    @PutMapping
    public UpdateUserResponse updateUser(
        @LoginId Long loginId,
        @Valid @RequestBody UpdateUserRequest updateUserRequest
    ) {
        return userService.updateUser(loginId, updateUserRequest);
    }
    
    @LoginValidation
    @DeleteMapping
    public void deleteUser(
        @LoginId Long loginId
    ) {
        userService.deleteUser(loginId);
    }
}
