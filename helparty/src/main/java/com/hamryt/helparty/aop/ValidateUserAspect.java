package com.hamryt.helparty.aop;

import com.hamryt.helparty.service.session.factory.UserSessionFactory;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
@RequiredArgsConstructor
public class ValidateUserAspect {
    
    private final UserSessionFactory userSessionFactory;
    
    @Before("@annotation(com.hamryt.helparty.aop.ValidateUser) && @annotation(validateUser)")
    public void checkUser(JoinPoint jp, ValidateUser validateUser) throws Throwable {
        log.debug("AOP - Check Login User Authentication Stated");
        
        Object[] args = jp.getArgs();
        
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        
        Long userId = null;
        
        for (int i = 0; i < parameters.length; i++) {
            String parameterName = parameters[i].getName();
            if (validateUser.value().equals(parameterName)) {
                userId = (Long) args[i];
            }
        }
        
        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException(
                "ValidateUser 어노테이션 설정이 잘못되었습니다. value와 변수명을 일치시켜주세요.");
        }
        
        userSessionFactory.createValidateUser(userId, validateUser.type());
    }
    
}
