package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.ImmunePlanModel;
import com.deep.domain.model.UseriotModel;
import com.deep.domain.service.ImmunePlanService;
import com.deep.domain.service.UseriotService;
import com.deep.domain.util.FileUtil;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;
import com.deep.domain.util.UploadUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "/ip")
public class ImmunePlanResource {

    private Logger logger = LoggerFactory.getLogger(ImmunePlanResource.class);

    @Resource
    private ImmunePlanService immunePlanService;

    //用于查询专家/监督员电话并抉择发送短信
    //@Resource
    //private UseriotService useriotService;


    /**
     * 返回插入结果
     * 成功：success
     * 失败：返回对应失败错误
     * 插入时mysql 在Redis插入数据 用于提醒专家/监督员完成审核任务
     * Key:"factory_num+模块名+专家/监督员"
     * Value:未审核条数
     *
     * METHOD:POST

     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveshow", method = RequestMethod.POST)
    public Response SaveShow(@Valid ImmunePlanModel immunePlanModel,
                             @RequestParam("immuneEartagFile") MultipartFile immuneEartagFile,
                             HttpServletRequest request)  {

        logger.info("invoke saveShow {}",immuneEartagFile,immunePlanModel,request);

        if (immunePlanModel.getFactoryNum() == null ||
                immunePlanModel.getCrowdNum() == null ||
                immunePlanModel.getImmuneTime() == null ||
                immunePlanModel.getImmuneKind() == null ||
                immunePlanModel.getImmuneWay() == null ||
                immunePlanModel.getImmuneQuality() == null ||
                immunePlanModel.getImmuneDuring() == null ||
                immunePlanModel.getOperator() == null ||
                immunePlanModel.getRemark() == null ||
                immuneEartagFile.isEmpty()) {
            return Responses.errorResponse("Lack Item");
        } else {

            String Header = FileUtil.getFileHeader(immuneEartagFile);

            /*if ( !"75736167".equals(Header) && !"7B5C727466".equals(Header) &&
                    !"D0CF11E0".equals(Header) && !"504B0304".equals(Header)) {
                return Responses.errorResponse("Wrong file form");
            }*/

            ImmunePlanModel immunePlanModel1 = immunePlanService.getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(immunePlanModel.getFactoryNum(), immunePlanModel.getCrowdNum(), immunePlanModel.getImmuneTime());
            //文件上传并将数据保存到数据库中
            //System.out.println("save before");

            //String filePath = request.getSession().getServletContext().getContextPath()+"../EartagDocument/immuneEartag/";
            //System.out.println("filepath"+filepath);


