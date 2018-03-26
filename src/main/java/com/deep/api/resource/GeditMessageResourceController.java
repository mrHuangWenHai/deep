package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.util.JedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用于编辑通知时短信内容
 * create by zhongrui on 18-3-25.
 */

@Controller
public class GeditMessageResourceController {

    @RequestMapping(value = "gredit",method = RequestMethod.GET)
    public String Gredit(){
        return "Gredit";
    }
    @RequestMapping(value = "greditshow",method = RequestMethod.GET)
    public Response GreditShow(/*@RequestParam("message") String message*/){
        //默认message
        JedisUtil jedisUtil = new JedisUtil("Message","请尽快完成未完成工作!");
        return new Response().addData("Success","");
    }
}
