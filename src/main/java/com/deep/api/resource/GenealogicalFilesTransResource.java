package com.deep.api.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * create by zhongrui on 18-3-20.
 */

@Controller
@RequestMapping(value = "/gft")
public class GenealogicalFilesTransResource {

    //追溯系统 通过商标耳牌追溯
    /**
     * METHOD:GET
     * @return 相关信息
     */
    @RequestMapping(value = "/function",method = RequestMethod.POST)
    public String GenealogicalFilesFunctionChoice(){
        return "GenealogicalFilesHTML/GenealogicalFilesFunctionChoiceForm";
    }

}
