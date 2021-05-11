package com.hamryt.helparty.aop;

import com.hamryt.helparty.dto.UserType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateUser {
    
    String value();
    UserType type();
    
}
