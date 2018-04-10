package com.deep.api.authorization.interceptor;

import com.deep.api.Utils.JedisUtil;
import com.deep.api.authorization.token.TokenManagerRealization;
import com.deep.api.authorization.token.TokenModel;
import com.deep.api.authorization.tools.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录识别拦截器, 判断用户的登录信息
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Resource
    private TokenManagerRealization tokenManagerRealization;

    /**
     * 预处理拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("invoke preHandle of AuthorizationInterceptor", request, response, handler);
        // 加相关的回应头
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, PUT, POST, DELETE");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        if (request.getMethod().equals("OPTIONS")) {
            logger.info("AuthorizationInterceptor:request type is OPTIONS");
            return true;
        }
        if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register") ||
                request.getRequestURI().equals("/allfunction") ||request.getRequestURI().equals("/loginresult")||
                request.getRequestURI().equals("/userAdd") ||request.getRequestURI().equals("/ensurequestion")||
                request.getRequestURI().equals("/phonefind") ||request.getRequestURI().equals("/questionfind")||
                request.getRequestURI().equals("/ensureverify") ||request.getRequestURI().equals("/findpassword")||
                request.getRequestURI().equals("/phonefind")||request.getRequestURI().equals("/ensurequestion") ||
                request.getRequestURI().equals("/error") || request.getRequestURI().equals("/question")
                ) {
            logger.info("AuthorizationInterceptor:don't need to interceptor");
            return true;
        }
        // 从header中获取token
        String authorization = request.getHeader(Constants.AUTHORIZATION);

        System.out.println(authorization);

        tokenManagerRealization = new TokenManagerRealization();
        // 从authorization中获取用户名以及token
        TokenModel model = tokenManagerRealization.getToken(authorization);
        if (model == null) {

            // 登录验证失败, 请登录
            logger.info("model == null");
            response.setStatus(401);
            return false;
        }
        if(JedisUtil.getValue(String.valueOf(model.getUserId()))== null) {
            logger.info("first false", JedisUtil.getValue(String.valueOf(model.getUserId())));
            response.setStatus(401);
            return false;
        } else if (!JedisUtil.getValue(String.valueOf(model.getUserId())).equals(model.getToken())) {
            logger.info("second false", JedisUtil.getValue(String.valueOf(model.getUserId())));
            System.out.println(JedisUtil.getValue(String.valueOf(model.getUserId())));
            System.out.println(model.getToken());
            System.out.println("second false");
            response.setStatus(401);

            return false;
        }
        // 从Redis数据库中获取用户原来的token, 然后取得其权限, 加入新的token
        String oldToken = JedisUtil.getValue(String.valueOf(model.getUserId()));
        String userRoleID = oldToken.split(":")[1];
        logger.info("oldToken", oldToken);
        TokenModel tokenModel = new TokenModel(model.getUserId(), userRoleID);
        logger.info("newToken", tokenModel.getToken());
        JedisUtil.setValue(String.valueOf(model.getUserId()),tokenModel.getToken());
        JedisUtil.doExpire(String.valueOf(model.getUserId()));
        response.setHeader("Authorization", model.getUserId() + ":" + tokenModel.getToken());
        logger.info("Authorization pass");

        return true;
    }
}

