package com.hamryt.helparty.aop;

import com.hamryt.helparty.service.session.factory.UserSessionFactory;
import java.lang.annotation.Annotation;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.SoftException;
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
    
    @Before("execution(* *(.., @ValidateUser (*), ..))")
    public void checkUser(JoinPoint jp) throws Throwable {
        log.debug("AOP - Check Login User Authentication Stated");
        
        MethodSignature signature = (MethodSignature) jp.getSignature();
        String methodName = signature.getMethod().getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        
        Annotation[][] annotations;
        
        try {
            annotations = jp
                .getTarget()
                .getClass()
                .getMethod(methodName, parameterTypes)
                .getParameterAnnotations();
        } catch (Exception e) {
            throw new SoftException(e);
        }
        
        int argLength = jp.getArgs().length;
        Long userId = null;
        
        ValidateUser validateUser = null;
        
        for (int i = 0; i < argLength; ++i) {
            for (Annotation annotation : annotations[i]) {
                if (annotation.annotationType() == ValidateUser.class) {
                    validateUser = (ValidateUser) annotation;
                    userId = (Long) jp.getArgs()[i];
                    break;
                }
            }
        }
        
        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException(
                "ValidateUser 어노테이션 설정이 잘못되었습니다. Id 값이 null입니다. 변수 타입을 확인해주세요.");
        }
        
        if (Objects.isNull(validateUser.type())) {
            throw new IllegalArgumentException(
                "ValidateUser 어노테이션 설정이 잘못되었습니다. validateUser의 필드인 type을 확인해주세요.");
        }
        
        userSessionFactory.createValidateUser(userId, validateUser.type());
    }
    
}
