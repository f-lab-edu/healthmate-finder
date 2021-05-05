package com.hamryt.helparty.exception.common;

import com.hamryt.helparty.dto.UserType;

public class UserTypeDoesNotMatchException extends RuntimeException {
    
    public UserTypeDoesNotMatchException(UserType userType) {
        super("userType dose not match does not match with : " + userType);
    }
    
}
