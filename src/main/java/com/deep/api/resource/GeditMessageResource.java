package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;


/**
 * 用于编辑通知时短信内容
 * 用于编辑短信通知间隔时间
 * 用于编辑发送短信条数
 * create by zhongrui on 18-3-25.
 */
@RestController
public class GeditMessageResource {

    private final Logger logger = LoggerFactory.getLogger(GeditMessageResource.class);

    @Permit(authorities = "edit_messages")
    @RequestMapping(value = "/gedit",method = RequestMethod.GET)
    public Response Gedit(@RequestParam(value = "message", required = false) String message,
                          @RequestParam(value = "expireTime", required = false) String expireTime,
                          @RequestParam(value = "pressureTips", required = false) String pressureTips){
        logger.info("invoke Gedit {}", message, expireTime, pressureTips);
        //message未设置
        if (message != null) {
            JedisUtil.setCertainKeyValue("Message",message);
        }

        if (expireTime != null) {
            JedisUtil.setCertainKeyValue("ExpireTime",expireTime);
        }

        if (pressureTips != null) {
            JedisUtil.setCertainKeyValue("PressureTips",pressureTips);
        }

        System.out.println("message: " + message + " " + JedisUtil.getCertainKeyValue("Message"));
        System.out.println("expiretime: " + expireTime + " " + JedisUtil.getCertainKeyValue("ExpireTime"));
        System.out.println("pressuretips: " + pressureTips + " " + JedisUtil.getCertainKeyValue("PressureTips"));

        return JudgeUtil.JudgeSuccess("setting","success");
    }
}
