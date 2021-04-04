package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.user.UserDeleteRequest;
import com.hamryt.helparty.dto.user.UserDto;
import com.hamryt.helparty.dto.user.request.SignUpRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserReqeust;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;


public interface UserService {

    void insertUser(SignUpRequest userDto);

    boolean isExistsEmail(String email);

    UserDto findUserByEmailAndPassword(String email, String password);

    void deleteUser(UserDeleteRequest userDeleteRequest);

    UpdateUserResponse updateUser(Long id, UpdateUserReqeust updateUserReqeust);

    UserDto findUserById(Long id);
}
