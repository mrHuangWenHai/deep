package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.ImmunePlanModel;
import com.deep.domain.service.ImmunePlanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;


@Controller
@RequestMapping(value = "/allfunction/ip")
public class ImmunePlanResourceController {
    @Resource
    private ImmunePlanService immunePlanService;

    @RequestMapping(value = "/function")
    public String ImmunePlanFunctionChoice(){
        return "ImmunePlanHTML/ImmunePlanFunctionChoiceForm";
    }

    @RequestMapping(value = "/save")
    public String Save(){
        return "ImmunePlanHTML/ImmunePlanSaveForm";
    }

    @ResponseBody
    @RequestMapping(value = "/saveshow", method = RequestMethod.POST)
    public Response SaveShow(@RequestParam("factoryNum") BigInteger factoryNum,
                           @RequestParam("crowdNum") String crowdNum,
                           @RequestParam("immuneEartag") MultipartFile immuneEartag,
                           @RequestParam("immuneTime") String immuneTime,
                           @RequestParam("immuneKind") String immuneKind,
                           @RequestParam("immuneWay") String immuneWay,
                           @RequestParam("immuneQuality") String immuneQuality,
                           @RequestParam("immuneDuring") String immuneDuring,
                           @RequestParam("operator") String operator,
                           @RequestParam("remark") String remark,
                           HttpServletRequest request)  {
        /*Jedis jedis = new Jedis("localhost");
        jedis.get("userId");
        jedis.get("token");
        System.out.println(jedis.get("userId")+ jedis.get("token"));
        System.out.println("查看userId的剩余生存时间："+jedis.ttl("userId"));*/
        Map<String, Object> map = new HashMap<>();
        String filename;
        if ("".equals(factoryNum.toString()) ||
                "".equals(crowdNum) ||
                immuneEartag.isEmpty() ||
                "".equals(immuneTime) ||
                "".equals(immuneKind) ||
                "".equals(immuneWay) ||
                "".equals(immuneQuality) ||
                "".equals(immuneDuring) ||
                "".equals(operator) ||
                "".equals(remark)) {
            map.put("Result", "Fail");
            map.put("type", "lack item");
            return new Response().addData("Error", map);
        } else {
            try{
                ImmunePlanModel immunePlanModel = immunePlanService.getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(factoryNum,crowdNum,immuneTime);
                //文件上传并将数据保存到数据库中
                if(immunePlanModel == null) {
                    try{
                        //建立upload路径
                        String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/"+"immune/";
                        File dir = new File(uploadDir);
                        if(dir.exists()){
                            dir.mkdir();
                        }
                        //后缀名
                        //文件保存名
                        String suffix = ".txt";
                        DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddhhMMss");
                        Calendar calendar = Calendar.getInstance();
                        //文件保存全名
                        filename = dateFormat1.format(calendar.getTime()) + suffix;
                        File serverFile = new File(uploadDir + filename);

                        //文件上传
                        immuneEartag.transferTo(serverFile);

                        //数据插入数据库
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String isPass = "0";
                        immunePlanService.setImmunePlanModel(new ImmunePlanModel(factoryNum,crowdNum,filename,immuneTime,
                                immuneKind,immuneWay,immuneQuality,immuneDuring,operator,
                                remark,isPass,isPass,timestamp,timestamp));
                        return new Response().addData("Success","");
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }else {
                        map.put("Result","Fail");
                        map.put("type","already exist");
                        return new Response().addData("Error", map);
                    }
                }catch (Exception e){

                    e.printStackTrace();
                map.put("Result", "Fail");
                map.put("type", "IO Exception");
                return new Response().addData("Error", map);
                }

        }
        map.put("Result", "Fail");
        map.put("type", "Parse Exception");
        return new Response().addData("Error", map);
    }

    @RequestMapping(value = "/find")
    public String Find()throws Exception{
        /*Jedis jedis = new Jedis("localhost");
        jedis.get("userId");
        jedis.get("token");
        System.out.println(jedis.get("userId")+ jedis.get("token"));
        System.out.println("查看userId的剩余生存时间："+jedis.ttl("userId"));*/

        return "ImmunePlanHTML/ImmunePlanFindForm";
    }

    @ResponseBody
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response FindShow(@RequestParam("factoryNum") BigInteger factoryNum,
                                 @RequestParam("crowdNum") String crowdNum,
                                 @RequestParam("immuneEartagStart") String immuneEartagStart,
                                 @RequestParam("immuneEartagEnd") String immuneEartagEnd,
                                 @RequestParam("immuneTimeStart") String immuneTimeStart,
                                 @RequestParam("immuneTimeEnd") String immuneTimeEnd,
                                 @RequestParam("immuneKind") String immuneKind,
                                 @RequestParam("immuneWay") String immuneWay,
                                 @RequestParam("immuneQuality") String immuneQuality,
                                 @RequestParam("immuneDuring") String immuneDuring,
                                 @RequestParam("operator") String operator,
                                 @RequestParam("professor") String professor,
                                 @RequestParam("supervisor") String supervisor,
                                 @RequestParam("remark") String remark,
                                 @RequestParam("isPass1") String  isPass1,
                                 @RequestParam("unpassReason1") String unpassReason1,
                                 @RequestParam("isPass2") String isPass2,
                                 @RequestParam("unpassReason2") String unpassReason2) {
        List<ImmunePlanModel> immunePlanModels =immunePlanService.getImmunePlanModel(factoryNum,crowdNum, immuneEartagStart,
                                                    immuneEartagEnd,immuneTimeStart,immuneTimeEnd,immuneKind,immuneWay,immuneQuality,
                                                    immuneDuring,operator,professor,supervisor,remark,
                                                    isPass1,unpassReason1,isPass2,unpassReason2);
        return new Response().addData("List<immunePlanModels>",immunePlanModels);
    }


    ////////
}
