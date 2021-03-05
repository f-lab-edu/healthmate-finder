package com.hamryt.helparty.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    enum LoginStatus {
        SUCCESS, FAIL
    }

    private final LoginStatus loginStatus;

    private UserDto userDto;

    public static final LoginResponse FAIL = new LoginResponse(
        LoginStatus.FAIL);

    public static LoginResponse success(UserDto userDto) {
        return new LoginResponse(LoginStatus.SUCCESS, userDto);
    }

}
