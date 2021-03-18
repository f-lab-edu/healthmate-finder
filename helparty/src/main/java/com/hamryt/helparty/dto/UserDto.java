package com.hamryt.helparty.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String addressCode;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
