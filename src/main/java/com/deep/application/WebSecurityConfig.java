package com.deep.application;

import com.deep.api.authorization.interceptor.AuthorizationInterceptor;
import com.deep.api.authorization.interceptor.PermitInterceptor;
import com.deep.api.authorization.interceptor.testInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.filter.HttpPutFormContentFilter;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new testInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new PermitInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/picture/**").addResourceLocations("file:///Users/huangwenhai/alibaba/Deep/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/movie/**").addResourceLocations("file:///Users/huangwenhai/alibaba/Deep/video/");
        registry.addResourceHandler("/pic/**").addResourceLocations("file:///Users/huangwenhai/alibaba/pic/");
        System.out.println("this is the ResourceHandler");
    }
}
