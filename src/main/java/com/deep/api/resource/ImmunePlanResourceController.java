package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.ImmunePlanModel;
import com.deep.domain.service.ImmunePlanService;
import com.deep.domain.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;


@Controller
@RequestMapping(value = "/allfunction/ip")
public class ImmunePlanResourceController {

    @Resource
    private ImmunePlanService immunePlanService;

    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "/function",method = RequestMethod.POST)
    public String ImmunePlanFunctionChoice(){
        return "ImmunePlanHTML/ImmunePlanFunctionChoiceForm";
    }

    /**
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "/save")
    public String Save(){
        return "ImmunePlanHTML/ImmunePlanSaveForm";
    }

    /**
     * 返回插入结果
     * 成功：success
     * 失败：返回对应失败错误
     * METHOD:POST
     * @param factoryNum
     * @param crowdNum
     * @param immuneEartag
     * @param immuneTime
     * @param immuneKind
     * @param immuneWay
     * @param immuneQuality
     * @param immuneDuring
     * @param operator
     * @param remark
     * @param request
     * @return
     */
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
                //System.out.println("save before");
                if(immunePlanModel == null) {
                    UploadUtil uploadUtil = new UploadUtil();
                    try{
                        String filepath = request.getSession().getServletContext().getContextPath()+"../EartagDocument/immuneEartag/"+"/";
                        //System.out.println("in saving");
                        //if (!immuneEartag.isEmpty()) System.out.println("immuneEartag is ready");
                        //System.out.println(filepath);
                        try {
                            uploadUtil.uploadFile(immuneEartag.getBytes(), filepath);
                            //System.out.println("saving file");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //数据插入数据库
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String isPass = "0";
                        immunePlanService.setImmunePlanModel(new ImmunePlanModel(factoryNum,crowdNum,uploadUtil.getFilename(),immuneTime,
                                immuneKind,immuneWay,immuneQuality,immuneDuring,operator,
                                remark,isPass,isPass,timestamp,timestamp));
                        return new Response().addData("Success","");
                        }catch (Exception e){
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
        map.put("type", "NullPointer Exception");
        return new Response().addData("Error", map);
    }

    /**
     * METHOD:GET
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public String Find()throws Exception{
        /*Jedis jedis = new Jedis("localhost");
        jedis.get("userId");
        jedis.get("token");
        System.out.println(jedis.get("userId")+ jedis.get("token"));
        System.out.println("查看userId的剩余生存时间："+jedis.ttl("userId"));*/

        return "ImmunePlanHTML/ImmunePlanFindForm";
    }

    /**
     * 返回查询结果
     * 以json格式返回前端
     * 分页查询
     * METHOD:POST
     * @param factoryNum
     * @param crowdNum
     * @param immuneEartagStart
     * @param immuneEartagEnd
     * @param immuneTimeStart
     * @param immuneTimeEnd
     * @param immuneKind
     * @param immuneWay
     * @param immuneQuality
     * @param immuneDuring
     * @param operator
     * @param professor
     * @param supervisor
     * @param remark
     * @param isPass1
     * @param unpassReason1
     * @param isPass2
     * @param unpassReason2
     * @return
     */
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
