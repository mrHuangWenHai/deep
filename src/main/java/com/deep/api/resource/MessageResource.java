package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.Message;
import com.deep.domain.model.MessageExample;
import com.deep.domain.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
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
            return Responses.errorResponse(e.getMessage());
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
            return Responses.errorResponse("数据插入失败");
        }
    }

    @Permit(authorities = "check_message_of_all_user")
    @RequestMapping(value = "/messageBoard/search",method = RequestMethod.POST)
    public Response searchByMessage(@RequestBody  Message message)throws Exception {

        logger.info("invoke /messageBoard/searchByMessage {}",message);
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        if(message.getMessage() != null && !message.getMessage().isEmpty())
            criteria.andMessageLike("%" + message.getMessage()+"%");
        if(message.getAttitude() != null && !message.getAttitude().isEmpty())
            criteria.andAttitudeLike("%"+message.getAttitude()+"%");
        if(message.getIntention() != null && !message.getIntention().isEmpty())
            criteria.andIntentionLike("%"+message.getIntention()+"%");
        if(message.getsTime() != null )
        {
            String[] times = message.getsTime().split(",");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            criteria.andInserttimeBetween(sdf.parse(times[0]),sdf.parse(times[1]));
        }
        List<Message> sizeOfAll = messageService.findMessageSelective(messageExample);
//        List<Message> select = messageService.findMessageSelectiveWithRowbounds(messageExample,message.getPageNumb(),message.getLimit());
//        Integer size = sizeOfALL.size();
        int size = sizeOfAll.size();
        int page = message.getPageNumb();
        int pageSize = message.getLimit();
        int destIndex = (page+1) * pageSize  > size ? size : (page+1) * pageSize ;
        List<Message> select = sizeOfAll.subList(page * pageSize, destIndex);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("List",select);
        data.put("Size",size);

        response.setData(data);
        return response;
    }

    @Permit(authorities = "check_message_of_all_user")
    @RequestMapping(value = "/messageBoard/searchByUsername",method = RequestMethod.POST)
    public Response searchByUsername(@RequestBody  Message message) {
        logger.info("invoke /messageBoard/searchByUsername {}",message.getUsername());
        if(message.getUsername().isEmpty()) {
            return Responses.errorResponse("查询条件不能为空！");
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

    @Permit(authorities = "check_message_of_all_user")
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

    @Permit(authorities = "check_message_of_all_user")
    @RequestMapping(value = "/messageBoard/searchByAttitude",method = RequestMethod.POST)
    public Response searchByAttitude(@RequestBody  Message message) {

        logger.info("invoke /messageBoard/searchByAttitude {}",message.getAttitude());
        if (message.getAttitude() == null || message.getAttitude().length() == 0) {
            return Responses.errorResponse("查询条件不能为空！");
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

    @Permit(authorities = "check_message_of_all_user")
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
    @RequestMapping(value = "/messageBoard/delete",method = RequestMethod.DELETE)
    public Response deleteMessageById(@RequestBody Map map)
    {
        messageService.deleteMessageById(Integer.parseInt(map.get("id")+""));
        Response response = Responses.successResponse();
        HashMap<String,Object> data = new HashMap<>();
        data.put("删除成功",200);
        response.setData(data);
        return response;
    }


}
