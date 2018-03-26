package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.domain.model.ImmunePlanModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.ImmunePlanService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.UploadUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    //用于查询专家/监督员电话并抉择发送短信
    @Resource
    private UserService userService;


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
     * 插入时mysql 在Redis插入数据 用于提醒专家/监督员完成审核任务
     * Key:"factory_num+模块名+专家/监督员"
     * Value:未审核条数
     *
     * METHOD:POST
     * @param immunePlanModel
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveshow", method = RequestMethod.POST)
    public Response SaveShow(@RequestBody ImmunePlanModel immunePlanModel,
                             //@RequestParam("factoryNum") BigInteger factoryNum,
                             //@RequestParam("crowdNum") String crowdNum,
                             //@RequestParam("immuneEartag") MultipartFile immuneEartag,
                             //@RequestParam("immuneTime") String immuneTime,
                             //@RequestParam("immuneKind") String immuneKind,
                             //@RequestParam("immuneWay") String immuneWay,
                             //@RequestParam("immuneQuality") String immuneQuality,
                             //@RequestParam("immuneDuring") String immuneDuring,
                             //@RequestParam("operator") String operator,
                             //@RequestParam("remark") String remark,
                             HttpServletRequest request)  {
        /*Jedis jedis = new Jedis("localhost");
        jedis.get("userId");
        jedis.get("token");
        System.out.println(jedis.get("userId")+ jedis.get("token"));
        System.out.println("查看userId的剩余生存时间："+jedis.ttl("userId"));*/
        if ("".equals(immunePlanModel.getFactoryNum().toString()) ||
                "".equals(immunePlanModel.getCrowdNum()) ||
                immunePlanModel.getImmuneEartag().isEmpty() ||
                "".equals(immunePlanModel.getImmuneTime()) ||
                "".equals(immunePlanModel.getImmuneKind()) ||
                "".equals(immunePlanModel.getImmuneWay()) ||
                "".equals(immunePlanModel.getImmuneQuality()) ||
                "".equals(immunePlanModel.getImmuneDuring()) ||
                "".equals(immunePlanModel.getOperator()) ||
                "".equals(immunePlanModel.getRemark())) {
            return new Response().addData("Error", "Lack Item");
        } else {
            try{
                ImmunePlanModel immunePlanModel1 = immunePlanService.getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(immunePlanModel.getFactoryNum(), immunePlanModel.getCrowdNum(), immunePlanModel.getImmuneTime());
                //文件上传并将数据保存到数据库中
                //System.out.println("save before");
                if(immunePlanModel1 == null) {
                    UploadUtil uploadUtil = new UploadUtil();
                    try{
                        String filepath = request.getSession().getServletContext().getContextPath()+"../EartagDocument/immuneEartag/"+"/";
                        //System.out.println("in saving");
                        //if (!immuneEartag.isEmpty()) System.out.println("immuneEartag is ready");
                        //System.out.println(filepath);
                        try {
                            uploadUtil.uploadFile(immunePlanModel.getImmuneEartag().getBytes(), filepath);
                            //System.out.println("saving file");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //数据插入数据库
                        //System.out.println("mysql执行前");
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String isPass = "0";
                        immunePlanService.setImmunePlanModel(new ImmunePlanModel(immunePlanModel.getFactoryNum(),immunePlanModel.getCrowdNum(),uploadUtil.getFilename(),
                                immunePlanModel.getImmuneTime(), immunePlanModel.getImmuneKind(),immunePlanModel.getImmuneWay(),immunePlanModel.getImmuneQuality(),
                                immunePlanModel.getImmuneDuring(),immunePlanModel.getOperator(),immunePlanModel.getRemark(), isPass,isPass,timestamp,timestamp));

                        //数据插入redis
                        JedisUtil jedisUtil = new JedisUtil();
                        String professorKey = immunePlanModel.getFactoryNum() + "_immunePlan_professor";
                        String supervisorKey = immunePlanModel.getFactoryNum() + "_immunePlan_supervisor";
                        String testSendProfessor = immunePlanModel.getFactoryNum() + "_immunePlan_professor_AlreadySend";
                        String testSendSupervisor = immunePlanModel.getFactoryNum() + "_immunePlan_supervisor_AlreadySend";

                        jedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                        jedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                        //System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
                        //System.out.println("judge equal:"+"1".equals(jedisUtil.getCertainKeyValue(testSendProfessor)));

                        //若redis中 若干天未发送短信
                        //若未完成超过50条
                        if(!("1".equals(jedisUtil.getCertainKeyValue(testSendProfessor)))){
                            System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
                            if(jedisUtil.redisJudgeTime(professorKey)){

                                //获取redis中存储的设定过期时间
                                int expireTime = Integer.parseInt(jedisUtil.getCertainKeyValue("ExpireTime"));
                                List<UserModel> userModels = userService.getUserTelephoneByfactoryNum(immunePlanModel.getFactoryNum());

                                //需完成:userModels.getTelephone()赋值给String
                                //获得StringBuffer手机号
                                StringBuffer phoneList = new StringBuffer("");
                                for(int i = 0; i < userModels.size(); i++){
                                    phoneList = phoneList.append(userModels.get(i).getTelephone()).append(",");
                                }

                                //发送成功 更新redis中字段
                                if(jedisUtil.redisSendMessage(phoneList.toString(),jedisUtil.getCertainKeyValue("Message"))){
                                    jedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor,"1",expireTime*24*60*60);
                                }

                                //System.out.println(phoneList);
                            }
                        }else {
                            System.out.println("professor:3天内已发送");
                        }

                        if(!("1".equals(jedisUtil.getCertainKeyValue(testSendSupervisor)))){
                            if(jedisUtil.redisJudgeTime(supervisorKey)){
                                int expireTime = Integer.parseInt(jedisUtil.getCertainKeyValue("ExpireTime"));
                                List<UserModel> userModels = userService.getUserTelephoneByfactoryNum(immunePlanModel.getFactoryNum());

                                StringBuffer phoneList = new StringBuffer("");
                                for(int i = 0; i < userModels.size(); i++){
                                    phoneList = phoneList.append(userModels.get(i).getTelephone()).append(",");
                                }
                                if(jedisUtil.redisSendMessage(phoneList.toString(),jedisUtil.getCertainKeyValue("Message"))){
                                    System.out.println("发送成功！");
                                    jedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor,"1",expireTime*24*60*60);
                                    return new Response().addData("Success","Send ok");
                                }
                            }
                        }else {
                            System.out.println("supervisor:3天内已发送");
                        }
                        return new Response().addData("Success","Uneccessary send");
                        //jedisUtil.redisSaveProfessorSupervisorWorks(professorKey,factoryNum);
                        //jedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey,factoryNum);
                        }catch (Exception e){

                            e.printStackTrace();
                        }

                    }else {
                        return new Response().addData("Error", "Already exist");
                    }
                }catch (Exception e){

                    e.printStackTrace();
                    return new Response().addData("Error", "IO Exception");
                }

        }
        return new Response().addData("Error", "NullPointer Exception");
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
     * @param immunePlanModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response FindShow(@RequestBody ImmunePlanModel immunePlanModel) {
        //前台传参数
        RowBounds bounds = new RowBounds(0,2);
        List<ImmunePlanModel> immunePlanModels = immunePlanService.getImmunePlanModel(immunePlanModel.getFactoryNum(),
                immunePlanModel.getCrowdNum(),immunePlanModel.getImmuneEartag(),immunePlanModel.getImmuneEartag(),
                immunePlanModel.getImmuneTimeStart(),immunePlanModel.getImmuneTimeEnd(),immunePlanModel.getImmuneKind(),
                immunePlanModel.getImmuneWay(),immunePlanModel.getImmuneQuality(),immunePlanModel.getImmuneDuring(),
                immunePlanModel.getOperator(),immunePlanModel.getProfessor(),immunePlanModel.getSupervisor(),
                immunePlanModel.getRemark(),immunePlanModel.getIsPass1(),immunePlanModel.getUnpassReason1(),
                immunePlanModel.getIsPass2(),immunePlanModel.getUnpassReason2(),bounds);
        return new Response().addData("List<immunePlanModels>",immunePlanModels);
    }


    /**
     * 专家入口 展示所有isPass1 = 0的数据
     * 审核
     * METHOD:GET
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "updateprofessor",method = RequestMethod.GET)
    public Response UpdateProfessor(){
        //
        RowBounds bounds = new RowBounds(0,20);
        List<ImmunePlanModel> immunePlanModels = immunePlanService.getImmunePlanModelByProfessor(bounds);
        return new Response().addData("List<immunePlanModels>",immunePlanModels);
        //
    }


    ////////
}
