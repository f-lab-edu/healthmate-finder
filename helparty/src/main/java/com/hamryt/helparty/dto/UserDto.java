package com.hamryt.helparty.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class UserDto {

    private Long id;

    @Setter
    private String email;

    @Setter
    private String name;

    @Setter
    private String password;

    @Setter
    private String addressCode;

    @Setter
    private String addressDetail;

    @Builder
    public UserDto(String email, String name, String password, String addressCode,
        String addressDetail) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
    }
}
