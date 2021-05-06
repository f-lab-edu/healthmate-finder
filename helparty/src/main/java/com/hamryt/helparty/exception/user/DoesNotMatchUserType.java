package com.hamryt.helparty.exception.user;

import com.hamryt.helparty.dto.UserType;

public class DoesNotMatchUserType extends RuntimeException {
    
    public DoesNotMatchUserType(UserType userType) {
        super("UserType does not match. It Must be : " + userType);
    }
    
}
