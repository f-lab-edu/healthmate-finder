package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.user.UserDto;
import com.hamryt.helparty.dto.user.request.SignUpRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserReqeust;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;

public interface UserService {

    void insertUser(SignUpRequest userDto);

    boolean isExistsEmail(String email);

    UserDto findUserByEmailAndPassword(String email, String password);

    UpdateUserResponse updateUser(Long id, UpdateUserReqeust updateUserReqeust);

    UserDto getUserById(Long id);
}
