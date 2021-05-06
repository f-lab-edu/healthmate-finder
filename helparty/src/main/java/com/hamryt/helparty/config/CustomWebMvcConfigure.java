package com.hamryt.helparty.config;

import com.hamryt.helparty.aop.CustomArgumentResolver;
import com.hamryt.helparty.interceptor.LoginValidationInterceptor;
import com.hamryt.helparty.service.login.LoginService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@RequiredArgsConstructor
public class CustomWebMvcConfigure extends WebMvcConfigurationSupport {
    
    private final LoginService loginService;
    private final CustomArgumentResolver customArgumentResolver;
    
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginValidationInterceptor(loginService))
            .addPathPatterns("/users/**");
    }
    
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(customArgumentResolver);
    }
    
}
