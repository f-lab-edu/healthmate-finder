package com.hamryt.helparty.dto;

import com.hamryt.helparty.exception.common.PermissionException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum UserType {
    USER("USER"),
    GYM("GYM"),
    PT("PT");
    
    private String name;
    
    UserType(String name) {
        this.name = name;
    }
    
    public void validEqualUserType(String authType){
        if (!name.equals(authType)){
            throw new PermissionException("This UserType does not appropriate for the board. this.userType: " + name + ", AuthType: " + authType);
        }
    }
    
}