                if (immunePlanModel1 == null) {
                    //String filepath = request.getSession().getServletContext().getContextPath() + "../file/";

                    try{

                        String fileName = "";
                        String filePath = request.getSession().getServletContext().getContextPath()+"../EartagDocument/immuneEartag/";
                        String fileAddress = "";
                        fileAddress = UploadUtil.uploadFile(immuneEartagFile.getBytes(),filePath,fileName,fileAddress);

                        //System.out.println("Address"+fileAddress);
                        //数据插入数据库
                        //System.out.println("mysql执行前");

                        //设置插入数据
                        //耳牌地址
                        //ispass 默认 0
                        //插入时间
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        immunePlanModel.setImmuneEartag(fileAddress);
                        immunePlanModel.setIsPass("0");
                        immunePlanModel.setIsPass1("0");
                        immunePlanModel.setGmtCreate(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));

                        immunePlanService.setImmunePlanModel(immunePlanModel);

                        //System.out.println("id:"+immunePlanModel.getId());
                        //return new Response().addData("Obiject",immunePlanModel);
                        //System.out.println("running after insert");


                        //return new Response().addData("Success",immunePlanModel);
                        //数据插入redis
                        String professorKey = immunePlanModel.getFactoryNum().toString() + "_immunePlan_professor";
                        String supervisorKey = immunePlanModel.getFactoryNum().toString() + "_immunePlan_supervisor";
                        String testSendProfessor = immunePlanModel.getFactoryNum().toString() + "_immunePlan_professor_AlreadySend";
                        String testSendSupervisor = immunePlanModel.getFactoryNum().toString() + "_immunePlan_supervisor_AlreadySend";
                        String professorWorkInRedis = immunePlanModel.getId().toString() + "_immunePlan_professor_worked";
                        String supervisorWorkInRedis = immunePlanModel.getId().toString() + "_immunePlan_supervisor_worked";

                        JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                        JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                        JedisUtil.setCertainKeyValue(professorWorkInRedis, "0");
                        JedisUtil.setCertainKeyValue(supervisorWorkInRedis, "0");


                        //若redis中 若干天未发送短信
                        //若未完成超过50条
                        if (!("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))) {
                            System.out.println("testSendProfessorValue:" + JedisUtil.getCertainKeyValue(testSendProfessor));
                            if (JedisUtil.redisJudgeTime(professorKey)) {

//                                List<UseriotModel> userModels = useriotService.getUserTelephoneByfactoryNum(immunePlanModel.getFactoryNum());
//
//                                //需完成:userModels.getTelephone()赋值给String
//                                //获得StringBuffer手机号
//                                StringBuffer phoneList = new StringBuffer("");
//                                for (int i = 0; i < userModels.size(); i++) {
//                                    phoneList = phoneList.append(userModels.get(i).getTelephone()).append(",");
//                                }

//                                //发送成功 更新redis中字段
//                                if (JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {
//                                    JedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
//                                }

                                //System.out.println(phoneList);
                            }
                        } else {
                            System.out.println("professor:3天内已发送");
                        }

                        if (!("1".equals(JedisUtil.getCertainKeyValue(testSendSupervisor)))) {
                            if (JedisUtil.redisJudgeTime(supervisorKey)) {
//                                List<UseriotModel> userModels = useriotService.getUserTelephoneByfactoryNum(immunePlanModel.getFactoryNum());

                                StringBuffer phoneList = new StringBuffer("");
//
//                                for (int i = 0; i < userModels.size(); i++) {
//                                    phoneList = phoneList.append(userModels.get(i).getTelephone()).append(",");
//                                }
                                if (JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {    System.out.println("发送成功！");
                                    JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);


                                    return JudgeUtil.JudgeSuccess("successMessage", "Message Sent");

                                }
                            }
                        } else {
                            System.out.println("supervisor:3天内已发送");

                            return JudgeUtil.JudgeSuccess("successMessage", "have sent message in days");

                        }

                        return JudgeUtil.JudgeSuccess("successMessage", "unnecessary send");

                        //jedisUtil.redisSaveProfessorSupervisorWorks(professorKey,factoryNum);
                        //jedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey,factoryNum);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else {
                    return Responses.errorResponse("Already Exist");
                }
            }
        return Responses.errorResponse("Exception");
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

        logger.info("invoke findShow {}", immunePlanModel);

        if (immunePlanModel.getSize() == 0){
            immunePlanModel.setSize(10);
        }
        //前台传参数
        List<ImmunePlanModel> immunePlanModels = immunePlanService.getImmunePlanModel(immunePlanModel,
                new RowBounds(immunePlanModel.getPage(),immunePlanModel.getSize()));

