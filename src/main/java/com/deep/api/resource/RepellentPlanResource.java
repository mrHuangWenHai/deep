package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.RepellentPlanModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.RepellentPlanService;
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
import javax.validation.Valid;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/rp")
@Controller
public class RepellentPlanResource {


    @Resource
    private RepellentPlanService repellentPlanService;

    @Resource
    private UserService userService;



    /**
     * 返回插入结果
     * 成功：success
     * 失败：返回对应失败错误
     * METHOD:POST
     * @param repellentPlanModel
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveshow",method = RequestMethod.POST)
    public Response SaveShow(@RequestBody RepellentPlanModel repellentPlanModel,
                             //@RequestParam("factoryNum") BigInteger factoryNum,
                             //@RequestParam("crowdNum") String crowdNum,
                             //@RequestParam("repellentEartag") MultipartFile repellentEartag,
                             //@RequestParam("repellentTime") String repellentTime,
                             //@RequestParam("repellentName") String repellentName,
                             //@RequestParam("repellentWay") String repellentWay,
                             //@RequestParam("repellentQuality") String repellentQuality,
                             //@RequestParam("operator") String operator,
                             //@RequestParam("professor") String professor, 审核时自动插入
                             //@RequestParam("supervisor") String supervisor, 审核时自动插入
                             //@RequestParam("remark") String remark,
                             //@RequestParam("isPass") String isPass,  默认 未审核
                             //@RequestParam("unpassReason") String unpassReason, 默认 无
                             //@RequestParam("gmtCreate") Timestamp gmtCreate; 插入时自动生成
                             // 下同
                             HttpServletRequest request
    ) {
        if ("".equals(repellentPlanModel.getFactoryNum().toString()) ||
                "".equals(repellentPlanModel.getCrowdNum()) ||
                repellentPlanModel.getRepellentEartag().isEmpty() ||
                "".equals(repellentPlanModel.getRepellentTime()) ||
                "".equals(repellentPlanModel.getRepellentName()) ||
                "".equals(repellentPlanModel.getRepellentWay()) ||
                "".equals(repellentPlanModel.getRepellentQuality()) ||
                "".equals(repellentPlanModel.getOperator()) ||
                "".equals(repellentPlanModel.getRemark())) {
            return Responses.errorResponse("Lack Item");
        } else {
            try{
                RepellentPlanModel repellentPlanModel1 = repellentPlanService.getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(repellentPlanModel.getFactoryNum(),repellentPlanModel.getRepellentTime(),repellentPlanModel.getRepellentName());
                if (repellentPlanModel1 == null){
                    //上传文件
                    UploadUtil uploadUtil = new UploadUtil();
                    try{
                        String filepath = request.getSession().getServletContext().getContextPath()+"../EartagDocument/repellentEartag/"+"/";
                        try {
                            uploadUtil.uploadFile(repellentPlanModel.getRepellentEartag().getBytes(), filepath);
                            //System.out.println("saving file");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String ispass = "0";

                        repellentPlanService.setRepellentPlanModel(repellentPlanModel.getFactoryNum(),repellentPlanModel.getCrowdNum(),uploadUtil.getFilename(),
                                repellentPlanModel.getRepellentTime(),repellentPlanModel.getRepellentName(),repellentPlanModel.getRepellentWay(),repellentPlanModel.getRepellentQuality(),
                                repellentPlanModel.getOperator(),repellentPlanModel.getRemark(),ispass,ispass,timestamp);
                        //RepellentPlanModel repellentPlanModel1 = repellentPlanService.getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(factoryNum, repellentTime, repellentName);
                        //System.out.println("save before:"+ispass);

                        JedisUtil jedisUtil = new JedisUtil();
                        String professorKey = repellentPlanModel.getFactoryNum() + "_immunePlan_professor";
                        String supervisorKey = repellentPlanModel.getFactoryNum() + "_immunePlan_supervisor";
                        String testSendProfessor = repellentPlanModel.getFactoryNum() + "_immunePlan_professor_AlreadySend";
                        String testSendSupervisor = repellentPlanModel.getFactoryNum() + "_immunePlan_supervisor_AlreadySend";

                        jedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                        jedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                        //System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
                        //System.out.println("judge equal:"+"1".equals(jedisUtil.getCertainKeyValue(testSendProfessor)));

                        //若redis中 若干天未发送短信
                        //若未完成超过50条
                        if(!("1".equals(jedisUtil.getCertainKeyValue(testSendProfessor)))){
                            System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
                            if(jedisUtil.redisJudgeTime(professorKey)){
                                System.out.println(professorKey);

                                //获取redis中存储的设定过期时间
                                int expireTime = Integer.parseInt(jedisUtil.getCertainKeyValue("ExpireTime"));
                                List<UserModel> userModels = userService.getUserTelephoneByfactoryNum(repellentPlanModel.getFactoryNum());

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
                                List<UserModel> userModels = userService.getUserTelephoneByfactoryNum(repellentPlanModel.getFactoryNum());

                                System.out.println(jedisUtil.redisJudgeTime(supervisorKey));

                                StringBuffer phoneList = new StringBuffer("");
                                for(int i = 0; i < userModels.size(); i++){
                                    phoneList = phoneList.append(userModels.get(i).getTelephone()).append(",");
                                }
                                if(jedisUtil.redisSendMessage(phoneList.toString(),jedisUtil.getCertainKeyValue("Message"))){

                                    jedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor,"1",expireTime*24*60*60);
                                    System.out.println("发送成功！");

                                    HashMap<String,Object> data = new HashMap<>();
                                    data.put("successMessage","Message Sent");
                                    return Responses.successResponse(data);
                                }
                            }
                        }else {
                            System.out.println("supervisor:3天内已发送");
                        }

                        HashMap<String,Object> data = new HashMap<>();
                        data.put("successMessage","have sent message in 3 days");
                        return Responses.successResponse(data);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    return Responses.errorResponse("Already Exist");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  Responses.errorResponse("NullPointerException");
    }


    /**
     * 返回查询结果
     * 以json格式返回前端
     * METHOD:POST
     * @param repellentPlanModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response FindShow(@RequestBody RepellentPlanModel repellentPlanModel){

        System.out.println(repellentPlanModel.getFactoryNum());

        List<RepellentPlanModel> repellentPlanModels =repellentPlanService.getRepellentPlanModel(repellentPlanModel,
                new RowBounds(repellentPlanModel.getPage(),repellentPlanModel.getSize()));

        return JudgeUtil.JudgeFind(repellentPlanModels);

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
        List<RepellentPlanModel> repellentPlanModels = repellentPlanService.getRepellentPlanModelByProfessor(isPass1,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(repellentPlanModels);
    }


    /**
     * 专家入口 审核isPass1 = 0的数据
     * @param repellentPlanModel
     * METHOD:PATCH
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "pupdate",method = RequestMethod.PATCH)
    public Response ProfessorUpdate(@RequestBody RepellentPlanModel repellentPlanModel){
        int row = repellentPlanService.updateRepellentPlanModelByProfessor(repellentPlanModel);

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
        List<RepellentPlanModel> repellentPlanModels = repellentPlanService.getRepellentPlanModelBySupervisor(isPass2,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(repellentPlanModels);
    }

    /**
     * 专家入口 审核isPass2 = 0的数据
     * @param repellentPlanModel
     * METHOD:PATCH
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "supdate",method = RequestMethod.PATCH)
    public Response SupervisorUpdate(@RequestBody RepellentPlanModel repellentPlanModel){
        int row = repellentPlanService.updateRepellentPlanModelBySupervisor(repellentPlanModel);

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
        int row = repellentPlanService.deleteRepellentPlanModelByid(id);
        return JudgeUtil.JudgeDelete(row);
    }
}
