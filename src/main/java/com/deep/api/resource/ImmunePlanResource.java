package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.ImmunePlanModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.ImmunePlanService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;
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
@RequestMapping(value = "/ip")
public class ImmunePlanResource {

    @Resource
    private ImmunePlanService immunePlanService;

    //用于查询专家/监督员电话并抉择发送短信
    @Resource
    private UserService userService;


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
                immunePlanModel.getImmuneEartagFile().isEmpty() ||
                "".equals(immunePlanModel.getImmuneTime()) ||
                "".equals(immunePlanModel.getImmuneKind()) ||
                "".equals(immunePlanModel.getImmuneWay()) ||
                "".equals(immunePlanModel.getImmuneQuality()) ||
                "".equals(immunePlanModel.getImmuneDuring()) ||
                "".equals(immunePlanModel.getOperator()) ||
                "".equals(immunePlanModel.getRemark())) {
            return Responses.errorResponse("Lack Item");
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
                            uploadUtil.uploadFile(immunePlanModel.getImmuneEartagFile().getBytes(), filepath);
                            //System.out.println("saving file");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //数据插入数据库
                        //System.out.println("mysql执行前");
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String isPass = "0";
                        immunePlanService.setImmunePlanModel(immunePlanModel.getFactoryNum(),immunePlanModel.getCrowdNum(),uploadUtil.getFilename(),
                                immunePlanModel.getImmuneTime(), immunePlanModel.getImmuneKind(),immunePlanModel.getImmuneWay(),immunePlanModel.getImmuneQuality(),
                                immunePlanModel.getImmuneDuring(),immunePlanModel.getOperator(),immunePlanModel.getRemark(), isPass,isPass,timestamp);

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

                                    HashMap<String,Object> data = new HashMap<>();
                                    data.put("successMessage","Message Sent");
                                    return Responses.successResponse(data);
                                }
                            }
                        }else {
                            System.out.println("supervisor:3天内已发送");
                            HashMap<String,Object> data = new HashMap<>();
                            data.put("successMessage","have sent message in 3 days");
                            return Responses.successResponse(data);
                        }
                        HashMap<String,Object> data = new HashMap<>();
                        data.put("successMessage","unnecessary send");
                        return Responses.successResponse(data);
                        //jedisUtil.redisSaveProfessorSupervisorWorks(professorKey,factoryNum);
                        //jedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey,factoryNum);
                        }catch (Exception e){

                            e.printStackTrace();
                        }

                    }else {
                        return Responses.errorResponse("Already Exist");
                    }
                }catch (Exception e){

                    e.printStackTrace();
                    return Responses.errorResponse("IOException");
                }

        }
        return Responses.errorResponse("NullPointerException");
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
        List<ImmunePlanModel> immunePlanModels = immunePlanService.getImmunePlanModel(immunePlanModel,
                new RowBounds(immunePlanModel.getPage(),immunePlanModel.getSize()));

        return JudgeUtil.JudgeFind(immunePlanModels,immunePlanModels.size());
    }


    /**
     * 专家入口 展示所有isPass1 = 0或者isPass1 = 1的数据
     * @param isPass1
     * @param page
     * @param size
     * METHOD:GET
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pfind",method = RequestMethod.GET)
    public Response ProfessorFind(@RequestParam("isPass1") Integer isPass1,
                                  @RequestParam("page") int page,
                                  @RequestParam("size") int size){
        List<ImmunePlanModel> immunePlanModels = immunePlanService.getImmunePlanModelByProfessor(isPass1,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(immunePlanModels,immunePlanModels.size());
    }


    /**
     * 专家入口 审核isPass1 = 0或者isPass1 = 1的数据
     * @param immunePlanModel
     * METHOD:PATCH
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "pupdate",method = RequestMethod.PATCH)
    public Response ProfessorUpdate(@RequestBody ImmunePlanModel immunePlanModel){
        int row = immunePlanService.updateImmunePlanModelByProfessor(immunePlanModel);

        return JudgeUtil.JudgeUpdate(row);
    }


    /**
     * 审核入口 展示所有isPass2 = 0或者isPass2 = 1的数据
     * @param isPass2
     * @param page
     * @param size
     * METHOD:GET
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sfind",method = RequestMethod.GET)
    public Response SupervisorFind(@RequestParam("isPass2") Integer isPass2,
                                   @RequestParam("page") int page,
                                   @RequestParam("size") int size){
        List<ImmunePlanModel> immunePlanModels = immunePlanService.getImmunePlanModelBySupervisor(isPass2,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(immunePlanModels,immunePlanModels.size());

    }

    /**
     * 专家入口 审核isPass2 = 0的数据
     * @param immunePlanModel
     * METHOD:PATCH
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "supdate",method = RequestMethod.PATCH)
    public Response SupervisorUpdate(@RequestBody ImmunePlanModel immunePlanModel){
        int row = immunePlanService.updateImmunePlanModelBySupervisor(immunePlanModel);

        return JudgeUtil.JudgeUpdate(row);
    }

    /**
     * 删除id = certain 的数据
     * 权限设置
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Response Delete(@RequestParam("id") BigInteger id){
        int row = immunePlanService.deleteImmunePlanModelByid(id);

        return JudgeUtil.JudgeDelete(row);
    }

}
