package com.hamryt.helparty.service.login.factory;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.exception.common.UserTypeDoesNotExistException;
import com.hamryt.helparty.service.login.GymLoginServiceImpl;
import com.hamryt.helparty.service.login.LoginService;
import com.hamryt.helparty.service.login.UserLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginTypeFactory extends LoginFactory {
    
    private final GymLoginServiceImpl gymLoginService;
    private final UserLoginServiceImpl userLoginService;
    
    @Override
    public LoginService createLoginService(UserType userType) {
        
        switch (userType) {
            case USER:
                return userLoginService;
            case GYM:
                return gymLoginService;
            default:
                throw new UserTypeDoesNotExistException(userType);
        }
        
    }
    
}
