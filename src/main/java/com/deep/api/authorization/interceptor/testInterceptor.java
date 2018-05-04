package com.deep.api.authorization.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class testInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
     //   System.out.println(response.getHeader("Vary"));
     //   System.out.println(response.getHeaderNames());
     //   System.out.println(response.getStatus());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Cache-Control");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, PUT, POST, DELETE,PATCH");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Cache-Control", "no-cache, no-store");
        return true;
    }
}
