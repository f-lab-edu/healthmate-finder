package com.hamryt.helparty.dto.user.request;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {

    private Long id;

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
