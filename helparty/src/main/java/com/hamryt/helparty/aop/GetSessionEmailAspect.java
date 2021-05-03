package com.hamryt.helparty.aop;

import com.hamryt.helparty.util.SessionKeys;
import java.lang.annotation.Annotation;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.SoftException;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class GetSessionEmailAspect {
    
    private final HttpSession session;
    
    @Around("execution(public * *(.., @GetSessionEmail (*), ..))")
    public Object getSessionEmail(ProceedingJoinPoint joinPoint) throws Throwable {
        
        String email = (String) session.getAttribute(SessionKeys.LOGIN_USER_EMAIL);
        
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        
        Annotation[][] annotations;
        
        try {
            annotations = joinPoint
                .getTarget()
                .getClass()
                .getMethod(methodName, parameterTypes)
                .getParameterAnnotations();
        } catch (Exception e) {
            throw new SoftException(e);
        }
        
        Object[] args = new Object[joinPoint.getArgs().length];
        
        int index = 0;
        for (Object arg : joinPoint.getArgs()) {
            for (Annotation annotation : annotations[index]) {
                if (annotation.annotationType() == GetSessionEmail.class) {
                    arg = email;
                }
            }
            args[index] = arg;
            ++index;
        }
        
        return joinPoint.proceed(args);
    }
    
}
