package com.deep.domain.service;

import com.deep.domain.util.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by huangwenhai on 2018/2/1.
 */

@Configuration

public class ServiceConfiguration {
    public static String redisServer = "localhost";
    public static int port = 6379;
}

