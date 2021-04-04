package com.hamryt.helparty.interceptor;

import com.hamryt.helparty.service.login.LoginService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class LoginValidationInterceptor implements HandlerInterceptor {

    private final LoginService loginService;

    @Override
    public boolean preHandle(
        HttpServletRequest request, HttpServletResponse response,
        Object handler
    ) {

        if (handler instanceof HandlerMethod == false) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        LoginValidation loginValidation = handlerMethod.getMethodAnnotation(LoginValidation.class);

        if (loginValidation != null) {
            loginService.sessionValidate();
        }

        return true;
    }

}
