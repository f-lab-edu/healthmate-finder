package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.user.UpdateInfo;
import com.hamryt.helparty.dto.user.UserDto;
import com.hamryt.helparty.request.SignUpRequest;
import com.hamryt.helparty.request.UpdateUserReqeust;

public interface UserService {

    void insertUser(SignUpRequest userDto);

    boolean isExistsEmail(String email);

    UserDto findUserByEmailAndPassword(String email, String password);

    UpdateInfo updateUser(Long id, UpdateUserReqeust updateUserReqeust);
}
