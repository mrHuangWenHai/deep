package com.deep.domain.util;

import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录 登录结果 注册 注册结果 游客页面 不拦截
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, PUT, POST, DELETE");

        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        /*if(request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register") ||
                request.getRequestURI().equals("/allfunction") ||request.getRequestURI().equals("/loginresult")||
                request.getRequestURI().equals("/registerresult") ||request.getRequestURI().equals("/ensurequestion")||
                request.getRequestURI().equals("/phonefind") ||request.getRequestURI().equals("/questionfind")||
                request.getRequestURI().equals("/ensureverify") ||request.getRequestURI().equals("/findpassword")||
                request.getRequestURI().equals("/phonefind")||request.getRequestURI().equals("/ensurequestion")){
            return true;
        }else {
            Jedis jedis = new Jedis("localhost");
            if(jedis.get("userId") == null || jedis.get("token") == null){
                response.sendRedirect("/login");
                return false;
            }else{
                //查看到操作时刷新jedis内存时间
                jedis.expire("userId",600);
                jedis.expire("token",600);
                return true;
            }
        }*/
        return true;
    }
}
