package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.Message;
import com.deep.domain.model.MessageExample;
import com.deep.domain.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class MessageResource {

    @Resource
    private MessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

    @RequestMapping(value = "/messageBoard/insert",method = RequestMethod.POST)
    public @ResponseBody
    Response addMessage(@RequestBody @Valid Message message) {
        logger.info("invoke/messageBoard/insert {}",message);

        message.setInserttime(new Date());
        String contact = message.getContact();
        try {
            if (!Message.isEmail(contact) && !Message.isMobile(contact)) {
                throw new Exception("请输入正确手机号或者邮箱地址！");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            Response response = Responses.errorResponse(e.getMessage());
            return response;
        }


        Integer id = messageService.insertMessage(message);

        Integer messageId = message.getMessageId();



        if (id != 0) {
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("Message",messageId);
            response.setData(data);
            return response;
        }
        else{
            Response response = Responses.errorResponse("数据插入失败");
            return  response;
        }
    }

    @RequestMapping(value = "/messageBoard/searchByMessage",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByMessage( @RequestBody  Message message)
    {
        logger.info("invoke /messageBoard/searchByMessage {}",message.getMessage());
        if(message.getMessage().isEmpty())
        {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andMessageLike("%" + message.getMessage()+"%");
        List<Message>select = messageService.findMessageSelectiveWithRowbounds(messageExample,message.getPageNumb(),message.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByMessage",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/messageBoard/searchByUsername",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByUsername(
                                @RequestBody  Message message

    ) {
        logger.info("invoke /messageBoard/searchByUsername {}",message.getUsername());

        if(message.getUsername().isEmpty())
        {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUsernameLike("%" + message.getUsername()+"%");
        List<Message>select=messageService.findMessageSelectiveWithRowbounds(messageExample,message.getPageNumb(),message.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByUsername",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/messageBoard/searchByTag",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByTag(@RequestBody  Message message)
    {
        logger.info("invoke /messageBoard/searchByTag {}",message.getTag());
        if(message.getTag().isEmpty())
        {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        MessageExample messageExample=new MessageExample();
        MessageExample.Criteria criteria=messageExample.createCriteria();
        criteria.andTagLike("%"+message.getTag()+"%");
        List<Message>select=messageService.findMessageSelectiveWithRowbounds(messageExample,message.getPageNumb(),message.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByTag",select);
        response.setData(data);
        return response;
    }
    @RequestMapping(value = "/messageBoard/searchByAttitude",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByAttitude(@RequestBody  Message message)
    {
        logger.info("invoke /messageBoard/searchByAttitude {}",message.getAttitude());
        if(message.getAttitude().isEmpty())
        {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        MessageExample messageExample=new MessageExample();
        MessageExample.Criteria criteria=messageExample.createCriteria();
        criteria.andAttitudeLike("%"+message.getAttitude()+"%");
        List<Message>select=messageService.findMessageSelectiveWithRowbounds(messageExample,message.getPageNumb(),message.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByAttitude",select);
        response.setData(data);
        return response;
    }
    @RequestMapping(value = "/messageBoard/searchByIntention",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByIntention(@RequestBody  Message message)
    {
        logger.info("invoke /messageBoard/searchByIntention {}",message.getIntention());
        if(message.getIntention().isEmpty())
        {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }
        MessageExample messageExample=new MessageExample();
        MessageExample.Criteria criteria=messageExample.createCriteria();
        criteria.andIntentionLike("%"+message.getIntention()+"%");
        List<Message>select=messageService.findMessageSelectiveWithRowbounds(messageExample,message.getPageNumb(),message.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByIntention",select);
        response.setData(data);
        return response;
    }


}
