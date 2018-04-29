package com.deep.api.resource;


import com.deep.api.request.RepellentRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.api.response.ValidResponse;
import com.deep.domain.model.RepellentPlanModel;
import com.deep.domain.service.RepellentPlanService;
import com.deep.domain.service.UserService;
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
import java.io.File;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/rp")
public class RepellentPlanResource {

    private Logger logger = LoggerFactory.getLogger(RepellentPlanResource.class);

    private final String pathPre = "../EartagDocument/";

    @Resource
    private RepellentPlanService repellentPlanService;
    @Resource
    private UserService userService;

    /**
     * 返回插入结果
     * 成功：success
     * 失败：返回对应失败错误
     * METHOD:POST
     * @param repellentPlanModel 驱虫类
     * @param bindingResult  异常抛出类
     * @param repellentEartagFile   耳牌文件
     * @return  保存结果
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Response save(@Valid RepellentPlanModel repellentPlanModel,
                         BindingResult bindingResult,
                         @RequestParam("repellentEartagFile") MultipartFile repellentEartagFile) {

        if (bindingResult.hasErrors()) {
          Response response = Responses.errorResponse("param is invalid");
          Map<String, Object> data = new HashMap<String, Object>();
          data.put("error",bindingResult.getAllErrors());
          response.setData(data);
          return response;
        }

        logger.info("invoke save {}",repellentPlanModel);

                //上传文件
                try {

                    String fileName = repellentEartagFile.getOriginalFilename();
                    String filePath = new StringBuilder(pathPre)
                        .append(repellentPlanModel.getFactoryNum().toString())
                        .append("/disinfectEartag/").toString();
                    fileName = UploadUtil.uploadFile(repellentEartagFile.getBytes(),filePath,fileName);
                    repellentPlanModel.setRepellentEartag(fileName);
                    int issuccess = repellentPlanService.setRepellentPlanModel(repellentPlanModel);

                    if (issuccess == 0) {
                      return Responses.errorResponse("add error");
                    }

                    String professorKey = repellentPlanModel.getFactoryNum().toString() + "_repellentPlan_professor";
                    String supervisorKey = repellentPlanModel.getFactoryNum().toString() + "_repellentPlan_supervisor";
                    String testSendProfessor = repellentPlanModel.getFactoryNum().toString() + "_repellentPlan_professor_AlreadySend";
                    String testSendSupervisor = repellentPlanModel.getFactoryNum().toString() + "_repellentPlan_supervisor_AlreadySend";


                    JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                    JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                    //若redis中 若干天未发送短信
                    //若未完成超过50条
                    if (!("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))) {
                        System.out.println("testSendProfessorValue:" + JedisUtil.getCertainKeyValue(testSendProfessor));
                        if (JedisUtil.redisJudgeTime(professorKey)) {
                            System.out.println(professorKey);



                            List<String> phone = userService.getProfessorTelephoneByFactoryNum(repellentPlanModel.getFactoryNum());



                            //需完成:userModels.getTelephone()赋值给String
                            //获得StringBuffer手机号
                            StringBuffer phoneList = new StringBuffer("");


                            for (String aPhone : phone) {
                                phoneList = phoneList.append(aPhone).append(",");
                            }

                            if (phoneList.length() != 0) {
                              //发送成功 更新redis中字段
                              if (JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {
                                JedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
                              }
                            }

                        }
                    }

                    if (!("1".equals(JedisUtil.getCertainKeyValue(testSendSupervisor)))) {
                        if (JedisUtil.redisJudgeTime(supervisorKey)) {




                            List<String> phone = userService.getProfessorTelephoneByFactoryNum(repellentPlanModel.getFactoryNum());



                            System.out.println(JedisUtil.redisJudgeTime(supervisorKey));


                            StringBuffer phoneList = new StringBuffer("");

                            for (String aPhone : phone) {
                                phoneList = phoneList.append(aPhone).append(",");
                            }

                            if (phoneList.length() != 0) {
                              if (JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {

                                JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
                                System.out.println("发送成功！");

                                return JudgeUtil.JudgeSuccess("successMessage", "Message Sent");

                              }
                            }
                        }
                    }

                    return JudgeUtil.JudgeSuccess("success", "1");

                } catch (Exception e) {
                    e.printStackTrace();
                   return Responses.errorResponse("Exception");
                }


    }


    /**
     * 返回查询结果
     * 以json格式返回前端
     * METHOD:POST
     * @param repellentRequest 驱虫请求类
     * @return  查询结果
     */
    @RequestMapping(value = "/findshow",method = RequestMethod.POST)
    public Response findShow(@RequestBody RepellentRequest repellentRequest) {
        logger.info("invoke finsShow {}", repellentRequest);
        if (repellentRequest.getSize() == 0) {
            repellentRequest.setSize(10);
        }

        List<RepellentPlanModel> repellentPlanModels = repellentPlanService.getRepellentPlanModel(repellentRequest,
                new RowBounds(repellentRequest.getPage() * repellentRequest.getSize() ,repellentRequest.getSize()));

        return JudgeUtil.JudgeFind(repellentPlanModels,repellentPlanModels.size());

    }

