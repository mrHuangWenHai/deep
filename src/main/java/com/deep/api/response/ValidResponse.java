package com.deep.api.response;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.BindException;
import java.util.HashMap;

/**
 * 注入失败 返回给前端数据
 * create by zhongrui on 18-4-15.
 */

@ControllerAdvice
public class ValidResponse {

//    @ExceptionHandler(BindException.class)
//    public static Response bindExceptionHandler() {
//
//        return Responses.errorResponse("inject failure");
//    }

//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public static Response exceptionHandler(Exception e) {
//        System.out.println(e);
//        Response response = Responses.errorResponse(e.getMessage());
//        return response;
//    }
}
