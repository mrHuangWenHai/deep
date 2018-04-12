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

    /**
     * 编辑默认短信内容
     * @param Message
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/mgedit",method = RequestMethod.GET)
    public Response MessageGredit(
            @RequestParam(value = "Message" , defaultValue = "") String Message) {

        logger.info("invoke messageGredit {}", Message);

        if (Message == null){
            return Responses.errorResponse("Lack Item");
        }else {
            JedisUtil.setCertainKeyValue("Message", Message);

            return JudgeUtil.JudgeSuccess("Success","Setting");
        }
    }

    /**
     * 用于编辑短信通知间隔时间
     * @param ExpireTime
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/egedit",method = RequestMethod.GET)
    public Response ExpireTimeGredit(
            @RequestParam(value = "ExpireTime" , defaultValue = "") String ExpireTime) {

        logger.info("invoke expireTimeGredit {}", ExpireTime);

        if (ExpireTime == null){
            return Responses.errorResponse("Lack Item");
        }else {
            JedisUtil.setCertainKeyValue("ExpireTime", ExpireTime);

            return JudgeUtil.JudgeSuccess("Success","Setting");
        }
    }

    /**
     * 用于编辑短信通知界限
     * @param PressureTips
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pgedit",method = RequestMethod.GET)
    public Response PressureTipsGredit(
            @RequestParam(value = "PressureTips" , defaultValue = "") String PressureTips) {

        logger.info("invoke pressureTipsGredit {}", PressureTips);

        if (PressureTips == null){
            return Responses.errorResponse("Lack Item");
        }else {
            JedisUtil.setCertainKeyValue("ExpireTime", PressureTips);

            return JudgeUtil.JudgeSuccess("Success","Setting");
        }
    }
}
