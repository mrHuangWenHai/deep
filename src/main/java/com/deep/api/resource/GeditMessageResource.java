package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.RedisDataModel;
import com.deep.domain.util.JedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 用于编辑通知时短信内容
 * 用于编辑短信通知间隔时间
 * 用于编辑发送短信条数
 * create by zhongrui on 18-3-25.
 */

@Controller
public class GeditMessageResource {


    @ResponseBody
    @RequestMapping(value = "greditshow",method = RequestMethod.POST)
    public Response GreditShow(@RequestBody RedisDataModel redisDataModel){

        //两个类型都不为null  设置redis中"Message"和"ExpireTime"的value
        if(redisDataModel.getExpireTime() != null && redisDataModel.getMessage() != null){
            JedisUtil.setCertainKeyValue("Message",redisDataModel.getMessage());
            JedisUtil.setCertainKeyValue("ExpireTime",redisDataModel.getExpireTime());

            System.out.println("ExpireTime value:"+JedisUtil.getCertainKeyValue("Message"));
            System.out.println("Message value:"+JedisUtil.getCertainKeyValue("ExpireTime"));

            HashMap<String,String> data = new HashMap<>();
            data.put("Success","Message and ExpireTime");
            return Responses.successResponse(data);
        }else if(redisDataModel.getExpireTime() != null && redisDataModel.getMessage() == null){

            //更新redis中"ExpireTime"的value
            JedisUtil.setCertainKeyValue("ExpireTime",redisDataModel.getExpireTime());

            HashMap<String,String> data = new HashMap<>();
            data.put("Success","ExpireTime");
            return Responses.successResponse(data);

        }else if(redisDataModel.getExpireTime() == null && redisDataModel.getMessage() != null){

            //更新redis中"Message"的value
            JedisUtil.setCertainKeyValue("Message",redisDataModel.getMessage());

            HashMap<String,String> data = new HashMap<>();
            data.put("Success","Message");
            return Responses.successResponse(data);
        }else {
            //System.out.println("Message:"+jedisUtil.getCertainKeyValue("Message"));
            //System.out.println("ExpireTime:"+jedisUtil.getCertainKeyValue("ExpireTime"));
            return Responses.errorResponse("Error Setting");
        }
    }
}