    /**
     * 下载文件 并保存到自定义路径
     * @param response HttpServletResponse
     * @throws Exception 下载文件异常
     */
    @RequestMapping(value = "/down",method = RequestMethod.GET)
    public Response download(HttpServletResponse response,
                             @Param("file") String file,
                             @Param("locate") String locate)throws Exception{
        logger.info("invoke download {}", response, file, locate);
        String filePath = "../EartagDocument/repelentEartag/";
        if (DownloadUtil.downloadFile(response , file, filePath, locate)){
            return JudgeUtil.JudgeSuccess("download","Success");
        }else {
            return Responses.errorResponse("download Error");
        }
    }



    /**
     * 专家入口 查看isPass = 0或者isPass = 1的数据
     * METHOD:GET
     * @param isPass 审核标志位
     * @param page  页号
     * @param size  条数
     * @return 查询结果/查询结果条数
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
     * 审核入口 审核isPass = 0的数据
     * METHOD:PATCH
     * @param repellentPlanModel 驱虫类
     * @return 更新结果
     */
    @RequestMapping(value = "pupdate",method = RequestMethod.PATCH)
    public Response professorUpdate(@RequestBody RepellentPlanModel repellentPlanModel) {

        logger.info("invoke pupdate {}", repellentPlanModel);

        if (repellentPlanModel.getId() == null ||
                repellentPlanModel.getProfessor() == null ||
                repellentPlanModel.getIspassCheck() == null ||
                repellentPlanModel.getUnpassReason() == null) {
            return Responses.errorResponse("Lack param");
        } else {
          int row = repellentPlanService.updateRepellentPlanModelByProfessor(repellentPlanModel);
          if (row == 1) {
            String professorKey = repellentPlanModel.getFactoryNum().toString() + "_repellentPlan_professor";
            JedisUtil.redisCancelProfessorSupervisorWorks(professorKey);
          }
          return JudgeUtil.JudgeUpdate(row);
        }
    }



    /**
     * 审核入口 展示所有isPass1 = 0或者isPass1 = 1的数据
     * @param isPass1 审核标志位
     * @param page   页码
     * @param size   条数
     * METHOD:GET
     * @return 查询结果
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
     * 监督员入口 审核isPass1 = 0的数据
     * 审核要求:审核时要求条例写完整 审核后 isPass = 1时 无权限再修改
     * @param repellentPlanModel 驱虫类
     * METHOD:PATCH
     * @return 审核结果
     */
    @RequestMapping(value = "supdate",method = RequestMethod.PATCH)
    public Response SupervisorUpdate(@RequestBody RepellentPlanModel repellentPlanModel) {
        logger.info("invoke supervisorUpdate {}", repellentPlanModel);

        if( repellentPlanModel.getId() == null||
                repellentPlanModel.getSupervisor() == null||
                repellentPlanModel.getIspassSup()== null) {
            return Responses.errorResponse("Lack Item");
        } else {
          int row = repellentPlanService.updateRepellentPlanModelBySupervisor(repellentPlanModel);
          if (row == 1) {
            String supervisorKey = repellentPlanModel.getFactoryNum().toString() + "_repellentPlan_supervisor";
            JedisUtil.redisCancelProfessorSupervisorWorks(supervisorKey);
          }
          return JudgeUtil.JudgeUpdate(row);
        }
    }



    /**
     * 操作员在审核前想修改数据的接口
     * 或处理被退回操作的接口
     *
     * 行为1 与redis数据库无关
     * 行为2 redis对应数据字段+1
     * @param repellentPlanModel 驱虫类
     * @return  更新结果
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Response operatorUpdate(RepellentPlanModel repellentPlanModel,
                                   BindingResult bindingResult,
                                   @RequestParam(value = "repellentEartag", required = false) MultipartFile repellentEartag) {

        logger.info("invoke operatorUpdate {}", repellentPlanModel);
        if (repellentPlanModel.getId() == null) {
            return Responses.errorResponse("Operate wrong");

        } else {

          if (repellentEartag != null) {
            String filePath = pathPre + repellentPlanModel.getFactoryNum().toString() + "/disinfectEartag/";
            String fileName = repellentEartag.getOriginalFilename();
            try {
              fileName = UploadUtil.uploadFile(repellentEartag.getBytes(),filePath,fileName);
            } catch (Exception e) {

              return Responses.errorResponse("update file error");

            }

            String oldPath = filePath + repellentPlanModel.getRepellentEartag();
            repellentPlanModel.setRepellentEartag(fileName);
            int row = this.repellentPlanService.updateRepellentPlanModelByOperator(repellentPlanModel);
            if (row == 1) {
              File file = new File(oldPath);
              file.delete();
            } else {
              String newPath = filePath + fileName;
              File file = new File(newPath);
              file.delete();
            }
            return JudgeUtil.JudgeUpdate(row);
          } else {
            int row = this.repellentPlanService.updateRepellentPlanModelByOperator(repellentPlanModel);
            return JudgeUtil.JudgeUpdate(row);
          }
        }
    }


    /**
     * 删除id = certain 的数据
     * 权限设置
     * @param id id
     * @return  删除结果
     */

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Response delete(@PathVariable(value = "id") Long id) {
        int row = repellentPlanService.deleteRepellentPlanModelByid(id);
        return JudgeUtil.JudgeDelete(row);
    }
}
