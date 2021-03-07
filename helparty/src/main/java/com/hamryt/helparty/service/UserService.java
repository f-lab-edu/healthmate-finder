package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;

public interface UserService {

    void insertUser(UserDto userDto);

    boolean findByEmailAndPassword(String email, String password);

    UserDto findUserByEmail(String email);

}
