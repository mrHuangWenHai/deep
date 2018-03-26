package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.Message;
import com.deep.domain.model.MessageExample;
import com.deep.domain.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class MessageController {

    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/messageboard",method = RequestMethod.GET)
    public String upload(){

        return "insertMessage";
    }

    @RequestMapping(value = "/messageboard/insert",method = RequestMethod.POST)
    public @ResponseBody
    Response addMessage(@Valid Message message,
                        HttpServletRequest request)
    {
        message.setUsername(message.getUsername());
        message.setContact(message.getContact());
        message.setMessage(message.getMessage());
        message.setInserttime(new Date());
        //数据分析相关
        message.setTag(message.getTag());
        message.setAttitude(message.getAttitude());
        message.setIntention(message.getIntention());

        //留言追加写入到message.txt文件中用作数据分析
        String path=request.getSession().getServletContext().getContextPath()+"../message/";

        File f = new File(path);

        if(!f.exists())
        {
            f.mkdirs();
        }

        String fileName = "message.txt";

        File file = new File(path,fileName);

        if (!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String fileAddress = path+fileName;

        try{
            FileWriter writer = new FileWriter(fileAddress,true);
            //由于在linux和windows中换行符的不同
            //在程序我们应尽量使用System.getProperty("line.separator")来获取当前系统的换
            //行符，而不是写/r/n或/n。
            writer.write(message.getMessage()+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        messageService.insertMessage(message);

        MessageExample messageExample =new MessageExample();
        MessageExample.Criteria criteria=messageExample.createCriteria();
        criteria.andIdEqualTo(message.getId());
        List<Message> select=messageService.findMessageSelective(messageExample);

        Response response= Responses.successResponse();
        HashMap<String,Object>data=new HashMap<>();
        data.put("Message",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/messageboard/searchByMessage",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByMessage(@RequestParam(value = "message",required = false,defaultValue = "")String message,
                             @RequestParam(value = "pageNumb",required = true)int pageNumb,
                             @RequestParam(value = "limit",required = true)int limit)
    {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andMessageLike("%" + message+"%");
        List<Message>select = messageService.findMessageSelectiveWithRowbounds(messageExample,pageNumb,limit);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByMessage",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/messageboard/searchByUsername",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByUsername(@RequestParam(value = "username",required = false,defaultValue = "")String username,
                              @RequestParam(value = "pageNumb",required = true)int pageNumb,
                              @RequestParam(value = "limit",required = true)int limit) {

        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUsernameLike("%" + username+"%");
        List<Message>select=messageService.findMessageSelectiveWithRowbounds(messageExample,pageNumb,limit);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByUsername",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/messageboard/searchByTag",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByTag(@RequestParam(value = "tag",required = false,defaultValue = "")String tag,
                              @RequestParam(value = "pageNumb",required = true)int pageNumb,
                              @RequestParam(value = "limit",required = true)int limit)
    {
        MessageExample messageExample=new MessageExample();
        MessageExample.Criteria criteria=messageExample.createCriteria();
        criteria.andTagLike("%"+tag+"%");
        List<Message>select=messageService.findMessageSelectiveWithRowbounds(messageExample,pageNumb,limit);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByTag",select);
        response.setData(data);
        return response;
    }
    @RequestMapping(value = "/messageboard/searchByAttitude",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByAttitude(@RequestParam(value = "attitude",required = false,defaultValue = "")String attitude ,
                              @RequestParam(value = "pageNumb",required = true)int pageNumb,
                              @RequestParam(value = "limit",required = true)int limit)
    {
        MessageExample messageExample=new MessageExample();
        MessageExample.Criteria criteria=messageExample.createCriteria();
        criteria.andAttitudeLike("%"+attitude+"%");
        List<Message>select=messageService.findMessageSelectiveWithRowbounds(messageExample,pageNumb,limit);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByAttitude",select);
        response.setData(data);
        return response;
    }
    @RequestMapping(value = "/messageboard/searchByIntention",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByIntention(@RequestParam(value = "intention",required = false,defaultValue = "")String intention,
                              @RequestParam(value = "pageNumb",required = true)int pageNumb,
                              @RequestParam(value = "limit",required = true)int limit)
    {
        MessageExample messageExample=new MessageExample();
        MessageExample.Criteria criteria=messageExample.createCriteria();
        criteria.andIntentionLike("%"+intention+"%");
        List<Message>select=messageService.findMessageSelectiveWithRowbounds(messageExample,pageNumb,limit);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByIntention",select);
        response.setData(data);
        return response;
    }






//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public String (){
//
//        return "";
//    }
//
//
//    @RequestMapping(value = "/",method = RequestMethod.POST)
//    public @ResponseBody Response ()
//    {
//
//    }


}
