package com.hamryt.helparty.aop;

import com.hamryt.helparty.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

@RequiredArgsConstructor
@Component
@Aspect
public class LoginValidationAspect {

    private final LoginService loginService;

    @Before("@annotation(com.hamryt.helparty.aop.LoginValidation)")
    public void userLoginValidation() {

        loginService.checkAuth();
    }

}
