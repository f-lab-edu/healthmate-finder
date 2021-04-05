package com.hamryt.helparty.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String phoneNumber;

    private String addressCode;

    private String addressDetail;

    @Builder
    public UserDTO(String email, String name, String password,
        String addressCode, String addressDetail, String phoneNumber
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
    }
}
