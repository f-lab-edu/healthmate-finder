package com.hamryt.helparty.controller;


import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.service.LoginService;
import javax.validation.Valid;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(
        @Valid @RequestBody LoginRequest loginRequest
    ) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UserDto userDto = loginService.login(email, password);

        return new LoginResponse(userDto);
    }

    @Getter
    @RequiredArgsConstructor
    private static class LoginRequest {

        @NonNull
        private String email;

        @NonNull
        private String password;
    }

    @Getter
    @RequiredArgsConstructor
    private static class LoginResponse {

        @NonNull
        private UserDto userDto;

    }

}
