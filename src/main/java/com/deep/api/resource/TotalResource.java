package com.deep.api.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TotalResource {

    @RequestMapping(value = "/all")
    public String AllFunction(){
        return "AllFunctionChoiceForm";
    }
}
