package com.hamryt.helparty.service.user;

import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.dto.user.request.SignUpRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserReqeust;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;

public interface UserService {

    void insertUser(SignUpRequest userDto);

    boolean isExistsEmail(String email);

    UserDTO findUserByEmail(String email);

    UserDTO findUserByEmailAndPassword(String email, String password);

    UpdateUserResponse updateUser(Long id, UpdateUserReqeust updateUserReqeust);

    UserDTO getUserById(Long id);
}
