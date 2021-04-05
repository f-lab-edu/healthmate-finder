package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.user.request.UserDeleteRequest;
import com.hamryt.helparty.dto.user.UserDto;
import com.hamryt.helparty.dto.user.request.SignUpUserRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserReqeust;
import com.hamryt.helparty.dto.user.response.SignUpUserResponse;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;


public interface UserService {

    SignUpUserResponse insertUser(SignUpUserRequest userDto);

    boolean isExistsEmail(String email);

    UserDto findUserByEmailAndPassword(String email, String password);

    void deleteUser(UserDeleteRequest userDeleteRequest);

    UpdateUserResponse updateUser(Long id, UpdateUserReqeust updateUserReqeust);

    UserDto findUserById(Long id);
}
