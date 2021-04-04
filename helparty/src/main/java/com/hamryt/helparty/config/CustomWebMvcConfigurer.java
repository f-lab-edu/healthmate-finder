package com.hamryt.helparty.config;

import com.hamryt.helparty.interceptor.LoginValidationInterceptor;
import com.hamryt.helparty.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@RequiredArgsConstructor
public class CustomWebMvcConfigurer extends WebMvcConfigurationSupport {

    private final LoginService loginService;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginValidationInterceptor(loginService))
            .addPathPatterns("/users/**");
    }

}
