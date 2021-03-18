package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.request.SignUpRequest;
import com.hamryt.helparty.request.UpdateUserReqeust;

public interface UserService {

    void insertUser(SignUpRequest userDto);

    boolean isExistsEmail(String email);

    UserDto findUserByEmailAndPassword(String email, String password);

    UserDto updateUser(Long id, UpdateUserReqeust updateUserReqeust);
}
