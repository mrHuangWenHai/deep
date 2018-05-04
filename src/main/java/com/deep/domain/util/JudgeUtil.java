package com.deep.domain.util;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;

import com.deep.domain.model.EnvironmentTraceModel;


import java.util.HashMap;

/**
 * 用于判断的工具类
 * create by zhongrui on 18-3-28.
 */
public class JudgeUtil {

    /**
     * 用于判断删除操作是否成功执行
     * @param row
     * @return
     */
    public static Response JudgeDelete(int row) {
        if (0 == row) {
            return Responses.errorResponse("delete error");
        } else {
            HashMap<String,Object> data = new HashMap<>();
            data.put("success",row);
            return Responses.successResponse(data);
        }
    }

    /**
     * 用于判断更新操作是否成功执行
     * @param row
     * @return
     */
    public static Response JudgeUpdate(int row) {

        if (0 == row) {
            return Responses.errorResponse("update error");
        } else {
            HashMap<String,Object> data = new HashMap<>();
            data.put("success",row);
            return Responses.successResponse(data);
        }
    }

    /**
     * 用于判断查询操作是否成功执行
     * 返回结果为单个对象
     * @param object
     * @return
     */
    public static Response JudgeFind(Object object) {
        HashMap<String,Object> data = new HashMap<>();

        data.put("model",object);

        return Responses.successResponse(data);
    }

    /**
     * 用于判断查询操作是否成功执行
     * 返回结果为多个对象数组
     * @param object

     * @param size

     * @return
     */
    public static Response JudgeFind(Object object ,int size) {
        HashMap<String,Object> data = new HashMap<>();
        data.put("List",object);
        data.put("size",size);
        return Responses.successResponse(data);
    }

    /**
     * 用于返回两个对象
     * @param object1
     * @param object2
     * @return
     */
    public static Response JudgeFind(Object object1 ,Object object2) {
        HashMap<String,Object> data = new HashMap<>();
        data.put("model1",object1);
        data.put("model2",object2);
        return Responses.successResponse(data);
    }
    /**
     * 用于返回成功方法
     * 内部构造HashMap
     * @param details
     * @param o
     * @return
     */
    public static Response JudgeSuccess(String details,Object o) {
        HashMap<String,Object> data = new HashMap<>();
        data.put(details,o);
        return Responses.successResponse(data);
    }

}
