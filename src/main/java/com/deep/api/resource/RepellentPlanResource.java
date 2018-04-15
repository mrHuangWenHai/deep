package com.deep.api.resource;

import com.deep.api.request.RepellentRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.api.response.ValidResponse;
import com.deep.domain.model.RepellentPlanModel;
import com.deep.domain.service.RepellentPlanService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.FileUtil;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;
import com.deep.domain.util.UploadUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


@RestController
@RequestMapping(value = "/rp")
public class RepellentPlanResource {

    private Logger logger = LoggerFactory.getLogger(RepellentPlanResource.class);
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
    @RequestMapping(value = "/saveshow",method = RequestMethod.POST)
    public Response SaveShow(@Valid RepellentPlanModel repellentPlanModel,
                             BindingResult bindingResult,
                             @RequestParam("repellentEartagFile") MultipartFile repellentEartagFile,
                             HttpServletRequest request) {

        if (bindingResult.hasErrors()){
            return ValidResponse.bindExceptionHandler();
        }

        logger.info("invoke saveShow {}", repellentEartagFile, repellentPlanModel, request);
        //判断文件头

        if (repellentEartagFile.isEmpty()) {

            return Responses.errorResponse("Lack Item");

        } else {

            String Header = FileUtil.getFileHeader(repellentEartagFile);

            /*if ( !"75736167".equals(Header) && !"7B5C727466".equals(Header) &&
                    !"D0CF11E0".equals(Header) && !"504B0304".equals(Header)) {
                return Responses.errorResponse("Wrong file form");
            }*/

            RepellentPlanModel repellentPlanModel1 = repellentPlanService.getRepellentPlanModelByfactoryNumAndcrowdNumAndrepellentTimeAndrepellentName(repellentPlanModel.getFactoryNum(),repellentPlanModel.getCrowdNum(),repellentPlanModel.getRepellentTime(),repellentPlanModel.getRepellentName());
            if (repellentPlanModel1 == null) {

                //上传文件
                try {

                    //uploadUtil.uploadFile(repellentPlanModel.getRepellentEartagFile().getBytes(), filepath, fileAddress);
                    //System.out.println("saving file");

                    String fileName = "";
                    String filePath = request.getSession().getServletContext().getContextPath() + "../EartagDocument/repellentEartag/";
                    String fileAddress = "";
                    fileAddress = UploadUtil.uploadFile(repellentEartagFile.getBytes(), filePath, fileName, fileAddress);

                    //数据插入数据库
                    //System.out.println("mysql执行前");


                    //设置插入数据
                    //耳牌地址
                    //ispass 默认 0
                    //插入时间
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

                    repellentPlanModel.setRepellentEartag(fileAddress);
                    repellentPlanModel.setIsPass("0");
                    repellentPlanModel.setIsPass1("0");
                    repellentPlanModel.setGmtCreate(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));

                    repellentPlanService.setRepellentPlanModel(repellentPlanModel);
                    //System.out.println("save before:"+ispass);

                    String professorKey = repellentPlanModel.getFactoryNum().toString() + "_repellentPlan_professor";
                    String supervisorKey = repellentPlanModel.getFactoryNum().toString() + "_repellentPlan_supervisor";
                    String testSendProfessor = repellentPlanModel.getFactoryNum().toString() + "_repellentPlan_professor_AlreadySend";
                    String testSendSupervisor = repellentPlanModel.getFactoryNum().toString() + "_repellentPlan_supervisor_AlreadySend";

                    String professorWorkInRedis = repellentPlanModel.getId().toString() + "_repellentPlan_professor_worked";
                    String supervisorWorkInRedis = repellentPlanModel.getId().toString() + "_repellentPlan_supervisor_worked";


                    JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                    JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                    System.out.println("插入后,审核前");
                    System.out.println("pk+"+professorKey+" "+"pv:"+JedisUtil.getCertainKeyValue(professorKey));
                    System.out.println("sk+"+supervisorKey+" "+"sv:"+JedisUtil.getCertainKeyValue(supervisorKey));
                    System.out.println("tpk+"+testSendProfessor+" "+"tpv:"+JedisUtil.getCertainKeyValue(testSendProfessor));
                    System.out.println("tsk+"+testSendSupervisor+" "+"tsv:"+JedisUtil.getCertainKeyValue(testSendSupervisor));
                    System.out.println("pkir+"+professorWorkInRedis+" "+"pvir:"+JedisUtil.getCertainKeyValue(professorWorkInRedis));
                    System.out.println("skir+"+supervisorWorkInRedis+" "+"svir:"+JedisUtil.getCertainKeyValue(supervisorWorkInRedis));

                    //System.out.println("testSendProfessorValue:"+jedisUtil.getCertainKeyValue(testSendProfessor));
                    //System.out.println("judge equal:"+"1".equals(jedisUtil.getCertainKeyValue(testSendProfessor)));

                    JedisUtil.setCertainKeyValue(professorWorkInRedis, "0");
                    JedisUtil.setCertainKeyValue(supervisorWorkInRedis, "0");

                    //若redis中 若干天未发送短信
                    //若未完成超过50条
                    if (!("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))) {
                        System.out.println("testSendProfessorValue:" + JedisUtil.getCertainKeyValue(testSendProfessor));
                        if (JedisUtil.redisJudgeTime(professorKey)) {
                            System.out.println(professorKey);


                            List<String> phone = userService.getUserTelephoneByfactoryNum(repellentPlanModel.getFactoryNum());


                            //需完成:userModels.getTelephone()赋值给String
                            //获得StringBuffer手机号
                            StringBuffer phoneList = new StringBuffer("");

                            for (int i = 0; i < phone.size(); i++) {
                                phoneList = phoneList.append(phone.get(i)).append(",");
                            }
                            if ("".equals(phoneList.toString())){
                                System.out.println("No phoneList");
                            }else {

                                //发送成功 更新redis中字段
                                if (JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {
                                    JedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
                                }
                            }
                            //System.out.println(phoneList);
                        }
                    } else {
                        System.out.println("professor:3天内已发送");
                    }

                    if (!("1".equals(JedisUtil.getCertainKeyValue(testSendSupervisor)))) {
                        if (JedisUtil.redisJudgeTime(supervisorKey)) {


                            List<String> phone = userService.getUserTelephoneByfactoryNum(repellentPlanModel.getFactoryNum());


                            System.out.println(JedisUtil.redisJudgeTime(supervisorKey));

                            StringBuffer phoneList = new StringBuffer("");

                            for (int i = 0; i < phone.size(); i++) {
                                phoneList = phoneList.append(phone.get(i)).append(",");
                            }
                            if ("".equals(phoneList.toString())){

                                return Responses.errorResponse("Not found telephone");

                            } else {

                                if (JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {

                                    JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
                                    System.out.println("发送成功！");

                                    return JudgeUtil.JudgeSuccess("successMessage", "Message Sent");

                                }
                            }
                        }

                    } else {
                        System.out.println("supervisor:3天内已发送");
                        return JudgeUtil.JudgeSuccess("successMessage", "have sent message in  days");

                    }

                    return JudgeUtil.JudgeSuccess("successMessage", "unnecessary send");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else {
                return  Responses.errorResponse("Already Exist");
            }
        }
        return Responses.errorResponse("Exception");
    }


    /**
     * 返回查询结果
     * 以json格式返回前端
     * METHOD:POST
     * @param repellentRequest
     * @return
     */
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response FindShow(@RequestBody RepellentRequest repellentRequest){
        //System.out.println(repellentRequest.getRepellentPlanModel().getFactoryNum());

        logger.info("invoke finsShow {}", repellentRequest);
        if(repellentRequest.getSize() == 0){
            repellentRequest.setSize(10);
        }
        //System.out.println(repellentPlanModel.getFactoryNum());


        List<RepellentPlanModel> repellentPlanModels =repellentPlanService.getRepellentPlanModel(repellentRequest,
                new RowBounds(repellentRequest.getPage(),repellentRequest.getSize()));

        return JudgeUtil.JudgeFind(repellentPlanModels,repellentPlanModels.size());

    }



    /**
     * 专家入口 展示所有isPass = 0或者isPass = 1的数据
     * @param isPass
     * @param page
     * @param size
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "pfind",method = RequestMethod.GET)
    public Response ProfessorFind(@RequestParam(value = "isPass",defaultValue = "2") Integer isPass,
                                  @RequestParam(value = "page",defaultValue = "0") int page,
                                  @RequestParam(value = "size",defaultValue = "10") int size){

        logger.info("invoke professorFind {}", isPass, page, size);
        List<RepellentPlanModel> repellentPlanModels = repellentPlanService.getRepellentPlanModelByProfessor(isPass,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(repellentPlanModels,repellentPlanModels.size());
    }


    /**
     * 专家入口 审核isPass = 0的数据
     * redis中数据-1
     * @param repellentPlanModel
     * METHOD:PATCH
     * @return
     */

    @RequestMapping(value = "pupdate",method = RequestMethod.PATCH)
    public Response ProfessorUpdate(@RequestBody RepellentPlanModel repellentPlanModel) {

        logger.info("invoke professorUpdate {}", repellentPlanModel);

        if (repellentPlanModel.getId() == null ||
                repellentPlanModel.getProfessor() == null ||
                repellentPlanModel.getIsPass() == null ||
                repellentPlanModel.getUnpassReason() == null) {
            return Responses.errorResponse("Lack Item");
        } else {
            //System.out.println("before:"+repellentPlanModel.getId());

            RepellentPlanModel repellentPlanModel1 = repellentPlanService.getRepellentPlanModelByid(repellentPlanModel.getId());

            String professorWorkInRedis = repellentPlanModel1.getId().toString() + "_repellentPlan_professor_worked";

            //return new Response().addData("",repellentPlanModel1);
            //System.out.println("isNull?"+repellentPlanModel1.getId());
            //System.out.println("in redis:"+ JedisUtil.getCertainKeyValue(professorWorkInRedis));
            if (repellentPlanModel1.getIsPass().equals("1") ) {

                return Responses.errorResponse("Already update");
            } else {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

                if ("1".equals(JedisUtil.getCertainKeyValue(professorWorkInRedis))) {

                    repellentPlanModel.setGmtProfessor(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));

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
     * @param isPass1
     * @param page
     * @param size
     * METHOD:GET
     * @return
     */
    @RequestMapping(value = "sfind",method = RequestMethod.GET)
    public Response SupervisorFind(@RequestParam(value = "isPass1",defaultValue = "2") Integer isPass1,
                                   @RequestParam(value = "page",defaultValue = "0") int page,
                                   @RequestParam(value = "size",defaultValue = "10") int size){
        logger.info("invoke supervisorFind {}", isPass1, page, size);

        List<RepellentPlanModel> repellentPlanModels = repellentPlanService.getRepellentPlanModelBySupervisor(isPass1,new RowBounds(page,size));

        return JudgeUtil.JudgeFind(repellentPlanModels,repellentPlanModels.size());
    }

    /**
     * 专家入口 审核isPass2 = 0的数据
     * @param repellentPlanModel
     * METHOD:PATCH
     * @return
     */
    @RequestMapping(value = "supdate",method = RequestMethod.PATCH)
    public Response SupervisorUpdate(@RequestBody RepellentPlanModel repellentPlanModel){

        logger.info("invoke supervisorUpdate {}", repellentPlanModel);

        if( repellentPlanModel.getId() == null||
                repellentPlanModel.getSupervisor() == null||
                repellentPlanModel.getIsPass1()== null){
            return Responses.errorResponse("Lack Item");

        }else {
            //若该id数据已被审核 直接返回
            RepellentPlanModel repellentPlanModel1 = repellentPlanService.getRepellentPlanModelByid(repellentPlanModel.getId());

            String supervisorWorkInRedis = repellentPlanModel1.getId().toString() + "_repellentPlan_supervisor_worked";


            if (repellentPlanModel1.getIsPass1().equals("1")){

                return Responses.errorResponse("Already update");
            }else {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

                if ("1".equals(JedisUtil.getCertainKeyValue(supervisorWorkInRedis))) {

                    repellentPlanModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
                    int row = repellentPlanService.updateRepellentPlanModelBySupervisor(repellentPlanModel);

                    return JudgeUtil.JudgeUpdate(row);
                }else{

                    repellentPlanModel.setGmtSupervise(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
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
    @RequestMapping(value = "oupdate",method = RequestMethod.PATCH)
    public Response OperatorUpdate(@RequestBody RepellentPlanModel repellentPlanModel) {

        logger.info("invoke operatorUpdate {}", repellentPlanModel);
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
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Response Delete(@RequestParam("id") Long id){

        logger.info("invoke delete {}", id);
        int row = repellentPlanService.deleteRepellentPlanModelByid(id);
        return JudgeUtil.JudgeDelete(row);
    }
}
