package com.hamryt.helparty.controller;

import com.hamryt.helparty.aop.ValidateUser;
import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.user.request.SignUpUserRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserRequest;
import com.hamryt.helparty.dto.user.request.UserDeleteRequest;
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
    @PutMapping("{id}")
    public UpdateUserResponse updateUser(
        @ValidateUser(type = UserType.USER) @PathVariable("id") Long id,
        @Valid @RequestBody UpdateUserRequest updateUserRequest
    ) {
        return userService.updateUser(id, updateUserRequest);
    }
    
    @LoginValidation
    @DeleteMapping("{id}")
    public void deleteUser(
        @ValidateUser(type = UserType.USER) @PathVariable("id") Long id,
        @Valid @RequestBody UserDeleteRequest userDeleteRequest
    ) {
        userService.deleteUser(userDeleteRequest);
    }
}
