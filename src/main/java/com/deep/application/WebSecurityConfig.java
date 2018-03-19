package com.deep.application;

import com.deep.api.authorization.interceptor.PermitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter{
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new PermitInterceptor()).addPathPatterns("/**");
    }
}
