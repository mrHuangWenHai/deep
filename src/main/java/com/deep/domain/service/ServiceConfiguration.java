package com.deep.domain.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by huangwenhai on 2018/2/1.
 */

@Configuration
public class ServiceConfiguration extends WebMvcConfigurerAdapter{

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/loginresult","/register","/registerresult","/allfunction");
    }
}
