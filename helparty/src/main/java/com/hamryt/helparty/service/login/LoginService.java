package com.hamryt.helparty.service.login;

import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.dto.user.UserDTO;

public interface LoginService {
    
    UserDTO loginUser(String email, String password);
    
    void sessionValidate();
    
    void validateUser(Long id);
    
    String getSessionEmail();
    
    GymDTO loginGym(String email, String password);
}
