package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.user.UserDto;

public interface LoginService {

    UserDto login(String email, String password);

    String getLoginEmail();
}
