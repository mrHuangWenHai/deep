package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.Message;
import com.deep.domain.model.MessageExample;
import com.deep.domain.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import java.util.Map;

@RestController
public class MessageResource {

    @Resource
    private MessageService messageService;

    private Logger logger = LoggerFactory.getLogger(MessageResource.class);

    @RequestMapping(value = "/messageBoard/insert",method = RequestMethod.POST)
    public Response addMessage(@RequestBody @Valid Message message) {
        logger.info("invoke/messageBoard/insert {}",message);

        message.setInserttime(new Date());
        String contact = message.getContact();
        try {
            if (!Message.isEmail(contact) && !Message.isMobile(contact)) {
                throw new Exception("请输入正确手机号或者邮箱地址！");
            }
        } catch (Exception e) {
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
        } else {
            Response response = Responses.errorResponse("数据插入失败");
            return  response;
        }
    }

    @RequestMapping(value = "/messageBoard/search",method = RequestMethod.POST)
    public Response searchByMessage(@RequestBody  Message message) {

        logger.info("invoke /messageBoard/searchByMessage {}",message);
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        if(message.getMessage() != null && !message.getMessage().isEmpty())
            criteria.andMessageLike("%" + message.getMessage()+"%");
        if(message.getAttitude() != null && !message.getAttitude().isEmpty())
            criteria.andAttitudeLike("%"+message.getAttitude()+"%");
        if(message.getIntention() != null && !message.getIntention().isEmpty())
            criteria.andIntentionLike("%"+message.getIntention()+"%");

        if(message.getsTime() != null && message.geteTime() != null)
           criteria.andInserttimeBetween(message.getsTime(),message.geteTime());

        List<Message> select = messageService.findMessageSelectiveWithRowbounds(messageExample,message.getPageNumb(),message.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("data",select);
        data.put("size",select.size());
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/messageBoard/searchByUsername",method = RequestMethod.POST)
    public Response searchByUsername(@RequestBody  Message message) {
        logger.info("invoke /messageBoard/searchByUsername {}",message.getUsername());
        if(message.getUsername().isEmpty()) {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }

        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUsernameLike("%" + message.getMessage()+"%");
        List<Message> select = messageService.findMessageSelectiveWithRowbounds(messageExample,message.getPageNumb(),message.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByUsername",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/messageBoard/searchByTag",method = RequestMethod.POST)
    public Response searchByTag(@NotNull(message = "标签不能为空")  @RequestParam(value = "tag",required = false,defaultValue = "")String tag,
                              @RequestParam(value = "pageNumb",required = true)int pageNumb,
                              @RequestParam(value = "limit",required = true)int limit) {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andTagLike("%"+tag+"%");
        List<Message> select = messageService.findMessageSelectiveWithRowbounds(messageExample,pageNumb,limit);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByTag",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/messageBoard/searchByAttitude",method = RequestMethod.POST)
    public Response searchByAttitude(@RequestBody  Message message) {

        logger.info("invoke /messageBoard/searchByAttitude {}",message.getAttitude());
        if (message.getAttitude() == null || message.getAttitude().length() == 0) {
            Response response = Responses.errorResponse("查询条件不能为空！");
            return response;
        }

        if (message.getLimit() == 0) {
            message.setLimit(10);
        }

        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andAttitudeLike("%"+message.getAttitude()+"%");
        List<Message> select = messageService.findMessageSelectiveWithRowbounds(messageExample,message.getPageNumb(),message.getLimit());

        Response response = Responses.successResponse();
        HashMap<String,Object> data = new HashMap<>();
        data.put("searchByAttitude",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/messageBoard/searchByIntention",method = RequestMethod.POST)
    public Response searchByIntention(@NotNull(message = "购买意向不能为空")  @RequestParam(value = "intention",required = false,defaultValue = "")String intention,
                              @RequestParam(value = "pageNumb",required = false, defaultValue = "0")int pageNumb,
                              @RequestParam(value = "limit",required = false, defaultValue = "10")int limit) {

        logger.info("/messageBoard/searchByIntention {} {} {}",intention,pageNumb,limit);
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andIntentionLike("%" + intention + "%");
        List<Message> select = messageService.findMessageSelectiveWithRowbounds(messageExample,pageNumb,limit);
        Response response = Responses.successResponse();
        HashMap<String,Object> data = new HashMap<>();
        data.put("searchByIntention",select);
        response.setData(data);
        return response;
    }

}
