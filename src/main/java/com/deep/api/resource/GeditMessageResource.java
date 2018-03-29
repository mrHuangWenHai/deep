package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用于编辑通知时短信内容
 * create by zhongrui on 18-3-25.
 */

@Controller
public class GeditMessageResource {

    @RequestMapping(value = "greditshow",method = RequestMethod.GET)
    public Response GreditShow(@RequestParam("Message") String Message){
        //默认message

        return Responses.successResponse();
    }
}
