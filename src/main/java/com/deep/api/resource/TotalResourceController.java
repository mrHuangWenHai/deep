package com.deep.api.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TotalResourceController {

    @RequestMapping(value = "/allfunction")
    public String AllFunction(){
        return "AllFunctionChoiceForm";
    }
}
