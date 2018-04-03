package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.DisinfectFilesModel;
import com.deep.domain.model.UserModel;
import com.deep.domain.service.DisinfectFilesService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.util.List;


@Controller
@RequestMapping(value = "/df",method = RequestMethod.GET)
public class DisinfectFilesResource {

    @Resource
    private DisinfectFilesService disinfectFilesService;
    //用于查询专家/监督员电话并抉择发送短信
    @Resource
    private UserService userService;



    /**
     * 返回插入结果
     * 成功：success
     * 失败：返回对应失败错误
     *
     * 同时 若累计未审核超过50条（自定义）
     * 3天内未通知对应专家/审核员
     * 则通知 不然返回已发送
     * METHOD:POST
     * @param disinfectFilesModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveshow",method = RequestMethod.POST)
    public Response SaveShow(@RequestBody DisinfectFilesModel disinfectFilesModel
                             //@RequestParam("professor") String professor,    //可空 技术审核时自动生成
                             //@RequestParam("supervisor") String supervisor,   //可空 监督员审核时自动生成
                             //@RequestParam("isPass") String isPass,    //可空 由技术审核填写 默认值 未审核
                             //@RequestParam("unpassReason") String unpassReason,   //可空 由技术审核填写 默认值 无
                             //表单提交自动生成
                             //@RequestParam("gmtCreate") Timestamp gmtCreate,
                             //@RequestParam("gmtModified") Timestamp gmtModified,
                             //@RequestParam("gmtProfessor") Timestamp gmtProfessor,   //可空 技术审核填写后自动生成
                             //@RequestParam("gmtSupervise") Timestamp gmtSupervise    //可空 监督员审核后自动生成
                            ){
        if("".equals(disinfectFilesModel.getFactoryNum().toString())||
                "".equals(disinfectFilesModel.getDisinfectTime())||
                "".equals(disinfectFilesModel.getDisinfectName())||
                "".equals(disinfectFilesModel.getDisinfectQuality())||
                "".equals(disinfectFilesModel.getDisinfectWay())||
                "".equals(disinfectFilesModel.getOperator())||
                "".equals(disinfectFilesModel.getRemark())){
            return Responses.errorResponse("Lack Item");

        }else {
            DisinfectFilesModel disinfectFilesModel1 = disinfectFilesService.getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(disinfectFilesModel.getFactoryNum(), disinfectFilesModel.getDisinfectTime(), disinfectFilesModel.getDisinfectName());
            if (disinfectFilesModel1 == null) {
                try{
                    //System.out.println("save before");
                    //数据插入数据库
                    //System.out.println("mysql执行前");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    disinfectFilesService.setDisinfectFilesModel(new DisinfectFilesModel(disinfectFilesModel.getFactoryNum(), disinfectFilesModel.getDisinfectTime(),
                            disinfectFilesModel.getDisinfectName(), disinfectFilesModel.getDisinfectQuality(),
                            disinfectFilesModel.getDisinfectWay(), disinfectFilesModel.getOperator(),
                            disinfectFilesModel.getRemark(), "0", "0", dateFormat.format(new Timestamp(System.currentTimeMillis()))));

                    //数据插入redis
                    String professorKey = disinfectFilesModel.getFactoryNum() + "_disinfectFiles_professor";
                    String supervisorKey = disinfectFilesModel.getFactoryNum() + "_disinfectFiles_supervisor";
                    String testSendProfessor = disinfectFilesModel.getFactoryNum() + "_disinfectFiles_professor_AlreadySend";
                    String testSendSupervisor = disinfectFilesModel.getFactoryNum() + "_disinfectFiles_supervisor_AlreadySend";

                    String professorWorkInRedis = disinfectFilesModel.getId().toString()+ "_disinfectFiles_professor_worked";
                    String supervisorWorkInRedis = disinfectFilesModel.getId().toString()+ "_disinfectFiles_supervisor_worked";

                    JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                    JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                    JedisUtil.setCertainKeyValue(professorWorkInRedis,"0");
                    JedisUtil.setCertainKeyValue(supervisorWorkInRedis,"0");

                    //System.out.println("Have sent:"+jedisUtil.getCertainKeyValue(testSendProfessor));

                    //发送短信后 testSendProfessor存放在redis中 过期时间为ExpireTime
                    if(!("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))){
                        //System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
                        if(JedisUtil.redisJudgeTime(professorKey)){

                            System.out.println("in redis:");
                            List<UserModel> userModels = userService.getUserTelephoneByfactoryNum(disinfectFilesModel.getFactoryNum());

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
                        } }else {
                            System.out.println("professor:3天内已发送");
                        }

                        if(!("1".equals(JedisUtil.getCertainKeyValue(testSendSupervisor)))){
                            if(JedisUtil.redisJudgeTime(supervisorKey)){
                                List<UserModel> userModels = userService.getUserTelephoneByfactoryNum(disinfectFilesModel.getFactoryNum());

                                StringBuffer phoneList = new StringBuffer("");
                                for(int i = 0; i < userModels.size(); i++){
                                    phoneList = phoneList.append(userModels.get(i).getTelephone()).append(",");
                                }
                                if(JedisUtil.redisSendMessage(phoneList.toString(),JedisUtil.getCertainKeyValue("Message"))){
                                    //System.out.println("发送成功！");
                                    JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor,"1",Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime"))*24*60*60);

                                    return JudgeUtil.JudgeSuccess("successMessage","Message Sent");

                                }
                            }
                        }else {
                            System.out.println("supervisor:3天内已发送");

                            return JudgeUtil.JudgeSuccess("successMessage","have sent message in 3 days");

                        }
                    return JudgeUtil.JudgeSuccess("successMessage","unnecessary send");

                    //jedisUtil.redisSaveProfessorSupervisorWorks(professorKey,factoryNum);
                    //jedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey,factoryNum);
                }catch (Exception e){

                    e.printStackTrace();
                }

            }else {
                return Responses.errorResponse("Already Exist");
            }
        }
        return Responses.errorResponse("IOException");

    }




    /**
     * 返回查询结果
     * 以json格式返回前端
     * 分页查询
     * METHOD:POST
     * @param disinfectFilesModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response FindShow(@RequestBody DisinfectFilesModel disinfectFilesModel){
        if(disinfectFilesModel.getSize() == 0){
            disinfectFilesModel.setSize(10);
        }

        List<DisinfectFilesModel> disinfectFilesModels = disinfectFilesService.getDisinfectFilesModel(disinfectFilesModel,
                new RowBounds(disinfectFilesModel.getPage(),disinfectFilesModel.getSize()));

        return JudgeUtil.JudgeFind(disinfectFilesModels,disinfectFilesModels.size());
    }

    //更新接口


    //权限仅为专家和监督员
    /**
     * 专家入口 查看isPass1 = 0或者isPass1 = 1的数据
     * METHOD:GET
     * @param isPass1
     * @param page
     * @param size
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/pfind",method = RequestMethod.GET)
    public Response ProfessorFind(@RequestParam("isPass1") Integer isPass1,
                                  @RequestParam(value = "page",defaultValue = "0") int page,
                                  @RequestParam(value = "size",defaultValue = "10") int size){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesService.getDisinfectFilesModelByProfessor(isPass1,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(disinfectFilesModels,disinfectFilesModels.size());
    }


    /**
     * 审核入口 审核isPass1 = 0的数据
     * METHOD:PATCH
     * @param disinfectFilesModel
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/pupdate",method = RequestMethod.PATCH)
    public Response ProfessorUpdate(@RequestBody DisinfectFilesModel disinfectFilesModel) {

        if (disinfectFilesModel.getId() == null ||
                disinfectFilesModel.getProfessor() == null ||
                disinfectFilesModel.getIsPass1() == null ||
                disinfectFilesModel.getUnpassReason1() == null) {
            return Responses.errorResponse("Lack Item");
        } else {

            DisinfectFilesModel disinfectFilesModel1 = disinfectFilesService.getDisinfectFilesModelByid(disinfectFilesModel.getId());
            String professorWorkInRedis = disinfectFilesModel1.getId().toString() + "_disinfectFiles_professor_worked";

            if (disinfectFilesModel1.getIsPass1().equals("1") ) {

                return Responses.errorResponse("Already update");
            }else {

                if ("1".equals(JedisUtil.getCertainKeyValue(professorWorkInRedis))) {

                    //生成更新当前时间
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    disinfectFilesModel.setGmtProfessor(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));

                    int row = this.disinfectFilesService.updateDisinfectFilesModelByProfessor(disinfectFilesModel);

                    return JudgeUtil.JudgeUpdate(row);
                }else{

                    int row = this.disinfectFilesService.updateDisinfectFilesModelByProfessor(disinfectFilesModel);

                    if (row == 0) {
                        return JudgeUtil.JudgeUpdate(row);
                    } else {

                        //删除成功 redis数据库种对应数据-1
                        String professorKey = disinfectFilesModel1.getFactoryNum().toString() + "_disinfectFiles_professor";


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



    @ResponseBody
    @RequestMapping(value = "/sfind",method = RequestMethod.GET)
    public Response SupervisorFind(@RequestParam("isPass2") Integer isPass2,
                                   @RequestParam(value = "page",defaultValue = "0") int page,
                                   @RequestParam(value = "size",defaultValue = "10") int size){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesService.getDisinfectFilesModelBySupervisor(isPass2,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(disinfectFilesModels,disinfectFilesModels.size());
    }



    @ResponseBody
    @RequestMapping(value = "/supdate",method = RequestMethod.PATCH)
    public Response SupervisorUpdate(@RequestBody DisinfectFilesModel disinfectFilesModel){

        if (disinfectFilesModel.getId() == null ||
                disinfectFilesModel.getSupervisor() == null ||
                disinfectFilesModel.getIsPass2() == null ||
                disinfectFilesModel.getUnpassReason2() == null) {
            return Responses.errorResponse("Lack Item");
        } else {

            DisinfectFilesModel disinfectFilesModel1 = disinfectFilesService.getDisinfectFilesModelByid(disinfectFilesModel.getId());
            String supervisorWorkInRedis = disinfectFilesModel1.getId().toString() + "_disinfectFiles_supervisor_worked";


            if (disinfectFilesModel1.getIsPass2().equals("1") ) {

                return Responses.errorResponse("Already update");
            }else {
                //生成更新当前时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                disinfectFilesModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));

                if ("1".equals(JedisUtil.getCertainKeyValue(supervisorWorkInRedis))) {

                    disinfectFilesModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
                    int row = this.disinfectFilesService.updateDisinfectFilesModelBySupervisor(disinfectFilesModel);
                    return JudgeUtil.JudgeUpdate(row);

                }else {

                    disinfectFilesModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
                    int row = this.disinfectFilesService.updateDisinfectFilesModelBySupervisor(disinfectFilesModel);


                    if (row == 0) {

                        return JudgeUtil.JudgeUpdate(row);

                    } else {

                        //删除成功 redis数据库种对应数据-1
                        String supervisorKey = disinfectFilesModel1.getFactoryNum().toString() + "_disinfectFiles_supervisor";
                        disinfectFilesModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));

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
     * @param disinfectFilesModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "oupdate",method = RequestMethod.PATCH)
    public Response OperatorUpdate(@RequestBody DisinfectFilesModel disinfectFilesModel) {

        if (disinfectFilesModel.getId() == null) {
            return Responses.errorResponse("Operate wrong");
        } else {
            DisinfectFilesModel disinfectFilesModel1 = this.disinfectFilesService.getDisinfectFilesModelByid(disinfectFilesModel.getId());

            String professorWorkInRedis = disinfectFilesModel1.getId().toString()+ "_immunePlan_professor_worked";
            String supervisorWorkInRedis = disinfectFilesModel1.getId().toString()+ "_immunePlan_supervisor_worked";

            //即专家未审核 操作员需要修改
            if("0".equals(JedisUtil.getCertainKeyValue(professorWorkInRedis))){
                //无需修改 在下个语句修改
                System.out.println("running");

            }else {

                //专家已审核 退回后的数据
                String professorKey = disinfectFilesModel1.getFactoryNum().toString()+ "_disinfectFiles_professor";

                //置0 专家可操作
                JedisUtil.setCertainKeyValue(professorWorkInRedis,"0");

                //redis数据库中对应字段+1
                JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
            }

            //监督员未审核 操作员需要修改
            if ("0".equals(JedisUtil.getCertainKeyValue(supervisorWorkInRedis))){
                //System.out.println("No redis");

                int row = this.disinfectFilesService.updateDisinfectFilesModelByOperator(disinfectFilesModel);
                return JudgeUtil.JudgeUpdate(row);

            } else {

                //专家已审核 退回后的数据
                int row = this.disinfectFilesService.updateDisinfectFilesModelByOperator(disinfectFilesModel);
                String supervisorKey = disinfectFilesModel1.getFactoryNum().toString()+ "_disinfectFiles_supervisor";

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
    public Response Delete(@RequestParam("id") Long id){
        int row = this.disinfectFilesService.deleteDisinfectFilesModelByid(id);

        return JudgeUtil.JudgeDelete(row);
    }

}
