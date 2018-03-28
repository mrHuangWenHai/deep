package com.deep.domain.util;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;

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
    public static Response JudgeDelete(int row){
        if (0 == row){
            return Responses.errorResponse("wrong id");
        }else{
            HashMap<String,Object> data = new HashMap<>();
            data.put("delete row",row);
            return Responses.successResponse(data);
        }
    }

    /**
     * 用于判断更新操作是否成功执行
     * @param row
     * @return
     */
    public static Response JudgeUpdate(int row){
        if (0 == row){
            return Responses.errorResponse("wrong id");
        }else{
            HashMap<String,Object> data = new HashMap<>();
            data.put("update row",row);
            return Responses.successResponse(data);
        }
    }

    /**
     * 用于判断查询操作是否成功执行
     * @param object
     * @return
     */
    public static Response JudgeFind(Object object){
        HashMap<String,Object> data = new HashMap<>();
        data.put("List",object);
        return Responses.successResponse(data);
    }
}
