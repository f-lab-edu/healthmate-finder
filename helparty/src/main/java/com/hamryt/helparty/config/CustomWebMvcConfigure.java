package com.hamryt.helparty.config;

import com.hamryt.helparty.argumentresolver.GetSessionIdArgumentResolver;
import com.hamryt.helparty.interceptor.LoginValidationInterceptor;
import com.hamryt.helparty.service.session.SessionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@RequiredArgsConstructor
public class CustomWebMvcConfigure extends WebMvcConfigurationSupport {
    
    private final SessionService sessionService;
    private final GetSessionIdArgumentResolver getSessionIdArgumentResolver;
    
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginValidationInterceptor(sessionService))
            .addPathPatterns("/gymboards/**")
            .addPathPatterns("/mateboards/**")
            .addPathPatterns("/users/**")
            .addPathPatterns("/gyms/**");
        
    }
    
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(getSessionIdArgumentResolver);
     
    }
    
}
