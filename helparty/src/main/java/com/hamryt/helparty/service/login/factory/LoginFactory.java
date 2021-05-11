package com.hamryt.helparty.service.login.factory;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.service.login.LoginService;

public abstract class LoginFactory {
    
    public abstract LoginService createLoginService(UserType userType);

}
