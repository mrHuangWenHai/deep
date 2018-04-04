package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.RedisDataModel;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 用于编辑通知时短信内容
 * 用于编辑短信通知间隔时间
 * 用于编辑发送短信条数
 * create by zhongrui on 18-3-25.
 */

@Controller
public class GeditMessageResource {

    private final Logger logger = LoggerFactory.getLogger(GeditMessageResource.class);

    @ResponseBody
    @RequestMapping(value = "/geditshow",method = RequestMethod.POST)
    public Response GreditShow(@RequestBody RedisDataModel redisDataModel) {

        logger.info("invoke greditShow {}", redisDataModel);

        if (redisDataModel.getMessage() == null ||
                redisDataModel.getExpireTime() == null||
                redisDataModel.getPressureTips() == null){
            return Responses.errorResponse("Lack Item");
        }else {

            JedisUtil.setCertainKeyValue("Message",redisDataModel.getMessage());
            JedisUtil.setCertainKeyValue("ExpireTime",redisDataModel.getExpireTime());
            JedisUtil.setCertainKeyValue("PressureTips",redisDataModel.getPressureTips());

            return JudgeUtil.JudgeSuccess("Success","All Setting");
        }
    }
}
