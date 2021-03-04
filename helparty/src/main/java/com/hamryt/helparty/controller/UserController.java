package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.dto.UserDto.Status;
import com.hamryt.helparty.service.UserService;
import com.hamryt.helparty.util.SessionUtil;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LoginResponse> login(
        @Valid @RequestBody LoginRequest LoginRequest,
        HttpSession session
    ) {

        ResponseEntity<LoginResponse> responseEntity = null;
        String email = LoginRequest.getEmail();
        String password = LoginRequest.getPassword();
        UserDto userInfo = userService.loginUser(email, password);
        LoginResponse loginResponse;

        if (userInfo == null) {
            loginResponse = LoginResponse.FAIL;
            responseEntity = new ResponseEntity<LoginResponse>(loginResponse,
                HttpStatus.UNAUTHORIZED);
        } else if (UserDto.Status.DEFAULT.equals(userInfo.getStatus())) {
            loginResponse = LoginResponse.success(userInfo);
            SessionUtil.setLoginUserEmail(session, email);
            responseEntity = new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        } else {
            log.error("Login Error");
            throw new RuntimeException("Login Error");
        }

        return responseEntity;
    }
    //------------------------ request 객체 ---------------------------

    @Getter
    @AllArgsConstructor
    @RequiredArgsConstructor
    private static class LoginResponse {

        enum LoginStatus {
            SUCCESS, FAIL
        }

        @NonNull
        private LoginStatus loginStatus;

        private UserDto userDto;

        private static final LoginResponse FAIL = new LoginResponse(LoginStatus.FAIL);

        private static LoginResponse success(UserDto userDto) {
            return new LoginResponse(LoginStatus.SUCCESS, userDto);
        }
    }

    //------------------------ request 객체 ---------------------------

    @Getter
    private static class LoginRequest {

        @NotNull
        private String email;
        @NotNull
        private String password;
    }

}
