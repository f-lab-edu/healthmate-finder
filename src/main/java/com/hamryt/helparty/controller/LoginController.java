package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.login.LoginDTO;
import com.hamryt.helparty.service.login.LoginService;
import com.hamryt.helparty.service.login.factory.LoginFactory;
import javax.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("login")
public class LoginController {
    
    private LoginService loginService;
    private final LoginFactory loginFactory;
    
    @PostMapping
    public LoginDTO login(
        @Valid @RequestBody LoginRequest loginRequest
    ) {
        loginService = loginFactory.createLoginService(loginRequest.getUserType());
        return loginService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
    
    @Getter
    @NoArgsConstructor
    @RequiredArgsConstructor
    private static class LoginRequest {
        
        @NonNull
        private String email;
        
        @NonNull
        private String password;
        
        @NonNull
        private UserType userType;
        
    }
    
}
