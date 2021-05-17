package com.hamryt.helparty.service.user;

import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.dto.user.request.SignUpUserRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserRequest;
import com.hamryt.helparty.dto.user.response.SignUpUserResponse;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;


public interface UserService {
    
    SignUpUserResponse insertUser(SignUpUserRequest userDto);
    
    boolean isExistsEmail(String email);
    
    UserDTO findUserByEmailAndPassword(String email, String password);
    
    void deleteUser(Long sessionId);
    
    UpdateUserResponse updateUser(Long SessionId, UpdateUserRequest updateUserRequest);
    
    UserDTO findUserById(Long id);
}
