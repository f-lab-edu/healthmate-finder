package com.hamryt.helparty.dto.user.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserDeleteRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
