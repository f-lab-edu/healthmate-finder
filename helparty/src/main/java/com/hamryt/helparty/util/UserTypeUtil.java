package com.hamryt.helparty.util;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.exception.common.UserTypeDoesNotMatchException;

public class UserTypeUtil {
    
    private UserTypeUtil() {
    }
    
    public static void validateUserType(UserType userType, UserType authType) {
        if (!userType.equals(authType)) {
            throw new UserTypeDoesNotMatchException(authType);
        }
    }
    
}
