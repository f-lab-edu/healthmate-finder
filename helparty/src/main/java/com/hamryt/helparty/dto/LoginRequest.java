package com.hamryt.helparty.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
