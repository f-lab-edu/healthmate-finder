package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.LoginResponse;
import com.hamryt.helparty.dto.UserDto;
import javax.servlet.http.HttpSession;

public interface UserService {

    LoginResponse loginUser(String email, String password, HttpSession session);

    void insertUser(UserDto userDto);

}
