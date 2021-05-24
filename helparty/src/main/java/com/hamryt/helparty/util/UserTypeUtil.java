package com.hamryt.helparty.util;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.exception.common.PermissionException;

public class UserTypeUtil {
    
    private UserTypeUtil() {
    }
    
    public static void validateUserTypePermissions(UserType userType, UserType authType) {
        if (!userType.equals(authType)) {
            throw new PermissionException("This UserType does not permission ");
        }
    }
    
}
