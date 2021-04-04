package com.hamryt.helparty.service.login;

import com.hamryt.helparty.dto.user.UserDTO;

public interface LoginService {

    UserDTO login(String email, String password);

    void sessionValidate();

    void validateUser(Long id);

    String getSessionEmail();

}
