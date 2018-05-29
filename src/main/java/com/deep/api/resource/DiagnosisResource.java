package com.deep.api.resource;

import com.deep.api.Utils.AgentUtil;
import com.deep.api.Utils.TokenAnalysis;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.request.DiagnosisRequest;
import com.deep.api.request.ProfessorRequest;
import com.deep.api.request.SupervisorRequest;
import com.deep.domain.model.DiagnosisPlanModel;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.service.DiagnosisPlanService;
import com.deep.domain.service.FactoryService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * author: Created  By  Caojiawei
 * date: 2018/2/18  11:46
 */
@RestController

@RequestMapping(value = "/diagnosis")
public class DiagnosisResource {

    private final Logger logger = LoggerFactory.getLogger(DiagnosisResource.class);

    @Resource
    private DiagnosisPlanService diagnosisPlanService;

    @Resource
    private UserService userService;

    @Resource
    private FactoryService factoryService;

    /**
     * 添加的接口：/addPlan
     * 接收参数：整个表单信息（所有参数必填）
     * 参数类型为：Long factoryNum;Date diagnosisT;String building;String etB;String operator;String remark;String diagnosisC;String diagnosisM;String drugQ;
     */
    @Permit(authorities = "increase_disease_prevention_files")
    @PostMapping(value = "")
    public Response addPlan(@RequestBody @Valid DiagnosisPlanModel diagnosisPlanModel,
                            BindingResult bindingResult) throws ParseException {

        logger.info("invoke diagnosis/insert {}", diagnosisPlanModel);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("param is error");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("param",bindingResult.getAllErrors());
            response.setData(map);
            return response;
        } else {
            int isSuccess = diagnosisPlanService.addPlan(diagnosisPlanModel);
            if (isSuccess <= 0) {

                return Responses.errorResponse("插入错误");
            }
            short agentID = this.factoryService.queryOneAgentByID(diagnosisPlanModel.getFactoryNum().longValue());
            String professorKey = agentID + "_professor";
            String supervisorKey = diagnosisPlanModel.getFactoryNum().toString() + "_supervisor";

            String testSendProfessor = agentID + "_professor_AlreadySend";
            String testSendSupervisor = diagnosisPlanModel.getFactoryNum().toString() + "_supervisor_AlreadySend";

            JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
            JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

            if( !("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))) {
                if( JedisUtil.redisJudgeTime(professorKey) ) {
                    System.out.println("in redis:");
                    List<String> phone = userService.getProfessorTelephoneByFactoryNum(BigInteger.valueOf(diagnosisPlanModel.getFactoryNum()));

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

            if( !("1".equals(JedisUtil.getCertainKeyValue(testSendSupervisor)))) {
                if(JedisUtil.redisJudgeTime(supervisorKey)) {
                    List<String> phone = userService.getSuperiorTelephoneByFactoryNum(BigInteger.valueOf(diagnosisPlanModel.getFactoryNum()));

                    StringBuffer phoneList = new StringBuffer("");

                    for (String aPhone : phone) {
                        phoneList = phoneList.append(aPhone).append(",");
                    }

                    if (phoneList.length() != 0) {

                        if( JedisUtil.redisSendMessage(phoneList.toString(), JedisUtil.getCertainKeyValue("Message"))) {
                            JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor,"1",Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime"))*24*60*60);
                        }
                    }
                }
            }

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("id",diagnosisPlanModel.getId());
            response.setData(data);
            return response;
        }
    }

    /**
     * 按照主键删除的接口
     * @param id 记录主键
     * @return response响应
     */
    @Permit(authorities = "delete_disease_prevention_files")
    @DeleteMapping(value = "/{id}")
    public Response dropPlan(@PathVariable("id") int id) {
        logger.info("invoke diagnosis/{}", id);
        // 首先查询该条数据
        DiagnosisPlanModel diagnosisPlanModel = diagnosisPlanService.findPlanById(id);
        if (diagnosisPlanModel.getIspassSup() == 2 && diagnosisPlanModel.getIspassCheck() == 2) {
            int isSuccess =  diagnosisPlanService.dropPlan(id);
            if (isSuccess == 0) {
                return Responses.errorResponse("删除失败");
            }
            return Responses.successResponse();
        } else {
            return Responses.errorResponse("该记录已经被审核，不能进行删除操作");
        }
    }

    /**
     * 操作员使用按主键修改的接口：/{id}
     * 操作员使用按主键修改的方法名：changePlanByOperator()
     * 操作员使用接收参数：整个表单信息（整型id必填，各参数选填）
     * @param diagnosisPlanModel 修改对象模型
     * @param bindingResult 错误信息
     * @return response 响应
     * @throws ParseException 异常exception
     */
    @Permit(authorities = "increase_disease_prevention_files")
    @PatchMapping(value = "/{id}")
    public Response changePlanByOperator(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid DiagnosisPlanModel diagnosisPlanModel,
                                         BindingResult bindingResult) throws ParseException {
        if (id < 0) {
          return Responses.errorResponse("id is invalide");
        }

        if (bindingResult.hasErrors()) {
          Response response = Responses.errorResponse("param is error");
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("param",bindingResult.getAllErrors());
          response.setData(map);
          return response;
        }

        diagnosisPlanModel.setId(id);

        logger.info("invoke diagnosis/update {}", diagnosisPlanModel);
        int isSuccess = diagnosisPlanService.updateDiagnosisPlanModel(diagnosisPlanModel);
        if (isSuccess == 0) {
            return Responses.errorResponse("修改失败");
        }
        return Responses.successResponse();
    }

    /**
     * 监督者使用按主键修改的接口：/diagnosisUpdateByProfessor
     * 监督者使用按主键修改的方法名：changePlanByProfessor()
     * 监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
     * @return Response
     */
    @Permit(authorities = "experts_review_disease_prevention_files")
    @RequestMapping(value = "/p/{id}",method = RequestMethod.PATCH)
    public Response changePlanByProfessor(@PathVariable(value = "id") int id,
                                          @RequestBody ProfessorRequest professorRequest) {
        logger.info("invoke diagnosis/p/{} {}", id, professorRequest.toString());
//        short ispassCheck = json.get("ispassCheck").shortValue();
        if (professorRequest.getIspassCheck() == 2) {
            return Responses.errorResponse("已经审批过了");
        }
        int isSuccess =  diagnosisPlanService.checkDiagnosisPlanModelById(id, professorRequest.getIspassCheck(), professorRequest.getProfessor(), professorRequest.getName());
        if (isSuccess == 0) {
            return Responses.errorResponse("错误");
        }
        String professorKey = this.factoryService.getAgentIDByFactoryNumber(professorRequest.getFactoryNum().toString()) + "_professor";
        JedisUtil.redisCancelProfessorSupervisorWorks(professorKey);
// TODO
//        if (!JedisUtil.redisCancelProfessorSupervisorWorks(professorKey)) {
//            return Responses.errorResponse("cancel error");
//        }
        return Responses.successResponse();
    }

    /**
     * 监督者使用按主键修改的接口：/diagnosisUpdateBySupervisor
     * 监督者使用按主键修改的方法名：changePlanBySupervisor()
     * 监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
     * @return response
     */
    @Permit(authorities = "supervise_and_verify_disease_prevention_files")
    @RequestMapping(value = "/s/{id}",method = RequestMethod.PATCH)
    public Response changePlanBySupervisor(@PathVariable(value = "id") int id,
                                           @RequestBody SupervisorRequest supervisorRequest) {
        logger.info("invoke diagnosis/supdate/{} {}", id, supervisorRequest.toString());
        if (supervisorRequest.getIspassSup() == 2) {
            return Responses.errorResponse("已经审批过了");
        }

        int isSuccess = diagnosisPlanService.supCheckDiagnosisPlanModelById(id, supervisorRequest.getIspassSup(), null, supervisorRequest.getSupervisor(), supervisorRequest.getName());
        if (isSuccess == 0) {
            return Responses.errorResponse("监督失败");
        }
        String supervisorKey = supervisorRequest.getFactoryNum().toString() + "_supervisor";
        JedisUtil.redisCancelProfessorSupervisorWorks(supervisorKey);
// TODO
//   if (!JedisUtil.redisCancelProfessorSupervisorWorks(supervisorKey)){
//            return Responses.errorResponse("cancel error");
//        }
        return Responses.successResponse();
    }

    /**
     * 按主键查询的接口：/{id}
     * 接收参数：整型的主键号（保留接口查询，前端不调用此接口）
     * @return Response
     */
    @Permit(authorities = "check_disease_prevention_files")
    @GetMapping(value = "find/{id}")
    public Response findPlanById(@PathVariable("id") int id) {
        logger.info("invoke Get diagnosis/{}", id);
        if (id < 0) {
            return Responses.errorResponse("id 无效");
        }

        //查询语句的写法：一定要在声明对象时把值直接赋进去
        DiagnosisPlanModel diagnosisPlanModel = diagnosisPlanService.findPlanById(id);
        if (diagnosisPlanModel == null) {
            return Responses.errorResponse("查询失败");
        }

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", diagnosisPlanModel);

        response.setData(data);
        return response;
    }

    /**
     * 按条件查询接口：/
     * 按条件查询方法名：findPlanSelective()
     * @param diagnosisRequest 按条件查询对象模型
     * @return Response
     */
    @Permit(authorities = "check_disease_prevention_files")
    @GetMapping(value = "/{id}")
    public Response findPlanSelective(@PathVariable(value = "id")long id,
                                      DiagnosisRequest diagnosisRequest,
                                      HttpServletRequest httpServletRequest) {

        logger.info("invoke Get diagnosis {}", diagnosisRequest);

        Map<Long, List<Long>> factoryMap = null;
        String roleString = TokenAnalysis.getFlag(httpServletRequest.getHeader(Constants.AUTHORIZATION));

        if (roleString == null) {
            return Responses.errorResponse("认证信息错误");
        }

        Byte role = Byte.parseByte(roleString);
        if (role == 0) {
            diagnosisRequest.setFactoryNum(id);
        } else if (role == 1) {
            factoryMap = AgentUtil.getAllSubordinateFactory(String.valueOf(id));
            List<Long> factoryList = new ArrayList<>();
            factoryList.addAll(factoryMap.get(new Long(-1)));
            factoryList.addAll(factoryMap.get(new Long(0)));
            diagnosisRequest.setFactoryList(factoryList);
        } else {
            return Responses.errorResponse("你没有权限");
        }

        List<DiagnosisPlanModel> totalList = diagnosisPlanService.selectDiagnosisPlanModelByDiagnosisRequest(diagnosisRequest);
        int size = totalList.size();
        int page = diagnosisRequest.getPage();
        int pageSize = diagnosisRequest.getSize();
        int destIndex = (page+1) * pageSize + 1  > size ? size : (page+1) * pageSize + 1;
        List<DiagnosisPlanModel> diagnosisPlanModels = totalList.subList(page * pageSize, destIndex);

        if (role == 1) {
            Map<String,Object> data = new HashMap<>();
            List<DiagnosisPlanModel> factorylist = new ArrayList<>();
            List<DiagnosisPlanModel> direct = new ArrayList<>();
            List<DiagnosisPlanModel> others = new ArrayList<>();
            List<Long> directId = factoryMap.get(new Long(-1));
            for (DiagnosisPlanModel diagnosisPlanModel : diagnosisPlanModels) {
                if (directId.contains(diagnosisPlanModel.getFactoryNum())) {
                    direct.add(diagnosisPlanModel);
                } else {
                    others.add(diagnosisPlanModel);
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
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("List", diagnosisPlanModels);
            data.put("size", size);
            response.setData(data);
            return response;
        }

    }

//    @GetMapping(value = "professor/select")
//    public Response professorFindPlan(@RequestParam("factoryNum") long factoryNum,
//                                      @RequestParam("ispassSup") int ispassSup,
//                                      @RequestParam(value = "page", defaultValue = "0") int page,
//                                      @RequestParam(value = "size", defaultValue = "10") int size) {
//      logger.info("invoke Get diagnosis/professor/select {},{},{},{}", factoryNum,ispassSup,page,size);
//      RowBounds rowBounds = new RowBounds(page*size,size);
//
//    }
}
