package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.DiagnosisPlanExample;
import com.deep.domain.model.DiagnosisPlanWithBLOBs;
import com.deep.domain.model.OtherTime;
import com.deep.domain.service.DiagnosisPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * author: Created  By  Caojiawei
 * date: 2018/2/18  11:46
 */
@Controller
public class DiagnosisResource {

    private final Logger logger = LoggerFactory.getLogger(DiagnosisResource.class);
    @Resource
    private DiagnosisPlanService diagnosisPlanService;

    @ResponseBody
    @RequestMapping(value = "/diagnosisPlan",method = RequestMethod.GET)
    public String helloDiagnosis() {
        return "Hello DiagnosisPlan!";
    }

//    按主键删除的接口：/diagnosisInsert
//    按主键删除的方法名：addPlan()
//    接收参数：整个表单信息（所有参数必填）
//    参数类型为：Long factoryNum;Date diagnosisT;String building;String etB;String operator;String remark;String diagnosisC;String diagnosisM;String drugQ;
    @RequestMapping(value = "/diagnosisInsert",method = RequestMethod.GET)
    public String addPlan(){
        return "DiagnosisInsert";
    }
    @ResponseBody
    @RequestMapping(value = "/diagnosisInsert/show",method = RequestMethod.POST)
    public Response addPlan(@Valid DiagnosisPlanWithBLOBs insert,
                            @Valid OtherTime otherTime,
                            BindingResult bindingResult) throws ParseException {
        logger.info("invoke diagnosisInsert/show {}",insert,otherTime,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("诊疗实施档案录入失败");
            return response;
        }else {
            Byte zero = 0;
            Date diagnosisT = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
            if (!otherTime.getS_diagnosisT().isEmpty()){
                diagnosisT =  formatter.parse(otherTime.getS_diagnosisT());
            }
            insert.setGmtCreate(new Date());
            insert.setDiagnosisT(diagnosisT);
            insert.setIsPass(zero);
            insert.setIsPass1(zero);
            diagnosisPlanService.addPlan(insert);

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan",insert);
            response.setData(data);
            return response;
        }
    }

//    按主键删除的接口：/diagnosisDeleteById
//    按主键删除的方法名：dropPlan()
//    接收参数：整型id，根据主键号删除
    @RequestMapping(value = "/diagnosisDeleteById",method = RequestMethod.GET)
    public String dropPlan(){
        return "DiagnosisDeleteById";
    }
    @ResponseBody
    @RequestMapping(value = "/diagnosisDeleteById/show",method = RequestMethod.DELETE)
    public Response dropPlan(@Valid DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs,
                             BindingResult bindingResult){
        logger.info("invoke diagnosisDeleteById/show {}",diagnosisPlanWithBLOBs,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案删除失败");
            return response;
        }else {
            DiagnosisPlanWithBLOBs delete = new DiagnosisPlanWithBLOBs();
            diagnosisPlanService.dropPlan(diagnosisPlanWithBLOBs.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan",delete);
            response.setData(data);
            return response;
        }
    }

//    操作员使用按主键修改的接口：/diagnosisUpdateByOperator
//    操作员使用按主键修改的方法名：changePlanByOperator()
//    操作员使用接收参数：整个表单信息（整型id必填，各参数选填）
    @RequestMapping(value = "/diagnosisUpdateByOperator",method = RequestMethod.GET)
    public String changePlanByOperator(){
        return "DiagnosisUpdateByOperator";
    }
    @ResponseBody
    @RequestMapping(value = "/diagnosisUpdateByOperator/show",method = RequestMethod.POST)
    public Response changePlanByOperator(@Valid DiagnosisPlanWithBLOBs operator,
                                         @Valid OtherTime otherTime,
                                         BindingResult bindingResult) throws ParseException {
        logger.info("invoke diagnosisUpdateByOperator/show {}",operator,otherTime,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("诊疗实施档案更新(操作员页面)失败");
            return response;
        }else {
            Date diagnosisT = null;
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
            if (!otherTime.getS_diagnosisT().isEmpty()){
                diagnosisT =  formatter.parse(otherTime.getS_diagnosisT());
            }
            operator.setDiagnosisT(diagnosisT);
            diagnosisPlanService.changePlanSelective(operator);

            DiagnosisPlanWithBLOBs selectById = diagnosisPlanService.findPlanById(operator.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    监督者使用按主键修改的接口：/diagnosisUpdateByProfessor
//    监督者使用按主键修改的方法名：changePlanByProfessor()
//    监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
    @RequestMapping(value = "/diagnosisUpdateByProfessor",method = RequestMethod.GET)
    public String changePlanByProfessor(){
        return "DiagnosisUpdateByProfessor";
    }
    @ResponseBody
    @RequestMapping(value = "/diagnosisUpdateByProfessor/show",method = RequestMethod.POST)
    public Response changePlanByProfessor(@Valid DiagnosisPlanWithBLOBs professor,
                                          BindingResult bindingResult){
        logger.info("invoke diagnosisUpdateByProfessor/show {}",professor,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("诊疗实施档案更新(专家页面)失败");
            return response;
        }else {
            professor.setGmtModified(new Date());
            if (professor.getIsPass() == 1){
                professor.setUpassReason("操作员已经修改档案并通过技术审核");
            }
            diagnosisPlanService.changePlanSelective(professor);

            DiagnosisPlanWithBLOBs selectById = diagnosisPlanService.findPlanById(professor.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    监督者使用按主键修改的接口：/diagnosisUpdateBySupervisor
//    监督者使用按主键修改的方法名：changePlanBySupervisor()
//    监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
    @RequestMapping(value = "/diagnosisUpdateBySupervisor",method = RequestMethod.GET)
    public String changePlanBySupervisor(){
        return "DiagnosisUpdateBySupervisor";
    }
    @ResponseBody
    @RequestMapping(value = "/diagnosisUpdateBySupervisor/show",method = RequestMethod.POST)
    public Response changePlanBySupervisor(@Valid DiagnosisPlanWithBLOBs supervisor,
                                           BindingResult bindingResult){
        logger.info("invoke diagnosisUpdateBySupervisor/show {}",supervisor,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("诊疗实施档案更新(监督页面)失败");
            return response;
        }else {
            supervisor.setGmtSupervised(new Date());
            diagnosisPlanService.changePlanSelective(supervisor);

            DiagnosisPlanWithBLOBs selectById = diagnosisPlanService.findPlanById(supervisor.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    按主键查询的接口：/diagnosisSelectById
//    按主键查询的方法名：findPlanById()
//    接收参数：整型的主键号（保留接口查询，前端不调用此接口）
    @RequestMapping(value = "/diagnosisSelectById",method = RequestMethod.GET)
    public String findPlanById(){
        return "DiagnosisSelectById";
    }
    @ResponseBody
    @RequestMapping(value = "/diagnosisSelectById/show",method = RequestMethod.GET)
    public Response findPlanById(@Valid DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs,
                                 BindingResult bindingResult){
        logger.info("invoke diagnosisSelectById/show {}",diagnosisPlanWithBLOBs,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案(根据主键)查询失败");
            return response;
        } else {
            //查询语句的写法：一定要在声明对象时把值直接赋进去
            DiagnosisPlanWithBLOBs selectById = diagnosisPlanService.findPlanById(diagnosisPlanWithBLOBs.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    按条件查询接口：/diagnosisSelective
//    按条件查询方法名：findPlanSelective()
//    接收的参数：前端的各参数，以及两个("s_diagnosisT1")("s_diagnosisT2")时间字符串（所有参数可以选填）
    @RequestMapping(value = "/diagnosisSelective",method = RequestMethod.GET)
    public String findPlanSelective(){
        return "DiagnosisSelective";
    }
    @ResponseBody
    @RequestMapping(value = "/diagnosisSelective/show",method = RequestMethod.GET)
    public Response findPlanSelective(@Valid DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs,
                                      @Valid OtherTime otherTime,
                                      BindingResult bindingResult) throws ParseException {
        logger.info("invoke diagnosisSelective/show {}",diagnosisPlanWithBLOBs,otherTime,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("诊疗实施档案(根据条件)查询失败");
            return response;
        }else {
            Date diagnosisT1 = null;
            Date diagnosisT2 = null;
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
            DiagnosisPlanExample diagnosisPlanExample = new DiagnosisPlanExample();
            DiagnosisPlanExample.Criteria criteria = diagnosisPlanExample.createCriteria();
            if (otherTime.getS_diagnosisT1() != null && otherTime.getS_diagnosisT1() != "" && otherTime.getS_diagnosisT2() != null && otherTime.getS_diagnosisT2() != ""){
                diagnosisT1 =  formatter.parse(otherTime.getS_diagnosisT1());
                diagnosisT2 =  formatter.parse(otherTime.getS_diagnosisT2());
            }
            if(diagnosisT1 != null && diagnosisT2 != null){
                criteria.andDiagnosisTBetween(diagnosisT1,diagnosisT2);
            }

            if(diagnosisPlanWithBLOBs.getId() != null && diagnosisPlanWithBLOBs.getId().toString() !=""){
                criteria.andIdEqualTo(diagnosisPlanWithBLOBs.getId());
            }
            if(diagnosisPlanWithBLOBs.getFactoryNum() != null && diagnosisPlanWithBLOBs.getFactoryNum().toString() !=""){
                criteria.andFactoryNumEqualTo(diagnosisPlanWithBLOBs.getFactoryNum());
            }
            if(diagnosisPlanWithBLOBs.getBuilding() != null && diagnosisPlanWithBLOBs.getBuilding() !=""){
                criteria.andBuildingEqualTo(diagnosisPlanWithBLOBs.getBuilding());
            }
            if(diagnosisPlanWithBLOBs.getEtB() != null && diagnosisPlanWithBLOBs.getEtB() !=""){
                criteria.andEtBEqualTo(diagnosisPlanWithBLOBs.getEtB());
            }
            if(diagnosisPlanWithBLOBs.getOperator() != null && diagnosisPlanWithBLOBs.getOperator() !=""){
                criteria.andOperatorEqualTo(diagnosisPlanWithBLOBs.getOperator());
            }
            if(diagnosisPlanWithBLOBs.getProfessor() != null && diagnosisPlanWithBLOBs.getProfessor() !=""){
                criteria.andProfessorEqualTo(diagnosisPlanWithBLOBs.getProfessor());
            }
            if(diagnosisPlanWithBLOBs.getSupervisor() != null && diagnosisPlanWithBLOBs.getSupervisor() !=""){
                criteria.andSupervisorEqualTo(diagnosisPlanWithBLOBs.getSupervisor());
            }
            if(diagnosisPlanWithBLOBs.getRemark() != null && diagnosisPlanWithBLOBs.getRemark() !=""){
                criteria.andRemarkLike(diagnosisPlanWithBLOBs.getRemark());
            }
            if(diagnosisPlanWithBLOBs.getIsPass() != null && diagnosisPlanWithBLOBs.getIsPass().toString() !=""){
                criteria.andIsPassEqualTo(diagnosisPlanWithBLOBs.getIsPass());
            }
            if(diagnosisPlanWithBLOBs.getUpassReason() != null && diagnosisPlanWithBLOBs.getUpassReason() !=""){
                criteria.andUpassReasonLike(diagnosisPlanWithBLOBs.getUpassReason());
            }
            if(diagnosisPlanWithBLOBs.getIsPass1() != null && diagnosisPlanWithBLOBs.getIsPass1().toString() !=""){
                criteria.andIsPassEqualTo(diagnosisPlanWithBLOBs.getIsPass1());
            }
            List<DiagnosisPlanWithBLOBs> selective = diagnosisPlanService.findPlanSelective(diagnosisPlanExample);
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan",selective);
            response.setData(data);
            return response;
        }
    }

//    供技术审核查询信息:/diagnosisSelectByProfessor
//    供技术审核查询方法名：findPlanSelectByProfessor()
//    接收的参数：前端的各参数，（所有参数可以选填）
    @RequestMapping(value = "/diagnosisSelectByProfessor",method = RequestMethod.GET)
    public String findPlanByProfessor(){
        return "DiagnosisSelectByProfessor";
    }
    @ResponseBody
    @RequestMapping(value = "/diagnosisSelectByProfessor/show",method = RequestMethod.GET)
    public Response findPlanByProfessor(@Valid DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs,
                                        @Valid OtherTime otherTime,
                                        BindingResult bindingResult){
        logger.info("invoke diagnosisSelectByProfessor/show {}",diagnosisPlanWithBLOBs,otherTime,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案(根据条件)查询失败");
            return response;
        }else {
            Byte notPassed = 0;
            DiagnosisPlanExample diagnosisPlanExample = new DiagnosisPlanExample();
            DiagnosisPlanExample.Criteria criteria = diagnosisPlanExample.createCriteria();

            if(diagnosisPlanWithBLOBs.getId() != null && diagnosisPlanWithBLOBs.getId().toString() !=""){
                criteria.andIdEqualTo(diagnosisPlanWithBLOBs.getId());
            }
            if(diagnosisPlanWithBLOBs.getProfessor() != null && diagnosisPlanWithBLOBs.getProfessor() !=""){
                criteria.andProfessorEqualTo(diagnosisPlanWithBLOBs.getProfessor());
            }
            if(diagnosisPlanWithBLOBs.getUpassReason() != null && diagnosisPlanWithBLOBs.getUpassReason() !=""){
                criteria.andUpassReasonLike(diagnosisPlanWithBLOBs.getUpassReason());
            }
            criteria.andIsPassEqualTo(notPassed);
            List<DiagnosisPlanWithBLOBs> select = diagnosisPlanService.findPlanSelective(diagnosisPlanExample);
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan",select);
            response.setData(data);
            return response;
        }
    }

//    供监督者查询信息:/diagnosisSelectBySupervisor
//    供监督者查询方法名：findPlanSelectBySupervisor()
//    接收的参数：前端的各参数，（所有参数可以选填）
    @RequestMapping(value = "/diagnosisSelectBySupervisor",method = RequestMethod.GET)
    public String findPlanSelectBySupervisor(){
        return "DiagnosisSelectBySupervisor";
    }
    @ResponseBody
    @RequestMapping(value = "/diagnosisSelectBySupervisor/show",method = RequestMethod.GET)
    public Response findPlanSelectBySupervisor(@Valid DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs,
                                               @Valid OtherTime otherTime,
                                               BindingResult bindingResult){
        logger.info("invoke diagnosisSelectBySupervisor/show {}",diagnosisPlanWithBLOBs,otherTime,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案(根据条件)查询失败");
            return response;
        }else {
            Byte notPassed1 = 0;
            DiagnosisPlanExample diagnosisPlanExample = new DiagnosisPlanExample();
            DiagnosisPlanExample.Criteria criteria = diagnosisPlanExample.createCriteria();

            if(diagnosisPlanWithBLOBs.getId() != null && diagnosisPlanWithBLOBs.getId().toString() !=""){
                criteria.andIdEqualTo(diagnosisPlanWithBLOBs.getId());
            }
            if(diagnosisPlanWithBLOBs.getSupervisor() != null && diagnosisPlanWithBLOBs.getSupervisor() !=""){
                criteria.andSupervisorEqualTo(diagnosisPlanWithBLOBs.getSupervisor());
            }
            criteria.andIsPass1EqualTo(notPassed1);
            List<DiagnosisPlanWithBLOBs> select = diagnosisPlanService.findPlanSelective(diagnosisPlanExample);
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan",select);
            response.setData(data);
            return response;
        }
    }
}
