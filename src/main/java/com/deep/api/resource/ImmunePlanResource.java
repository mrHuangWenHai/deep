package com.deep.api.resource;

import com.deep.api.Utils.AgentUtil;
import com.deep.api.Utils.TokenAnalysis;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;

import com.deep.domain.model.ImmunePlanModel;
import com.deep.domain.service.FactoryService;
import com.deep.domain.service.ImmunePlanService;
import com.deep.domain.service.UserService;
import com.deep.api.request.ImmuneRequest;
import com.deep.domain.util.JedisUtil;
import com.deep.domain.util.JudgeUtil;
import com.deep.domain.util.UploadUtil;
import com.deep.domain.util.*;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.File;

import java.io.IOException;
import java.io.OutputStream;

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

    //用于查询羊厂代理id
    @Resource
    private FactoryService factoryService;

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
     * @return 插入结果
     */
    @Permit(authorities = "increase_the_immunization_implementation_file")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response saveShow(@Valid ImmunePlanModel immunePlanModel,
                             BindingResult bindingResult)  {
        //  @RequestParam("eartagFile") MultipartFile immuneEartagFile
        if (bindingResult.hasErrors()) {
            Response response = Responses.successResponse();
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("数据异常,请按照规范填写!!",bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }

        logger.info("invoke saveShow {}",immunePlanModel);
        try {
//                String fileName = immuneEartagFile.getOriginalFilename();
//                String filePath = pathPre + immunePlanModel.getFactoryNum().toString() + "/immuneEartag/";
//                fileName = UploadUtil.uploadFile(immuneEartagFile.getBytes(),filePath,fileName);
            //System.out.println("Address"+fileAddress);
            //数据插入数据库
            //System.out.println("mysql执行前");
            //设置插入数据
            //耳牌地址
            //ispass 默认 0
            immunePlanModel.setImmuneEartag(immunePlanModel.getEartagFile());
            immunePlanService.setImmunePlanModel(immunePlanModel);
            short agentID = this.factoryService.queryOneAgentByID(immunePlanModel.getFactoryNum().longValue());
            String professorKey = agentID + "_professor";
            String supervisorKey = immunePlanModel.getFactoryNum().toString() + "_supervisor";
            String testSendProfessor = agentID + "_professor_AlreadySend";
            String testSendSupervisor = immunePlanModel.getFactoryNum().toString() + "_supervisor_AlreadySend";

            JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
            JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);
            //若redis中 若干天未发送短信
            //若未完成超过50条
            if (!("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))) {
                if (JedisUtil.redisJudgeTime(professorKey)) {
                    List<String> phone = this.userService.getProfessorTelephoneByFactoryNum(immunePlanModel.getFactoryNum());

                    if (phone.size() != 0) {
                      if (JedisUtil.redisSendMessage(phone, JedisUtil.getCertainKeyValue("Message"))) {
                        JedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
                      }
                    }
                }
            }
            if (!("1".equals(JedisUtil.getCertainKeyValue(testSendSupervisor)))) {
                if (JedisUtil.redisJudgeTime(supervisorKey)) {
                    List<String> phone = userService.getSuperiorTelephoneByFactoryNum(immunePlanModel.getFactoryNum());
                    if (phone.size() != 0) {
                      if (JedisUtil.redisSendMessage(phone, JedisUtil.getCertainKeyValue("Message"))) {
                        System.out.println("发送成功！");
                        JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
                      }
                    }
                }
            }
            return JudgeUtil.JudgeSuccess("id", immunePlanModel.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return Responses.errorResponse("数据录入成功, 短信服务器异常!");
        }
    }

    /**
     * 返回查询结果
     * 分页查询
     * METHOD:POST
     * @param immuneRequest 免疫请求类
     * @return 查询结果
     */
    @Permit(authorities = "query_the_immunization_implementation_file")
    @GetMapping(value = "/{id}")
    public Response findShow(@PathVariable(value = "id")long id,
                             ImmuneRequest immuneRequest,
                             HttpServletRequest httpServletRequest) {

      logger.info("invoke  /ip/{} {}", id,immuneRequest);
        //前台传参数
      Map<Long, List<Long>> factoryMap = null;

      String roleString = TokenAnalysis.getFlag(httpServletRequest.getHeader(Constants.AUTHORIZATION));
      if (roleString == null) {
        return Responses.errorResponse("认证信息错误");
      }
      Byte role = Byte.parseByte(roleString);


      if (role == 0) {
        immuneRequest.setFactoryNum(id);
      } else if (role == 1) {
        factoryMap = AgentUtil.getAllSubordinateFactory(String.valueOf(id));
        List<Long> factoryList = new ArrayList<>();
        assert factoryMap != null;
        factoryList.addAll(factoryMap.get((long) -1));
        factoryList.addAll(factoryMap.get(0L));
        if (factoryList.size() == 0) {
            return Responses.errorResponse("本级代理没有发展羊场和代理！");
        }
        immuneRequest.setFactoryList(factoryList);
      } else {
        return Responses.errorResponse("你没有权限");
      }

      List<ImmunePlanModel> totalList = immunePlanService.getImmunePlanModel(immuneRequest);
      int size = totalList.size();
      int page = immuneRequest.getPage();
      int pageSize = immuneRequest.getSize();
      int destIndex = (page+1) * pageSize  > size ? size : (page+1) * pageSize;
      List<ImmunePlanModel> immunePlanModels = totalList.subList(page * pageSize, destIndex);
      if (role == 1) {
        Map<String,Object> data = new HashMap<>();
        List<ImmunePlanModel> factorylist = new ArrayList<>();
        List<ImmunePlanModel> direct = new ArrayList<>();
        List<ImmunePlanModel> others = new ArrayList<>();
        List<Long> directId = factoryMap.get((long) -1);
        for (ImmunePlanModel immunePlanModel : immunePlanModels) {
          if (directId.contains(immunePlanModel.getFactoryNum())) {
            direct.add(immunePlanModel);
          } else {
            others.add(immunePlanModel);
          }
        }

        factorylist.addAll(direct);
        factorylist.addAll(others);
        data.put("List", factorylist);
        data.put("size", size);
        data.put("directSize",direct.size());
        Response response = Responses.successResponse();
        response.setData(data);
        return response;
      } else {
        return JudgeUtil.JudgeFind(immunePlanModels,size);
      }

    }

    /**
     * 用于id查询
     * @param id id
     * @return 查询结果
     */
    @Permit(authorities = "query_the_immunization_implementation_file")
    @RequestMapping(value = "/find/{id}",method = RequestMethod.GET)
    public Response find(@PathVariable("id") long id) {
        logger.info("invoke find{id} {}" , id);
        ImmunePlanModel immunePlanModel = this.immunePlanService.getImmunePlanModelById(id);
        return JudgeUtil.JudgeFind(immunePlanModel);
    }

    /**
     * TODO： 根据需求 增加是否通过ispass查询
     * 通过耳牌号模糊查找
     * @param immuneEartag 耳牌
     * @param page 页
     * @param size 条
     * @return 查询结果
     */
    @Permit(authorities = "query_the_immunization_implementation_file")
    @RequestMapping(value = "findet",method = RequestMethod.GET)
    public Response findByEarTag(@RequestParam("eartag") List<String[]> immuneEartag,
                                 @RequestParam(value = "page",defaultValue = "0") int page,
                                 @RequestParam(value = "size",defaultValue = "10") int size){
        logger.info("invoke findByEarTag {}" );
        List<ImmunePlanModel> list = this.immunePlanService.getImmunePlanModelByTradeMarkEarTag(immuneEartag, new RowBounds(page * size , size));
        return JudgeUtil.JudgeFind(list,list.size());
    }

    /**
     * 下载文件 并保存到自定义路径
     * @param response  HttpServletResponse
     * @param factoryNum  下载文件所属工厂号
     * @param fileName 文件名
     * @return  下载结果
     */
    @Permit(authorities = "download_product_file_action")
    @RequestMapping(value = "/down/{factoryNum}/{fileName}",method = RequestMethod.GET)
    public Response download(HttpServletResponse response,
                             @PathVariable("factoryNum") String factoryNum,
                             @PathVariable("fileName") String fileName) throws Exception{
        logger.info("invoke download {}", response, factoryNum, fileName);
        String filePath = pathPre + factoryNum + "/immuneEartag/";
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        };
        if (DownloadUtil.testDownload(response , filePath, fileName, outputStream)) {
            outputStream.close();
            return JudgeUtil.JudgeSuccess("download","Success");
        } else {
            return Responses.errorResponse("下载失败");
        }
    }

    /**
     * 审核入口 审核isPass = 0的数据
     * METHOD:PATCH
     * @param immunePlanModel 免疫类
     * @return 更新结果
     */
    @Permit(authorities = "experts_review_immune_implementation_files")
    @RequestMapping(value = "/p/{id}",method = RequestMethod.PATCH)
    public Response professorUpdate(@PathVariable(value = "id") long id,
                                    @RequestBody ImmunePlanModel immunePlanModel) {

        logger.info("invoke PATCH /p/{id} {} {}",id, immunePlanModel);
        if( immunePlanModel.getIspassCheck() == null
            || immunePlanModel.getProfessor() == null) {
            return Responses.errorResponse("请完善表单信息!");
        } else {
          immunePlanModel.setId(id);
          immunePlanModel.setProfessor(immunePlanModel.getName());
          immunePlanModel.setUpassReason(immunePlanModel.getUnpassReason());
          int row = immunePlanService.updateImmunePlanModelByProfessor(immunePlanModel);
          if (row == 1) {
              System.out.println("factoryNumber: " + immunePlanModel.getFactoryNum());
            String professorKey = this.factoryService.getAgentIDByFactoryNumber(Long.valueOf(immunePlanModel.getFactoryNum().toString())) + "_professor";
            System.out.println("professorKey" + professorKey);
              if ("1".equals(immunePlanModel.getIspassCheck()) && !JedisUtil.redisCancelProfessorSupervisorWorks(professorKey)) {
                return Responses.errorResponse("审核成功, 短信服务器异常");
            }
          }
          return JudgeUtil.JudgeUpdate(row);
        }

    }

    /**
     * 监督员入口 审核isPass1 = 0的数据
     * 审核要求:审核时要求条例写完整 审核后 isPass = 1时 无权限再修改
     * @param immunePlanModel 免疫类
     * METHOD:PATCH
     * @return 审核结果
     */
    @Permit(authorities = "surveillance_audit_of_immunization_implementation_files")
    @RequestMapping(value = "/s/{id}",method = RequestMethod.PATCH)
    public Response supervisorUpdate(@PathVariable(value = "id")long id,
                                     @RequestBody ImmunePlanModel immunePlanModel){
        logger.info("invoke supervisorUpdate {}", immunePlanModel);
        if( immunePlanModel.getSupervisor() == null
            || immunePlanModel.getIspassSup() == null) {
            return Responses.errorResponse("请完善表单信息!");
        } else {
          immunePlanModel.setId(id);
          immunePlanModel.setSupervisor(immunePlanModel.getName());
          int row = immunePlanService.updateImmunePlanModelBySupervisor(immunePlanModel);
          if (row == 1) {
            String supervisorKey = immunePlanModel.getFactoryNum().toString() + "_supervisor";
            if (!JedisUtil.redisCancelProfessorSupervisorWorks(supervisorKey)){
                return Responses.errorResponse("审核成功, 短信服务器异常");
            }
          }
          return JudgeUtil.JudgeUpdate(row);
        }
    }


    /**
     * 操作员在审核前想修改数据的接口
     * 或处理被退回操作的接口
     * @param immunePlanModel 免疫类
     * @return 更新结果
     */

    @Permit(authorities = "modify_the_immunization_implementation_file")
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public Response operatorUpdate(@PathVariable(value = "id") long id,
                                   @Validated ImmunePlanModel immunePlanModel,
                                   BindingResult bindingResult) {
          logger.info("invoke operatorUpdate {}", immunePlanModel);
          // @RequestParam(value = "immuneEartagFile", required = false) MultipartFile immuneEartagFile
          if (bindingResult.hasErrors()) {
             Response response = Responses.successResponse();
             Map<String, Object> data = new HashMap<String, Object>();
             data.put("error",bindingResult.getAllErrors());
             response.setData(data);
             return response;
           }
           immunePlanModel.setId(id);
           ImmunePlanModel temp = this.immunePlanService.getImmunePlanModelById(id);
           if ("1".equals(temp.getIspassCheck())){
              return Responses.errorResponse("该条数据已被审核,无法修改");
           }
           immunePlanModel.setIspassCheck("2");
           immunePlanModel.setImmuneEartag(immunePlanModel.getEartagFile());
           int row = this.immunePlanService.updateImmunePlanModelByOperator(immunePlanModel);
           return JudgeUtil.JudgeUpdate(row);
    }

    /**
     * 删除id = certain 的数据
     * 权限设置
     * @param id id
     * @return 删除结果
     */
    @Permit(authorities = "delete_the_immunization_implementation_file")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Response delete(@Min(0) @PathVariable(value = "id") Long id) {

        logger.info("invoke delete {}", id);
        if ("0".equals(id.toString())) {
            return Responses.errorResponse("无效的ID");
        }

        ImmunePlanModel immunePlanModel = this.immunePlanService.getImmunePlanModelById(id);
        String filePath = pathPre + immunePlanModel.getFactoryNum().toString() + "/immuneEartag/" + immunePlanModel.getImmuneEartag();
        if (!("1".equals(immunePlanModel.getIspassCheck()))) {
            int row = immunePlanService.deleteImmunePlanModelById(id);
            if (FileUtil.deleteFile(filePath) && row == 1){
                return JudgeUtil.JudgeDelete(row);
            } else {
                return Responses.errorResponse("删除失败");
            }
        } else {
            return Responses.errorResponse("该条记录已经被审核过，不能删除！");
        }
    }
}
