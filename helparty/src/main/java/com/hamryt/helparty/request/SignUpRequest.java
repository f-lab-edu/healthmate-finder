package com.hamryt.helparty.request;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String addressCode;

    @NotEmpty
    private String addressDetail;

}
