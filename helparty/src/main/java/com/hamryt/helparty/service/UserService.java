package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.user.UserUpdateResponse;
import com.hamryt.helparty.dto.user.UserDto;
import com.hamryt.helparty.request.SignUpRequest;
import com.hamryt.helparty.request.UpdateUserReqeust;

public interface UserService {

    void insertUser(SignUpRequest userDto);

    boolean isExistsEmail(String email);

    UserDto findUserByEmailAndPassword(String email, String password);

    UserUpdateResponse updateUser(Long id, UpdateUserReqeust updateUserReqeust);
}
