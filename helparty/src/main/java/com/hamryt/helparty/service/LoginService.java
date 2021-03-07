package com.hamryt.helparty.service;

import com.hamryt.helparty.dto.UserDto;
import javax.servlet.http.HttpSession;

public interface LoginService {
    UserDto login(String email, String password, HttpSession session);
}
