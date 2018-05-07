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
    public Response Gedit(@RequestParam(value = "message",defaultValue = "") String message,
                          @RequestParam(value = "expireTime",defaultValue = "") String expireTime,
                          @RequestParam(value = "pressureTips",defaultValue = "") String pressureTips){
        logger.info("invoke Gedit {}", message, expireTime, pressureTips);
        //message未设置
        if ("".equals(message)) {
            //redis中一定存在Message字段
            System.out.println(JedisUtil.getCertainKeyValue("Message"));
        } else {
            JedisUtil.setCertainKeyValue("Message",message);
        }
        if ("".equals(expireTime)) {
            System.out.println(JedisUtil.getCertainKeyValue("ExpireTime"));
        } else {
            JedisUtil.setCertainKeyValue("ExpireTime",expireTime);
        }
        if ("".equals(pressureTips)) {
            System.out.println(JedisUtil.getCertainKeyValue("PressureTips"));
        } else {
            JedisUtil.setCertainKeyValue("PressureTips",pressureTips);
        }
        return JudgeUtil.JudgeSuccess("setting","success");
    }
}
