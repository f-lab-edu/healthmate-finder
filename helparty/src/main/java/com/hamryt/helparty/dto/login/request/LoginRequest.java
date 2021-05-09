package com.hamryt.helparty.dto.login.request;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {
    
    @NotNull
    private String email;
    
    @NotNull
    private String password;
    
    @Builder
    public LoginRequest(String email, String password){
        this.email = email;
        this.password = password;
       
    }
}
