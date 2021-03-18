package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;

public interface LoginService {

    UserDto login(String email, String password);

    String getLoginId();

    boolean checkAuth(UserDto userDto);
}
