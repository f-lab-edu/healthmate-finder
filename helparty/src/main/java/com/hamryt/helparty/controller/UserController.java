package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.user.UserUpdateResponse;
import com.hamryt.helparty.request.SignUpRequest;
import com.hamryt.helparty.request.UpdateUserReqeust;
import com.hamryt.helparty.service.LoginService;
import com.hamryt.helparty.service.UserService;
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

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signUpUser(
        @Valid @RequestBody SignUpRequest resource
    ) {
        userService.insertUser(resource);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserUpdateResponse updateUser(
        @PathVariable("id") Long id,
        @Valid @RequestBody UpdateUserReqeust updateUserRequest
    ) {
        loginService.checkAuth(updateUserRequest.getEmail());

        return userService.updateUser(id, updateUserRequest);

    }

}
