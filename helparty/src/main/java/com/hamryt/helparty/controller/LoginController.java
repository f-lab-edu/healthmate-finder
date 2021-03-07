package com.hamryt.helparty.controller;


import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.service.LoginService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public LoginResponse login(
        @Valid @RequestBody LoginRequest loginRequest,
        HttpSession session
    ) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UserDto userDto = loginService.login(email, password, session);

        return new LoginResponse(userDto);
    }

    @Getter
    @RequiredArgsConstructor
    private static class LoginRequest{

        @NonNull
        private String email;

        @NonNull
        private String password;
    }

    @Getter
    @RequiredArgsConstructor
    private static class LoginResponse{

        @NonNull
        private UserDto userDto;

    }

}
