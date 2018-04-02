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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.sql.Timestamp;
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
                repellentPlanModel.getRepellentEartagFile().isEmpty() ||
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
                            uploadUtil.uploadFile(repellentPlanModel.getRepellentEartagFile().getBytes(), filepath);
                            //System.out.println("saving file");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                        repellentPlanService.setRepellentPlanModel(repellentPlanModel.getFactoryNum(),repellentPlanModel.getCrowdNum(),uploadUtil.getFilename(),
                                repellentPlanModel.getRepellentTime(),repellentPlanModel.getRepellentName(),repellentPlanModel.getRepellentWay(),repellentPlanModel.getRepellentQuality(),
                                repellentPlanModel.getOperator(),repellentPlanModel.getRemark(),"0","0",timestamp);
                        //RepellentPlanModel repellentPlanModel1 = repellentPlanService.getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(factoryNum, repellentTime, repellentName);
                        //System.out.println("save before:"+ispass);

                        String professorKey = repellentPlanModel.getFactoryNum() + "_repellentPlan_professor";
                        String supervisorKey = repellentPlanModel.getFactoryNum() + "_repellentPlan_supervisor";
                        String testSendProfessor = repellentPlanModel.getFactoryNum() + "_repellentPlan_professor_AlreadySend";
                        String testSendSupervisor = repellentPlanModel.getFactoryNum() + "_repellentPlan_supervisor_AlreadySend";

                        String professorWorkInRedis = repellentPlanModel.getId().toString()+ "_repellentPlan_professor_worked";
                        String supervisorWorkInRedis = repellentPlanModel.getId().toString()+ "_repellentPlan_supervisor_worked";

                        JedisUtil.setCertainKeyValue(professorWorkInRedis,"0");
                        JedisUtil.setCertainKeyValue(supervisorWorkInRedis,"0");

                        JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                        JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                        //System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
                        //System.out.println("judge equal:"+"1".equals(jedisUtil.getCertainKeyValue(testSendProfessor)));

                        //若redis中 若干天未发送短信
                        //若未完成超过50条
                        if(!("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))){
                            System.out.println("testSendProfessorValue:"+JedisUtil.getCertainKeyValue(testSendProfessor));
                            if(JedisUtil.redisJudgeTime(professorKey)){
                                System.out.println(professorKey);

                                List<UserModel> userModels = userService.getUserTelephoneByfactoryNum(repellentPlanModel.getFactoryNum());

                                //需完成:userModels.getTelephone()赋值给String
                                //获得StringBuffer手机号
                                StringBuffer phoneList = new StringBuffer("");
                                for(int i = 0; i < userModels.size(); i++){
                                    phoneList = phoneList.append(userModels.get(i).getTelephone()).append(",");
                                }

                                //发送成功 更新redis中字段
                                if(JedisUtil.redisSendMessage(phoneList.toString(),JedisUtil.getCertainKeyValue("Message"))){
                                    JedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor,"1",Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime"))*24*60*60);
                                }

                                //System.out.println(phoneList);
                            }
                        }else {
                            System.out.println("professor:3天内已发送");
                        }

                        if(!("1".equals(JedisUtil.getCertainKeyValue(testSendSupervisor)))){
                            if(JedisUtil.redisJudgeTime(supervisorKey)){

                                List<UserModel> userModels = userService.getUserTelephoneByfactoryNum(repellentPlanModel.getFactoryNum());

                                System.out.println(JedisUtil.redisJudgeTime(supervisorKey));

                                StringBuffer phoneList = new StringBuffer("");
                                for(int i = 0; i < userModels.size(); i++){
                                    phoneList = phoneList.append(userModels.get(i).getTelephone()).append(",");
                                }
                                if(JedisUtil.redisSendMessage(phoneList.toString(),JedisUtil.getCertainKeyValue("Message"))){

                                    JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor,"1",Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime"))*24*60*60);
                                    System.out.println("发送成功！");

                                    return  JudgeUtil.JudgeSuccess("successMessage","Message Sent");

                                }
                            }
                        }else {
                            System.out.println("supervisor:3天内已发送");
                            return  JudgeUtil.JudgeSuccess("successMessage","have sent message in  days");

                        }

                        return JudgeUtil.JudgeSuccess("successMessage","unnecessary send");

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

        if(repellentPlanModel.getSize() == 0){
            repellentPlanModel.setSize(10);
        }
        System.out.println(repellentPlanModel.getFactoryNum());

        List<RepellentPlanModel> repellentPlanModels =repellentPlanService.getRepellentPlanModel(repellentPlanModel,
                new RowBounds(repellentPlanModel.getPage(),repellentPlanModel.getSize()));

        return JudgeUtil.JudgeFind(repellentPlanModels,repellentPlanModels.size());

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
                                  @RequestParam(value = "page",defaultValue = "0") int page,
                                  @RequestParam(value = "size",defaultValue = "10") int size){

        List<RepellentPlanModel> repellentPlanModels = repellentPlanService.getRepellentPlanModelByProfessor(isPass1,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(repellentPlanModels,repellentPlanModels.size());
    }


    /**
     * 专家入口 审核isPass1 = 0的数据
     * redis中数据-1
     * @param repellentPlanModel
     * METHOD:PATCH
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "pupdate",method = RequestMethod.PATCH)
    public Response ProfessorUpdate(@RequestBody RepellentPlanModel repellentPlanModel) {


        if (repellentPlanModel.getId() == null ||
                repellentPlanModel.getProfessor() == null ||
                repellentPlanModel.getIsPass1() == null ||
                repellentPlanModel.getUnpassReason1() == null) {
            return Responses.errorResponse("Lack Item");
        } else {
            //System.out.println("before:"+repellentPlanModel.getId());

            RepellentPlanModel repellentPlanModel1 = repellentPlanService.getRepellentPlanModelByid(repellentPlanModel.getId());

            String professorWorkInRedis = repellentPlanModel1.getId().toString() + "_repellentPlan_professor_worked";

            //return new Response().addData("",repellentPlanModel1);
            //System.out.println("isNull?"+repellentPlanModel1.getId());
            if (repellentPlanModel1.getIsPass1().equals("1") ) {

                return Responses.errorResponse("Already update");
            } else {

                if ("1".equals(JedisUtil.getCertainKeyValue(professorWorkInRedis))) {

                    repellentPlanModel.setGmtProfessor(new Timestamp(System.currentTimeMillis()));

                    int row = repellentPlanService.updateRepellentPlanModelByProfessor(repellentPlanModel);
                    return JudgeUtil.JudgeUpdate(row);

                }else{

                    int row = this.repellentPlanService.updateRepellentPlanModelByProfessor(repellentPlanModel);
                    if (row == 0) {
                        return JudgeUtil.JudgeUpdate(row);
                    } else {

                        //删除成功 redis数据库种对应数据-1
                        String professorKey = repellentPlanModel1.getFactoryNum().toString() + "_repellentPlan_professor";

                        JedisUtil.setCertainKeyValue(professorWorkInRedis,"1");
                        //key->value-1 返回true
                        if (JedisUtil.redisCancelProfessorSupervisorWorks(professorKey)) {
                            return JudgeUtil.JudgeUpdate(row);
                        } else {
                            //此时数据库出现较大问题
                            //未完成工作实际数量与redis记录不一样
                            return Responses.errorResponse("Inner Error");
                        }
                    }

                }
            }
        }
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
                                   @RequestParam(value = "page",defaultValue = "0") int page,
                                   @RequestParam(value = "size",defaultValue = "10") int size){
        List<RepellentPlanModel> repellentPlanModels = repellentPlanService.getRepellentPlanModelBySupervisor(isPass2,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(repellentPlanModels,repellentPlanModels.size());
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


        if( repellentPlanModel.getId() == null||
                repellentPlanModel.getSupervisor() == null||
                repellentPlanModel.getIsPass2()== null||
                repellentPlanModel.getUnpassReason2() == null){
            return Responses.errorResponse("Lack Item");

        }else {
            //若该id数据已被审核 直接返回
            RepellentPlanModel repellentPlanModel1 = repellentPlanService.getRepellentPlanModelByid(repellentPlanModel.getId());

            String supervisorWorkInRedis = repellentPlanModel1.getId().toString() + "_repellentPlan_supervisor_worked";


            if (repellentPlanModel1.getIsPass2().equals("1")){

                return Responses.errorResponse("Already update");
            }else {

                if ("1".equals(JedisUtil.getCertainKeyValue(supervisorWorkInRedis))) {

                    repellentPlanModel.setGmtSupervise(new Timestamp(System.currentTimeMillis()));
                    int row = repellentPlanService.updateRepellentPlanModelBySupervisor(repellentPlanModel);

                    return JudgeUtil.JudgeUpdate(row);
                }else{

                    repellentPlanModel.setGmtSupervise(new Timestamp(System.currentTimeMillis()));
                    int row = repellentPlanService.updateRepellentPlanModelBySupervisor(repellentPlanModel);

                    if( row == 0){
                        return JudgeUtil.JudgeUpdate(row);
                    }else {

                        //删除成功 redis数据库种对应数据-1
                        String supervisorKey = repellentPlanModel1.getFactoryNum().toString() + "_repellentPlan_supervisor";
                        JedisUtil.setCertainKeyValue(supervisorWorkInRedis,"1");

                        //key->value-1 返回true
                        if (JedisUtil.redisCancelProfessorSupervisorWorks(supervisorKey)) {
                            return JudgeUtil.JudgeUpdate(row);

                        } else {
                            //此时数据库出现较大问题
                            //未完成工作实际数量与redis记录不一样
                            return Responses.errorResponse("Inner Error");
                        }
                    }
                }
            }
        }
    }



    /**
     * 操作员在审核前想修改数据的接口
     * 或处理被退回操作的接口
     *
     * 行为1 与redis数据库无关
     * 行为2 redis对应数据字段+1
     * @param repellentPlanModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "oupdate",method = RequestMethod.PATCH)
    public Response OperatorUpdate(@RequestBody RepellentPlanModel repellentPlanModel) {

        if (repellentPlanModel.getId() == null) {
            return Responses.errorResponse("Operate wrong");
        } else {

            RepellentPlanModel repellentPlanModel1 = this.repellentPlanService.getRepellentPlanModelByid(repellentPlanModel.getId());

            String professorWorkInRedis = repellentPlanModel1.getId().toString() + "_repellentPlan_professor_worked";
            String supervisorWorkInRedis = repellentPlanModel1.getId().toString() + "_repellentPlan_supervisor_worked";


            //即专家未审核 操作员需要修改
            if(repellentPlanModel1.getProfessor() == null){

                //直接修改 与redis中数据无关
                System.out.println(JedisUtil.getCertainKeyValue(professorWorkInRedis));
            }else {

                //专家已审核 退回后的数据
                String professorKey = repellentPlanModel1.getFactoryNum().toString()+ "_repellentPlan_professor";

                JedisUtil.setCertainKeyValue(professorWorkInRedis,"0");
                //redis数据库中对应字段+1
                JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
            }

            //监督员未审核 操作员需要修改
            if (repellentPlanModel1.getSupervisor() == null){
                //System.out.println("No redis");
                int row = this.repellentPlanService.updateRepellentPlanModelByOperator(repellentPlanModel);
                return JudgeUtil.JudgeUpdate(row);

            }else{

                //专家已审核 退回后的数据
                int row = this.repellentPlanService.updateRepellentPlanModelByOperator(repellentPlanModel);
                String supervisorKey = repellentPlanModel1.getFactoryNum().toString()+ "_repellentPlan_supervisor";

                JedisUtil.setCertainKeyValue(supervisorWorkInRedis,"0");
                //redis数据库中对应字段+1
                JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                return JudgeUtil.JudgeUpdate(row);
            }

        }
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
