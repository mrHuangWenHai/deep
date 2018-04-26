package com.deep.api.resource;


import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.api.response.ValidResponse;
import com.deep.domain.model.ImmunePlanModel;
import com.deep.domain.service.ImmunePlanService;
import com.deep.domain.service.UserService;
import com.deep.api.request.ImmuneRequest;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;
import com.deep.domain.util.UploadUtil;
import com.deep.domain.util.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping(value = "/ip")
public class ImmunePlanResource {

    private Logger logger = LoggerFactory.getLogger(ImmunePlanResource.class);

    private final String pathPre = "../EartagDocument/";

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
     * @param immunePlanModel 免疫类
     * @param bindingResult  异常抛出类
     * @param immuneEartagFile  耳牌文件
     * @return 插入结果
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response saveShow(@Valid ImmunePlanModel immunePlanModel,
                             BindingResult bindingResult,
                             @RequestParam("immuneEartagFile") MultipartFile immuneEartagFile)  {

        if (bindingResult.hasErrors()) {
            Response response = Responses.successResponse();
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("error",bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }

        logger.info("invoke saveShow {}",immuneEartagFile,immunePlanModel);

            try {
                String fileName = immuneEartagFile.getOriginalFilename();
                String filePath = new StringBuilder(pathPre)
                    .append(immunePlanModel.getFactoryNum().toString())
                    .append("/disinfectEartag/").toString();

                fileName = UploadUtil.uploadFile(immuneEartagFile.getBytes(),filePath,fileName);

                //System.out.println("Address"+fileAddress);
                //数据插入数据库
                //System.out.println("mysql执行前");

                //设置插入数据
                //耳牌地址
                //ispass 默认 0

                immunePlanModel.setImmuneEartag(fileName);
                immunePlanService.setImmunePlanModel(immunePlanModel);

                //数据插入redis
                String professorKey = immunePlanModel.getFactoryNum().toString() + "_immunePlan_professor";
                String supervisorKey = immunePlanModel.getFactoryNum().toString() + "_immunePlan_supervisor";
                String testSendProfessor = immunePlanModel.getFactoryNum().toString() + "_immunePlan_professor_AlreadySend";
                String testSendSupervisor = immunePlanModel.getFactoryNum().toString() + "_immunePlan_supervisor_AlreadySend";

                JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                //若redis中 若干天未发送短信
                //若未完成超过50条
                if (!("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))) {
                    if (JedisUtil.redisJudgeTime(professorKey)) {
                        //需完成:userModels.getTelephone()赋值给String
                        //获得StringBuffer手机号
                        System.out.println("running here");
                        System.out.println("factoryNum"+immunePlanModel.getFactoryNum());

                        List<String> phone = this.userService.getUserTelephoneByfactoryNum(immunePlanModel.getFactoryNum());

                        StringBuffer phoneList = new StringBuffer("");
                        for (String aPhone : phone) {
                            phoneList = phoneList.append(aPhone).append(",");
                        }

                        if (phoneList.length() != 0) {
                          if (JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {
                            JedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
                          }

                        }

                    }

                }

                if (!("1".equals(JedisUtil.getCertainKeyValue(testSendSupervisor)))) {
                    if (JedisUtil.redisJudgeTime(supervisorKey)) {

                        List<String> phone = userService.getUserTelephoneByfactoryNum(immunePlanModel.getFactoryNum());

                        StringBuffer phoneList = new StringBuffer("");

                        for (String aPhone : phone) {
                            phoneList = phoneList.append(aPhone).append(",");
                        }

                        if (phoneList.length() != 0) {
                          if (JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {
                            //System.out.println("发送成功！");
                            JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);

                            return JudgeUtil.JudgeSuccess("successMessage", "Message Sent");

                          }
                        }
                    }

                }
                return Responses.successResponse();

            } catch (Exception e) {
                e.printStackTrace();
                return Responses.errorResponse("Exception");
            }
    }


    /**
     * 返回查询结果
     * 以json格式返回前端
     * 分页查询
     * METHOD:POST
     * @param immuneRequest 免疫请求类
     * @return 查询结果
     */
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response findShow(@RequestBody ImmuneRequest immuneRequest) {

        logger.info("invoke findShow {}", immuneRequest);

        if (immuneRequest.getSize() == 0) {
            immuneRequest.setSize(10);
        }
        //前台传参数
        List<ImmunePlanModel> immunePlanModels = immunePlanService.getImmunePlanModel(immuneRequest,
                new RowBounds(immuneRequest.getPage() * immuneRequest.getSize(),immuneRequest.getSize()));
        return JudgeUtil.JudgeFind(immunePlanModels,immunePlanModels.size());
    }

    /**
     * 下载文件 并保存到自定义路径
     * @param response HttpServletResponse
     * @throws Exception 下载文件异常
     */
    @RequestMapping(value = "/down",method = RequestMethod.GET)
    public Response download(HttpServletResponse response,
                             @Param("file") String file,
                             @Param("locate") String locate)throws Exception {
        logger.info("invoke download {}", response, file, locate);
        String filePath = "../EartagDocument/immuneEartag/";
        if (DownloadUtil.downloadFile(response , file, filePath, locate)) {
            return JudgeUtil.JudgeSuccess("download","Success");
        } else {
            return Responses.errorResponse("download Error");
        }
    }


//    /**
//     * 专家入口 查看isPass = 0或者isPass = 1的数据
//     * METHOD:GET
//     * @param ispassCheck 审核标志位
//     * @param page  页号
//     * @param size  条数
//     * @return 查询结果/查询结果条数
//     */
//
//    @RequestMapping(value = "pfind",method = RequestMethod.GET)
//    public Response professorFind(@RequestParam(value = "ispassCheck",defaultValue = "2") String ispassCheck,
//                                  @RequestParam(value = "page",defaultValue = "0") int page,
//                                  @RequestParam(value = "size",defaultValue = "10") int size) {
//        logger.info("invoke professorFind {}", ispassCheck, page, size);
//
//        if ("2".equals(ispassCheck)) {
//            return Responses.errorResponse("Wrong Pass num");
//        }
//        List<ImmunePlanModel> immunePlanModels = immunePlanService.getImmunePlanModelByProfessor(ispassCheck,new RowBounds(page,size));
//
//        return JudgeUtil.JudgeFind(immunePlanModels,immunePlanModels.size());
//    }


    /**
     * 审核入口 审核isPass = 0的数据
     * METHOD:PATCH
     * @param immunePlanModel 免疫类
     * @return 更新结果
     */
    @RequestMapping(value = "pupdate",method = RequestMethod.PATCH)
    public Response professorUpdate(@RequestBody ImmunePlanModel immunePlanModel) {

        logger.info("invoke professorUpdate {}", immunePlanModel);
        if(immunePlanModel.getId() == null||
                immunePlanModel.getProfessor() == null||
                immunePlanModel.getIspassCheck() == null||
                immunePlanModel.getUnpassReason() == null) {
            return Responses.errorResponse("Lack Item");
        } else {
          int row = immunePlanService.updateImmunePlanModelByProfessor(immunePlanModel);
          if (row == 1) {
            String professorKey = immunePlanModel.getFactoryNum().toString() + "_immunePlan_professor";
            JedisUtil.redisCancelProfessorSupervisorWorks(professorKey);
          }
          return JudgeUtil.JudgeUpdate(row);
        }

    }


//    /**
//     * 审核入口 展示所有isPass1 = 0或者isPass1 = 1的数据
//     * @param ispassSup 审核标志位
//     * @param page   页码
//     * @param size   条数
//     * METHOD:GET
//     * @return 查询结果
//     */
//    @RequestMapping(value = "sfind",method = RequestMethod.GET)
//    public Response supervisorFind(@RequestParam(value = "ispassSup",defaultValue = "2") String ispassSup,
//                                   @RequestParam(value = "page",defaultValue = "0") int page,
//                                   @RequestParam(value = "size",defaultValue = "10") int size) {
//
//        logger.info("invoke supervisorFind {}", ispassSup, page, size);
//        if ("2".equals(ispassSup)) {
//            return Responses.errorResponse("Wrong Pass num");
//        }
//
//        List<ImmunePlanModel> immunePlanModels = immunePlanService.getImmunePlanModelBySupervisor(ispassSup,new RowBounds(page,size));
//
//
//        return JudgeUtil.JudgeFind(immunePlanModels,immunePlanModels.size());
//
//    }

    /**
     * 监督员入口 审核isPass1 = 0的数据
     * 审核要求:审核时要求条例写完整 审核后 isPass = 1时 无权限再修改
     * @param immunePlanModel 免疫类
     * METHOD:PATCH
     * @return 审核结果
     */
    @RequestMapping(value = "supdate",method = RequestMethod.PATCH)
    public Response supervisorUpdate(@RequestBody ImmunePlanModel immunePlanModel){
        logger.info("invoke supervisorUpdate {}", immunePlanModel);
        if(immunePlanModel.getId() == null||
                immunePlanModel.getSupervisor() == null||
                immunePlanModel.getIspassSup() == null){
            return Responses.errorResponse("Lack Item");

        } else {
          int row = immunePlanService.updateImmunePlanModelBySupervisor(immunePlanModel);
          if (row == 1) {
            String supervisorKey = immunePlanModel.getFactoryNum().toString() + "_immunePlan_supervisor";
            JedisUtil.redisCancelProfessorSupervisorWorks(supervisorKey);
          }
          return JudgeUtil.JudgeUpdate(row);
        }
    }


    /**
     * 操作员在审核前想修改数据的接口
     * 或处理被退回操作的接口
     *
     * @param immunePlanModel 免疫类
     * @return 更新结果
     */


    @RequestMapping(value = "update",method = RequestMethod.PATCH)
    public Response operatorUpdate(@RequestBody ImmunePlanModel immunePlanModel) {
          logger.info("invoke operatorUpdate {}", immunePlanModel);
          int row = this.immunePlanService.updateImmunePlanModelByOperator(immunePlanModel);
          return JudgeUtil.JudgeUpdate(row);
    }

    /**
     * 删除id = certain 的数据
     * 权限设置
     * @param id id
     * @return 删除结果
     */

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Response delete(@Min(0) @PathVariable(value = "id") Long id) {

        logger.info("invoke delete {}", id);
        if ("0".equals(id.toString())) {
            return Responses.errorResponse("Wrong id");
        }

        int row = immunePlanService.deleteImmunePlanModelByid(id);
        return JudgeUtil.JudgeDelete(row);
    }

}
