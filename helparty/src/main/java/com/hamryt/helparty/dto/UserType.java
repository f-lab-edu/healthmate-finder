package com.hamryt.helparty.dto;

import com.hamryt.helparty.exception.common.UserTypeDoesNotMatchException;

public enum UserType {
    USER("일반사용자"),
    GYM("운동시설"),
    PT("퍼스널트레이너");
    
    private String name;
    
    UserType(String name) {
        this.name = name;
    }
    
    public static void checkUserType(UserType userType, UserType authType){
        if(!userType.equals(authType)){
            throw new UserTypeDoesNotMatchException(authType);
        }
    }
}
