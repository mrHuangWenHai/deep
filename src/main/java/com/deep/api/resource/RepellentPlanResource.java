package com.deep.api.resource;


import com.deep.api.Utils.AgentUtil;
import com.deep.api.request.RepellentRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.RepellentPlanModel;
import com.deep.domain.service.FactoryService;
import com.deep.domain.service.RepellentPlanService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.*;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
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

    //用于查询羊厂代理id
    @Resource
    private FactoryService factoryService;

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
                    String filePath = pathPre + repellentPlanModel.getFactoryNum().toString() + "/repellentEartag/";
                    fileName = UploadUtil.uploadFile(repellentEartagFile.getBytes(),filePath,fileName);
                    repellentPlanModel.setRepellentEartag(fileName);
                    int issuccess = repellentPlanService.setRepellentPlanModel(repellentPlanModel);

                    if (issuccess == 0) {
                      return Responses.errorResponse("add error");
                    }

                    short agentID = this.factoryService.getAgentIDByFactoryNumber(repellentPlanModel.getFactoryNum().toString());
                    String professorKey = agentID + "_professor";
                    String supervisorKey = repellentPlanModel.getFactoryNum().toString() + "_supervisor";

                    String testSendProfessor = agentID + "_professor_AlreadySend";
                    String testSendSupervisor = repellentPlanModel.getFactoryNum().toString() + "_supervisor_AlreadySend";


                    JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
                    JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

                    System.out.println("插入后,审核前");
                    System.out.println("pk+"+professorKey+" "+"pv:"+JedisUtil.getCertainKeyValue(professorKey));
                    System.out.println("sk+"+supervisorKey+" "+"sv:"+JedisUtil.getCertainKeyValue(supervisorKey));
                    System.out.println("tpk+"+testSendProfessor+" "+"tpv:"+JedisUtil.getCertainKeyValue(testSendProfessor));
                    System.out.println("tsk+"+testSendSupervisor+" "+"tsv:"+JedisUtil.getCertainKeyValue(testSendSupervisor));

                    //若redis中 若干天未发送短信
                    //若未完成超过50条
                    if (!("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))) {
                        //System.out.println("testSendProfessorValue:" + JedisUtil.getCertainKeyValue(testSendProfessor));
                        if (JedisUtil.redisJudgeTime(professorKey)) {
                            //System.out.println(professorKey);

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

                            List<String> phone = userService.getSupervisorTelephoneByFactoryNum(repellentPlanModel.getFactoryNum());
                            StringBuffer phoneList = new StringBuffer("");

                            for (String aPhone : phone) {
                                phoneList = phoneList.append(aPhone).append(",");
                            }

                            if (phoneList.length() != 0) {
                              if (JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {

                                JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
                                System.out.println("发送成功！");

                              }
                            }
                        }
                    }

                    return JudgeUtil.JudgeSuccess("id", repellentPlanModel.getId());

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
     * 用于id查询
     * @param id id
     * @return 查询结果
     */
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public Response find(@PathVariable("id") long id){
        logger.info("invoke find{id} {}" , id);
        RepellentPlanModel repellentPlanModel = this.repellentPlanService.getRepellentPlanModelById(id);
        return JudgeUtil.JudgeFind(repellentPlanModel);
    }

    /**
     * 查看某专家负责的工厂
     * @param agentId 代理ID
     * @param factoryNum 工厂号
     * @param page 页号
     * @param size 页数
     * @return 查询结果
     */
    @RequestMapping(value = "/professor",method = RequestMethod.GET)
    public Response pFind(@RequestParam("agentId") Long agentId,
                          @RequestParam(value = "factoryNum",required = false)BigInteger factoryNum,
                          @RequestParam(value = "ispassCheck",required = false)String ispassCheck,
                          @RequestParam(value = "page" , defaultValue = "0") int page,
                          @RequestParam(value = "size" , defaultValue = "10") int size){
        logger.info(" invoke pFind{agentId, factoryNum, ispassCheck ,page ,size} {}" , agentId, factoryNum, ispassCheck, page ,size);
        List<RepellentPlanModel> list = new ArrayList<>();
        if (factoryNum == null){
            //未指定factoryNum 查询出负责的所有的factoryID
            long[] factoryId = AgentUtil.getFactory(agentId.toString());
            if (factoryId == null){
                return Responses.errorResponse("find no factory");
            }

            for (long factory : factoryId){
                list.addAll(this.repellentPlanService.getRepellentPlanModelByFactoryNumAndIsPassCheck(BigInteger.valueOf(factory), ispassCheck, new RowBounds(page * size ,size)));
            }
            return JudgeUtil.JudgeFind(list , list.size());
            //指定查询的factoryNum
        } else {
            list.addAll(this.repellentPlanService.getRepellentPlanModelByFactoryNumAndIsPassSup(factoryNum, ispassCheck, new RowBounds(page * size ,size)));
            return JudgeUtil.JudgeFind(list , list.size());
        }

    }

    /**
     * 查看某监督员负责的工厂
     * @param factoryNum 工厂号
     * @param ispassSup  审核
     * @param page  页
     * @param size  条
     * @return  查询结果
     */
    @RequestMapping(value = "/supervisor",method = RequestMethod.GET)
    public Response sFind(@RequestParam(value = "factoryNum")BigInteger factoryNum,
                          @RequestParam(value = "ispassSup",required = false)String ispassSup,
                          @RequestParam(value = "page" , defaultValue = "0") int page,
                          @RequestParam(value = "size" , defaultValue = "10") int size){
        logger.info(" invoke sFind{ factoryNum, ispassSup, page, size } {}" ,  factoryNum, ispassSup, page ,size);
        List<RepellentPlanModel> list = this.repellentPlanService.getRepellentPlanModelByFactoryNumAndIsPassSup(factoryNum, ispassSup, new RowBounds(page * size , size));
        return JudgeUtil.JudgeFind(list, list.size());
    }

    /**
     * 下载文件 并保存到自定义路径
     * @param response  HttpServletResponse
     * @param factoryNum  下载文件所属工厂号
     * @param file  文件名
     * @param locate  目的地址
     * @return  下载结果
     */
    @RequestMapping(value = "/down/{num}/{file}/{locate}",method = RequestMethod.GET)
    public Response download(HttpServletResponse response,
                             @PathVariable("num") String factoryNum,
                             @PathVariable("file") String file,
                             @PathVariable("locate") String locate){
        logger.info("invoke download {}", response, file, locate);
        String filePath = "../EartagDocument" +factoryNum + "/repellentEartag/";
        if (DownloadUtil.downloadFile(response , file, filePath, locate)){
            return JudgeUtil.JudgeSuccess("download","Success");
        }else {
            return Responses.errorResponse("download Error");
        }
    }


    /**
     * 审核入口 审核isPass = 0的数据
     * METHOD:PATCH
     * @param repellentPlanModel 驱虫类
     * @return 更新结果
     */
    @RequestMapping(value = "/professor/select",method = RequestMethod.PATCH)
    public Response professorUpdate(@RequestBody RepellentPlanModel repellentPlanModel) {

        logger.info("invoke pupdate {}", repellentPlanModel);

        if (repellentPlanModel.getId() == null ||
                repellentPlanModel.getFactoryNum() == null ||
                repellentPlanModel.getProfessor() == null ||
                repellentPlanModel.getIspassCheck() == null ||
                repellentPlanModel.getUnpassReason() == null) {
            return Responses.errorResponse("Lack param");
        } else {
          int row = repellentPlanService.updateRepellentPlanModelByProfessor(repellentPlanModel);
          if (row == 1) {
            String professorKey = this.factoryService.getAgentIDByFactoryNumber(repellentPlanModel.getFactoryNum().toString()) + "_professor";
            JedisUtil.redisCancelProfessorSupervisorWorks(professorKey);
          }
          return JudgeUtil.JudgeUpdate(row);
        }
    }



    /**
     * 监督员入口 审核isPass1 = 0的数据
     * 审核要求:审核时要求条例写完整 审核后 isPass = 1时 无权限再修改
     * @param repellentPlanModel 驱虫类
     * METHOD:PATCH
     * @return 审核结果
     */


    @RequestMapping(value = "/supervisor/select",method = RequestMethod.PATCH)
    public Response SupervisorUpdate(@RequestBody RepellentPlanModel repellentPlanModel) {
        logger.info("invoke supervisorUpdate {}", repellentPlanModel);

        if( repellentPlanModel.getId() == null ||
                repellentPlanModel.getFactoryNum() == null ||
                repellentPlanModel.getSupervisor() == null ||
                repellentPlanModel.getIspassSup() == null) {
            return Responses.errorResponse("Lack Item");
        } else {
          int row = repellentPlanService.updateRepellentPlanModelBySupervisor(repellentPlanModel);
          if (row == 1) {
            String supervisorKey = repellentPlanModel.getFactoryNum().toString() + "_supervisor";
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
                                   @RequestParam(value = "repellentEartag", required = false) MultipartFile repellentEartag) {

        logger.info("invoke operatorUpdate {}", repellentPlanModel);
        if (repellentPlanModel.getId() == null) {
            return Responses.errorResponse("Operate wrong");

        } else {

          if (repellentEartag != null) {
            String filePath = pathPre + repellentPlanModel.getFactoryNum().toString() + "/repellentEartag/";
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
    public Response delete(@Min(0) @PathVariable(value = "id") Long id) {
        logger.info("invoke delete {}", id);
        if ("0".equals(id.toString())) {
            return Responses.errorResponse("Wrong id");
        }
        RepellentPlanModel repellentPlanModel = this.repellentPlanService.getRepellentPlanModelById(id);
        String filePath = pathPre + repellentPlanModel.getFactoryNum().toString() + "/repellentEartag/" + repellentPlanModel.getRepellentEartag();
        int row = repellentPlanService.deleteRepellentPlanModelByid(id);
        if (FileUtil.deleteFile(filePath) && row == 1){
            return JudgeUtil.JudgeDelete(row);
        } else {
            return Responses.errorResponse("delete wrong");
        }
    }
}
