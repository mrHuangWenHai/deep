package com.deep.api.response;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.BindException;

/**
 * 注入失败 返回给前端数据
 * create by zhongrui on 18-4-15.
 */

@ControllerAdvice
public class ValidResponse {

    @ExceptionHandler(BindException.class)
    public static Response bindExceptionHandler() {
        return Responses.errorResponse("inject failure");
    }
}
