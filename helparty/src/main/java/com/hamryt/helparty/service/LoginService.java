package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;

public interface LoginService {

    UserDto login(String email, String password);
}
