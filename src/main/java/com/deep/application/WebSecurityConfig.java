package com.deep.application;

import com.deep.api.authorization.interceptor.AuthorizationInterceptor;
import com.deep.api.authorization.interceptor.PermitInterceptor;
import com.deep.api.authorization.interceptor.testInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebSecurityConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new testInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new PermitInterceptor()).addPathPatterns("/**");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/picture/**").addResourceLocations("file:///home/doubibobo/picture/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        System.out.println("this is the ResourceHandler");
    }
}
