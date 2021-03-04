package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;

public interface UserService {

    UserDto loginUser(String email, String password);

    void insertUser(UserDto userDto);

}
