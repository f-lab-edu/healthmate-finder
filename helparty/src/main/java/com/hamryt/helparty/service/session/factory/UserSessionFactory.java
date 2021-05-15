package com.hamryt.helparty.service.session.factory;

import com.hamryt.helparty.dto.UserType;

public abstract class UserSessionFactory {
    
    public abstract void createValidateUser(Long id, UserType userType);
}