        return JudgeUtil.JudgeFind(immunePlanModels,immunePlanModels.size());
    }


    /**
     * 专家入口 展示所有isPass1 = 0或者isPass1 = 1的数据
     * @param isPass
     * @param page
     * @param size
     * METHOD:GET
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pfind",method = RequestMethod.GET)
    public Response ProfessorFind(@RequestParam(value = "isPass",defaultValue = "2") Integer isPass,
                                  @RequestParam(value = "page",defaultValue = "0") int page,
                                  @RequestParam(value = "size",defaultValue = "10") int size){
        logger.info("invoke professorFind {}", isPass, page, size);

        if ("2".equals(isPass.toString())){
            return Responses.errorResponse("Wrong Pass num");
        }
        List<ImmunePlanModel> immunePlanModels = immunePlanService.getImmunePlanModelByProfessor(isPass,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(immunePlanModels,immunePlanModels.size());
    }


    /**
     * 专家入口 审核isPass1 = 0或者isPass1 = 1的数据
     * @param immunePlanModel 通过id修改
     * METHOD:PATCH
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "pupdate",method = RequestMethod.PATCH)
    public Response ProfessorUpdate(@RequestBody ImmunePlanModel immunePlanModel){

        logger.info("invoke professorUpdate {}", immunePlanModel);
        if(immunePlanModel.getId() == null||
                immunePlanModel.getProfessor() == null||
                immunePlanModel.getIsPass() == null||
                immunePlanModel.getUnpassReason() == null){
            return Responses.errorResponse("Lack Item");
        }else {
            ImmunePlanModel immunePlanModel1 = immunePlanService.getImmunePlanModelByid(immunePlanModel.getId());

            String professorWorkInRedis = immunePlanModel1.getId().toString() + "_immunePlan_professor_worked";

            if (immunePlanModel1.getIsPass().equals("1")){

                return Responses.errorResponse("Already update");


                //此时flag=*1 表示专家已经修改过一次 此次为第n次修改
                //isPass1=0 审核未通过
            }else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if ("1".equals(JedisUtil.getCertainKeyValue(professorWorkInRedis))){

                    //System.out.println("professor No redis");
                    immunePlanModel.setGmtProfessor(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
                    int row = immunePlanService.updateImmunePlanModelByProfessor(immunePlanModel);
                    return JudgeUtil.JudgeUpdate(row);

                    //此时为操作员提交后第一次修改 professorWorkInRedis=0
                }else {

                    //标志专家已操作
                    immunePlanModel.setGmtProfessor(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
                    int row = immunePlanService.updateImmunePlanModelByProfessor(immunePlanModel);

                    if( row == 0){
                        return JudgeUtil.JudgeUpdate(row);
                    }else {
                        //更新成功 redis数据库种对应数据-1
                        JedisUtil.setCertainKeyValue(professorWorkInRedis,"1");

                        String professorKey = immunePlanModel1.getFactoryNum().toString() + "_immunePlan_professor";


                        //System.out.println("before professor professorKey:" + professorKey);
                        //System.out.println("before professor professorKey value:" + JedisUtil.getCertainKeyValue(professorKey));

                        if (JedisUtil.redisCancelProfessorSupervisorWorks(professorKey)) {
                            //System.out.println("after:"+JedisUtil.getCertainKeyValue(supervisorKey));
                            //System.out.println("after professor professorKey:" + professorKey);
                            //System.out.println("after professor professorKey value:" + JedisUtil.getCertainKeyValue(professorKey));

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
     * 审核入口 展示所有isPass1 = 0或者isPass1 = 1的数据
     * @param isPass1
     * @param page
     * @param size
     * METHOD:GET
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sfind",method = RequestMethod.GET)
    public Response SupervisorFind(@RequestParam(value = "isPass1",defaultValue = "2") Integer isPass1,
                                   @RequestParam(value = "page",defaultValue = "0") int page,
                                   @RequestParam(value = "size",defaultValue = "10") int size){

        logger.info("invoke supervisorFind {}", isPass1, page, size);
        if ("2".equals(isPass1.toString())){
            return Responses.errorResponse("Wrong Pass num");
        }
        List<ImmunePlanModel> immunePlanModels = immunePlanService.getImmunePlanModelBySupervisor(isPass1,new RowBounds(page,size));

        System.out.println("size:"+immunePlanModels.size());
        return JudgeUtil.JudgeFind(immunePlanModels,immunePlanModels.size());

    }

    /**
     * 监督员入口 审核isPass1 = 0的数据
     * 审核要求:审核时要求条例写完整 审核后无权限再修改
     * @param immunePlanModel
     * METHOD:PATCH
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "supdate",method = RequestMethod.PATCH)
    public Response SupervisorUpdate(@RequestBody ImmunePlanModel immunePlanModel){

        logger.info("invoke supervisorUpdate {}", immunePlanModel);
        if(immunePlanModel.getId() == null||
                immunePlanModel.getSupervisor() == null||
                immunePlanModel.getIsPass1() == null){
            return Responses.errorResponse("Lack Item");

        }else {

            ImmunePlanModel immunePlanModel1 = immunePlanService.getImmunePlanModelByid(immunePlanModel.getId());

            String supervisorWorkInRedis = immunePlanModel1.getId().toString() + "_immunePlan_supervisor_worked";

            //System.out.println("direct in DB"+immunePlanModel1.getFlag());
            if (immunePlanModel1.getIsPass1().equals("1")){

                return Responses.errorResponse("Already update");

                //此时flag=1*
            }else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if ("1".equals(JedisUtil.getCertainKeyValue(supervisorWorkInRedis))){
                    //在操作员再次提交前 在监督员修改一次后 监督员需要再修改
                    //此时修改不需要进入redis
                    System.out.println("supervisor No redis");
                    immunePlanModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
                    int row = immunePlanService.updateImmunePlanModelBySupervisor(immunePlanModel);
                    return JudgeUtil.JudgeUpdate(row);

                } else {

                    //此时是操作员提交后 监督员第一次审核
                    //操作需要和redis关联
                    immunePlanModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
                    //int i = immunePlanModel1.getFlag();

                    //System.out.println("i="+i);
                    //flag = 1* 即+2 标识监督员已审核
                    //System.out.println("immunetime:"+immunePlanModel.getGmtProfessor());

                    int row = immunePlanService.updateImmunePlanModelBySupervisor(immunePlanModel);

                    if( row == 0){
                        return JudgeUtil.JudgeUpdate(row);
                    }else {
                        //更新成功 redis数据库种对应数据-1

                        String supervisorKey = immunePlanModel1.getFactoryNum().toString() + "_immunePlan_supervisor";

                        JedisUtil.setCertainKeyValue(supervisorWorkInRedis,"1");

                        //System.out.println("rediskey:"+JedisUtil.getCertainKeyValue(supervisorWorkInRedis));
                        //System.out.println("before supervisor supervisorKey:"+supervisorKey);
                        //System.out.println("before supervisor supervisorKey value:"+JedisUtil.getCertainKeyValue(supervisorKey));

                        if (JedisUtil.redisCancelProfessorSupervisorWorks(supervisorKey)){
                            //System.out.println("after:"+JedisUtil.getCertainKeyValue(supervisorKey));
                            //System.out.println("after supervisor supervisorKey:"+supervisorKey);
                            //System.out.println("after supervisor supervisorKey value:"+JedisUtil.getCertainKeyValue(supervisorKey));

                            return JudgeUtil.JudgeUpdate(row);

                        }else {
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
     * @param immunePlanModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "oupdate",method = RequestMethod.PATCH)
    public Response OperatorUpdate(@RequestBody ImmunePlanModel immunePlanModel) {

        logger.info("invoke operatorUpdate {}", immunePlanModel);
        if (immunePlanModel.getId() == null) {
            return Responses.errorResponse("Operate wrong");
        } else {
            ImmunePlanModel immunePlanModel1 = this.immunePlanService.getImmunePlanModelByid(immunePlanModel.getId());

            //字段标识
            String professorWorkInRedis = immunePlanModel1.getId().toString()+ "_immunePlan_professor_worked";
            String supervisorWorkInRedis = immunePlanModel1.getId().toString()+ "_immunePlan_supervisor_worked";

            //System.out.println("professorwork:"+JedisUtil.getCertainKeyValue(professorWorkInRedis));
            //System.out.println("supervisorwork:"+JedisUtil.getCertainKeyValue(supervisorWorkInRedis));

            //即专家未审核 操作员需要修改
            if("0".equals(JedisUtil.getCertainKeyValue(professorWorkInRedis))){
                //
                System.out.println("professorWork No redis");

            }else {

                String professorKey = immunePlanModel1.getFactoryNum().toString()+ "_immunePlan_professor";

                //置0 即提交专家可审核
                JedisUtil.setCertainKeyValue(professorWorkInRedis,"0");
                //System.out.println("before operator update professorKey:"+professorKey);
                //System.out.println("before operator update professorKey value:"+JedisUtil.getCertainKeyValue(professorKey));

                //redis数据库中对应字段+1
                JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                //System.out.println("before operator update professorKey:"+professorKey);
                //System.out.println("before operator update professorKey value:"+JedisUtil.getCertainKeyValue(professorKey));

            }

            //监督员未审核 操作员需要修改
            if ("0".equals(JedisUtil.getCertainKeyValue(supervisorWorkInRedis))){

                System.out.println("supervisorWork No redis");
                int row = this.immunePlanService.updateImmunePlanModelByOperator(immunePlanModel);
                return JudgeUtil.JudgeUpdate(row);

            }else {
                //专家已审核 退回后的数据
                int row = this.immunePlanService.updateImmunePlanModelByOperator(immunePlanModel);
                String supervisorKey = immunePlanModel1.getFactoryNum().toString()+ "_immunePlan_supervisor";

                JedisUtil.setCertainKeyValue(supervisorWorkInRedis,"0");
                //System.out.println("before operator update supervisorKey:"+supervisorKey);
                //System.out.println("before operator update supervisorKey value:"+JedisUtil.getCertainKeyValue(supervisorKey));
                //redis数据库中对应字段+1
                JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                //System.out.println("after operator update supervisorKey:"+supervisorKey);
                //System.out.println("after operator update supervisorKey value:"+JedisUtil.getCertainKeyValue(supervisorKey));

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
    public Response Delete(@RequestParam(value = "id",defaultValue = "0") Long id){

        logger.info("invoke delete {}", id);
        if ("0".equals(id.toString())){
            return Responses.errorResponse("Wrong id");
        }
        int row = immunePlanService.deleteImmunePlanModelByid(id);
        return JudgeUtil.JudgeDelete(row);
    }

}
