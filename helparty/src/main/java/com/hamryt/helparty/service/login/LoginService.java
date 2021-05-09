package com.hamryt.helparty.service.login;

import com.hamryt.helparty.dto.login.LoginDTO;

public interface LoginService {
    
    LoginDTO login(String email, String password);
    
}
