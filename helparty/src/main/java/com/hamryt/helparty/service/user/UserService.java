package com.hamryt.helparty.service.user;

import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.dto.user.request.SignUpUserRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserRequest;
import com.hamryt.helparty.dto.user.request.UserDeleteRequest;
import com.hamryt.helparty.dto.user.response.SignUpUserResponse;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;


public interface UserService {

    SignUpUserResponse insertUser(SignUpUserRequest userDto);

    boolean isExistsEmail(String email);

    UserDTO findUserByEmail(String email);

    UserDTO findUserByEmailAndPassword(String email, String password);

    void deleteUser(UserDeleteRequest userDeleteRequest);

    UpdateUserResponse updateUser(Long id, UpdateUserRequest updateUserRequest);

    UserDTO getUserById(Long id);
    
    String findUserEmailById(Long id);
}
