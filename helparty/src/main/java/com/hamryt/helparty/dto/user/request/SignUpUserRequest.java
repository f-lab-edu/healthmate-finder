package com.hamryt.helparty.dto.user.request;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpUserRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String addressCode;

    @NotEmpty
    private String addressDetail;

}
