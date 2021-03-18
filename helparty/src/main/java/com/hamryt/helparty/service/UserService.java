package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.request.SignUpRequest;

public interface UserService {

    void insertUser(SignUpRequest userDto);

    boolean isExistsEmail(String email);

    UserDto findUserByEmailAndPassword(String email, String password);

}
