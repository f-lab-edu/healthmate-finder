package com.hamryt.helparty.exception.common;

import com.hamryt.helparty.dto.UserType;

public class UserTypeDoesNotExistException extends RuntimeException{
    
    public UserTypeDoesNotExistException(UserType userType){
        super("This UserType does not exists : " + userType);
    }
}
