package com.deep.api.resource;

import com.deep.api.request.DiagnosisPlanModel;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.DiagnosisPlanExample;
import com.deep.domain.model.DiagnosisPlanWithBLOBs;
import com.deep.domain.model.OtherTime;
import com.deep.domain.service.DiagnosisPlanService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public Response addPlan(@RequestBody @Valid DiagnosisPlanModel planModel,
                            BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("诊疗实施档案录入失败");
        }else {
            //将planModel部分变量拆分传递给对象insert
            DiagnosisPlanWithBLOBs insert = new DiagnosisPlanWithBLOBs();
            insert.setId(planModel.getId());
            insert.setGmtCreate(planModel.getGmtCreate());
            insert.setGmtModified(planModel.getGmtModified());
            insert.setSupervisor(planModel.getSupervisor());
            insert.setFactoryNum(planModel.getFactoryNum());
            insert.setBuilding(planModel.getBuilding());
            insert.setEtB(planModel.getEtB());
            insert.setDiagnosisT(planModel.getDiagnosisT());
            insert.setOperator(planModel.getOperator());
            insert.setProfessor(planModel.getProfessor());
            insert.setSupervisor(planModel.getSupervisor());
            insert.setRemark(planModel.getRemark());
            insert.setIsPass(planModel.getIsPass());
            insert.setUpassReason(planModel.getUpassReason());
            insert.setIsPass1(planModel.getIsPass1());
            insert.setDiagnosisC(planModel.getDiagnosisC());
            insert.setDiagnosisM(planModel.getDiagnosisM());
            insert.setDrugQ(planModel.getDrugQ());

            //将planModel部分变量拆分传递给对象otherTime
            OtherTime otherTime = new OtherTime();
            otherTime.setSearch_string(planModel.getSearch_string());
            otherTime.setS_breedingT(planModel.getS_breedingT());
            System.out.println(otherTime.getS_breedingT());
            otherTime.setS_gestationT(planModel.getS_gestationT());
            System.out.println(otherTime.getS_gestationT());
            otherTime.setS_prenatalIT(planModel.getS_prenatalIT());
            System.out.println(otherTime.getS_prenatalIT());
            otherTime.setS_cubT(planModel.getS_cubT());
            System.out.println(otherTime.getS_cubT());
            otherTime.setS_diagnosisT(planModel.getS_diagnosisT());
            otherTime.setS_nutritionT(planModel.getS_nutritionT());
            otherTime.setS_gmtCreate1(planModel.getS_gmtCreate1());
            otherTime.setS_gmtCreate2(planModel.getS_gmtCreate2());
            otherTime.setS_gmtModified1(planModel.getS_gmtModified1());
            otherTime.setS_gmtModified2(planModel.getS_gmtModified2());
            otherTime.setS_breedingT1(planModel.getS_breedingT1());
            otherTime.setS_breedingT2(planModel.getS_breedingT2());
            otherTime.setS_prenatalIT1(planModel.getS_prenatalIT1());
            otherTime.setS_prenatalIT2(planModel.getS_prenatalIT2());
            otherTime.setS_gestationT1(planModel.getS_gestationT1());
            otherTime.setS_gestationT2(planModel.getS_gestationT2());
            otherTime.setS_cubT1(planModel.getS_cubT1());
            otherTime.setS_cubT2(planModel.getS_cubT2());
            otherTime.setS_diagnosisT1(planModel.getS_diagnosisT1());
            otherTime.setS_diagnosisT2(planModel.getS_diagnosisT2());
            otherTime.setS_nutritionT1(planModel.getS_nutritionT1());
            otherTime.setS_nutritionT2(planModel.getS_nutritionT2());
            otherTime.setDownloadPath(planModel.getDownloadPath());
            otherTime.setPage(planModel.getPage());
            otherTime.setSize(planModel.getSize());

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
    public Response dropPlan(@RequestBody @Valid DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("育种实施档案删除失败");
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
    public Response changePlanByOperator(@RequestBody @Valid DiagnosisPlanModel planModel,
                                         BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("诊疗实施档案更新(操作员页面)失败");
        }else {
            //将planModel部分变量拆分传递给对象operator
            DiagnosisPlanWithBLOBs operator = new DiagnosisPlanWithBLOBs();
            operator.setId(planModel.getId());
            operator.setGmtCreate(planModel.getGmtCreate());
            operator.setGmtModified(planModel.getGmtModified());
            operator.setSupervisor(planModel.getSupervisor());
            operator.setFactoryNum(planModel.getFactoryNum());
            operator.setBuilding(planModel.getBuilding());
            operator.setEtB(planModel.getEtB());
            operator.setDiagnosisT(planModel.getDiagnosisT());
            operator.setOperator(planModel.getOperator());
            operator.setProfessor(planModel.getProfessor());
            operator.setSupervisor(planModel.getSupervisor());
            operator.setRemark(planModel.getRemark());
            operator.setIsPass(planModel.getIsPass());
            operator.setUpassReason(planModel.getUpassReason());
            operator.setIsPass1(planModel.getIsPass1());
            operator.setDiagnosisC(planModel.getDiagnosisC());
            operator.setDiagnosisM(planModel.getDiagnosisM());
            operator.setDrugQ(planModel.getDrugQ());

            //将planModel部分变量拆分传递给对象otherTime
            OtherTime otherTime = new OtherTime();
            otherTime.setSearch_string(planModel.getSearch_string());
            otherTime.setS_breedingT(planModel.getS_breedingT());
            System.out.println(otherTime.getS_breedingT());
            otherTime.setS_gestationT(planModel.getS_gestationT());
            System.out.println(otherTime.getS_gestationT());
            otherTime.setS_prenatalIT(planModel.getS_prenatalIT());
            System.out.println(otherTime.getS_prenatalIT());
            otherTime.setS_cubT(planModel.getS_cubT());
            System.out.println(otherTime.getS_cubT());
            otherTime.setS_diagnosisT(planModel.getS_diagnosisT());
            otherTime.setS_nutritionT(planModel.getS_nutritionT());
            otherTime.setS_gmtCreate1(planModel.getS_gmtCreate1());
            otherTime.setS_gmtCreate2(planModel.getS_gmtCreate2());
            otherTime.setS_gmtModified1(planModel.getS_gmtModified1());
            otherTime.setS_gmtModified2(planModel.getS_gmtModified2());
            otherTime.setS_breedingT1(planModel.getS_breedingT1());
            otherTime.setS_breedingT2(planModel.getS_breedingT2());
            otherTime.setS_prenatalIT1(planModel.getS_prenatalIT1());
            otherTime.setS_prenatalIT2(planModel.getS_prenatalIT2());
            otherTime.setS_gestationT1(planModel.getS_gestationT1());
            otherTime.setS_gestationT2(planModel.getS_gestationT2());
            otherTime.setS_cubT1(planModel.getS_cubT1());
            otherTime.setS_cubT2(planModel.getS_cubT2());
            otherTime.setS_diagnosisT1(planModel.getS_diagnosisT1());
            otherTime.setS_diagnosisT2(planModel.getS_diagnosisT2());
            otherTime.setS_nutritionT1(planModel.getS_nutritionT1());
            otherTime.setS_nutritionT2(planModel.getS_nutritionT2());
            otherTime.setDownloadPath(planModel.getDownloadPath());
            otherTime.setPage(planModel.getPage());
            otherTime.setSize(planModel.getSize());

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
    public Response changePlanByProfessor(@RequestBody @Valid DiagnosisPlanWithBLOBs professor,
                                          BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("诊疗实施档案更新(专家页面)失败");
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
    public Response changePlanBySupervisor(@RequestBody @Valid DiagnosisPlanWithBLOBs supervisor,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("诊疗实施档案更新(监督页面)失败");
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
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("育种实施档案(根据条件)查询失败");
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
    @RequestMapping(value = "/diagnosisSelective/show",method = RequestMethod.POST)
    public Response findPlanSelective(@RequestBody @Valid DiagnosisPlanModel planModel,
                                      BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("诊疗实施档案(根据条件)查询失败");
        }else {
            //将planModel部分变量拆分传递给对象diagnosisPlanWithBLOBs
            DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs = new DiagnosisPlanWithBLOBs();
            diagnosisPlanWithBLOBs.setId(planModel.getId());
            diagnosisPlanWithBLOBs.setGmtCreate(planModel.getGmtCreate());
            diagnosisPlanWithBLOBs.setGmtModified(planModel.getGmtModified());
            diagnosisPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            diagnosisPlanWithBLOBs.setFactoryNum(planModel.getFactoryNum());
            diagnosisPlanWithBLOBs.setBuilding(planModel.getBuilding());
            diagnosisPlanWithBLOBs.setEtB(planModel.getEtB());
            diagnosisPlanWithBLOBs.setDiagnosisT(planModel.getDiagnosisT());
            diagnosisPlanWithBLOBs.setOperator(planModel.getOperator());
            diagnosisPlanWithBLOBs.setProfessor(planModel.getProfessor());
            diagnosisPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            diagnosisPlanWithBLOBs.setRemark(planModel.getRemark());
            diagnosisPlanWithBLOBs.setIsPass(planModel.getIsPass());
            diagnosisPlanWithBLOBs.setUpassReason(planModel.getUpassReason());
            diagnosisPlanWithBLOBs.setIsPass1(planModel.getIsPass1());
            diagnosisPlanWithBLOBs.setDiagnosisC(planModel.getDiagnosisC());
            diagnosisPlanWithBLOBs.setDiagnosisM(planModel.getDiagnosisM());
            diagnosisPlanWithBLOBs.setDrugQ(planModel.getDrugQ());

            //将planModel部分变量拆分传递给对象otherTime
            OtherTime otherTime = new OtherTime();
            otherTime.setSearch_string(planModel.getSearch_string());
            otherTime.setS_breedingT(planModel.getS_breedingT());
            System.out.println("getS_breedingT"+otherTime.getS_breedingT());
            otherTime.setS_gestationT(planModel.getS_gestationT());
            System.out.println("getS_gestationT"+otherTime.getS_gestationT());
            otherTime.setS_prenatalIT(planModel.getS_prenatalIT());
            System.out.println("getS_prenatalIT"+otherTime.getS_prenatalIT());
            otherTime.setS_cubT(planModel.getS_cubT());
            System.out.println("getS_cubT"+otherTime.getS_cubT());
            otherTime.setS_diagnosisT(planModel.getS_diagnosisT());
            System.out.println("getS_diagnosisT"+otherTime.getS_diagnosisT());
            otherTime.setS_nutritionT(planModel.getS_nutritionT());
            System.out.println("getS_nutritionT"+otherTime.getS_nutritionT());
            otherTime.setS_gmtCreate1(planModel.getS_gmtCreate1());
            otherTime.setS_gmtCreate2(planModel.getS_gmtCreate2());
            otherTime.setS_gmtModified1(planModel.getS_gmtModified1());
            otherTime.setS_gmtModified2(planModel.getS_gmtModified2());
            otherTime.setS_breedingT1(planModel.getS_breedingT1());
            otherTime.setS_breedingT2(planModel.getS_breedingT2());
            otherTime.setS_prenatalIT1(planModel.getS_prenatalIT1());
            otherTime.setS_prenatalIT2(planModel.getS_prenatalIT2());
            otherTime.setS_gestationT1(planModel.getS_gestationT1());
            otherTime.setS_gestationT2(planModel.getS_gestationT2());
            otherTime.setS_cubT1(planModel.getS_cubT1());
            otherTime.setS_cubT2(planModel.getS_cubT2());
            otherTime.setS_diagnosisT1(planModel.getS_diagnosisT1());
            otherTime.setS_diagnosisT2(planModel.getS_diagnosisT2());
            otherTime.setS_nutritionT1(planModel.getS_nutritionT1());
            otherTime.setS_nutritionT2(planModel.getS_nutritionT2());
            otherTime.setDownloadPath(planModel.getDownloadPath());
            otherTime.setPage(planModel.getPage());
            otherTime.setSize(planModel.getSize());

            Date diagnosisT1 = null;
            Date diagnosisT2 = null;
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
            DiagnosisPlanExample diagnosisPlanExample = new DiagnosisPlanExample();
            DiagnosisPlanExample.Criteria criteria = diagnosisPlanExample.createCriteria();
            if (otherTime.getS_diagnosisT1() != null && !otherTime.getS_diagnosisT1().isEmpty()&& otherTime.getS_diagnosisT2() != null && !otherTime.getS_diagnosisT2().isEmpty()){
                diagnosisT1 =  formatter.parse(otherTime.getS_diagnosisT1());
                diagnosisT2 =  formatter.parse(otherTime.getS_diagnosisT2());
            }
            if(diagnosisT1 != null && diagnosisT2 != null){
                criteria.andDiagnosisTBetween(diagnosisT1,diagnosisT2);
            }

            if(diagnosisPlanWithBLOBs.getId() != null && !diagnosisPlanWithBLOBs.getId().toString().isEmpty()){
                criteria.andIdEqualTo(diagnosisPlanWithBLOBs.getId());
            }
            if(diagnosisPlanWithBLOBs.getFactoryNum() != null && !diagnosisPlanWithBLOBs.getFactoryNum().toString().isEmpty()){
                criteria.andFactoryNumEqualTo(diagnosisPlanWithBLOBs.getFactoryNum());
            }
            if(diagnosisPlanWithBLOBs.getBuilding() != null && !diagnosisPlanWithBLOBs.getBuilding().isEmpty()){
                criteria.andBuildingEqualTo(diagnosisPlanWithBLOBs.getBuilding());
            }
            if(diagnosisPlanWithBLOBs.getEtB() != null && !diagnosisPlanWithBLOBs.getEtB().isEmpty()){
                criteria.andEtBEqualTo(diagnosisPlanWithBLOBs.getEtB());
            }
            if(diagnosisPlanWithBLOBs.getOperator() != null && !diagnosisPlanWithBLOBs.getOperator().isEmpty()){
                criteria.andOperatorEqualTo(diagnosisPlanWithBLOBs.getOperator());
            }
            if(diagnosisPlanWithBLOBs.getProfessor() != null && !diagnosisPlanWithBLOBs.getProfessor().isEmpty()){
                criteria.andProfessorEqualTo(diagnosisPlanWithBLOBs.getProfessor());
            }
            if(diagnosisPlanWithBLOBs.getSupervisor() != null && !diagnosisPlanWithBLOBs.getSupervisor().isEmpty()){
                criteria.andSupervisorEqualTo(diagnosisPlanWithBLOBs.getSupervisor());
            }
            if(diagnosisPlanWithBLOBs.getRemark() != null && !diagnosisPlanWithBLOBs.getRemark().isEmpty()){
                criteria.andRemarkLike(diagnosisPlanWithBLOBs.getRemark());
            }
            if(diagnosisPlanWithBLOBs.getIsPass() != null && !diagnosisPlanWithBLOBs.getIsPass().toString().isEmpty()){
                criteria.andIsPassEqualTo(diagnosisPlanWithBLOBs.getIsPass());
            }
            if(diagnosisPlanWithBLOBs.getUpassReason() != null && !diagnosisPlanWithBLOBs.getUpassReason().isEmpty()){
                criteria.andUpassReasonLike(diagnosisPlanWithBLOBs.getUpassReason());
            }
            if(diagnosisPlanWithBLOBs.getIsPass1() != null && !diagnosisPlanWithBLOBs.getIsPass1().toString().isEmpty()){
                criteria.andIsPassEqualTo(diagnosisPlanWithBLOBs.getIsPass1());
            }
            List<DiagnosisPlanWithBLOBs> selective = diagnosisPlanService.findPlanSelective(diagnosisPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
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
    @RequestMapping(value = "/diagnosisSelectByProfessor/show",method = RequestMethod.POST)
    public Response findPlanByProfessor(@RequestBody @Valid DiagnosisPlanModel planModel,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("育种实施档案(根据条件)查询失败");
        }else {
            //将planModel部分变量拆分传递给对象diagnosisPlanWithBLOBs
            DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs = new DiagnosisPlanWithBLOBs();
            diagnosisPlanWithBLOBs.setId(planModel.getId());
            diagnosisPlanWithBLOBs.setGmtCreate(planModel.getGmtCreate());
            diagnosisPlanWithBLOBs.setGmtModified(planModel.getGmtModified());
            diagnosisPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            diagnosisPlanWithBLOBs.setFactoryNum(planModel.getFactoryNum());
            diagnosisPlanWithBLOBs.setBuilding(planModel.getBuilding());
            diagnosisPlanWithBLOBs.setEtB(planModel.getEtB());
            diagnosisPlanWithBLOBs.setDiagnosisT(planModel.getDiagnosisT());
            diagnosisPlanWithBLOBs.setOperator(planModel.getOperator());
            diagnosisPlanWithBLOBs.setProfessor(planModel.getProfessor());
            diagnosisPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            diagnosisPlanWithBLOBs.setRemark(planModel.getRemark());
            diagnosisPlanWithBLOBs.setIsPass(planModel.getIsPass());
            diagnosisPlanWithBLOBs.setUpassReason(planModel.getUpassReason());
            diagnosisPlanWithBLOBs.setIsPass1(planModel.getIsPass1());
            diagnosisPlanWithBLOBs.setDiagnosisC(planModel.getDiagnosisC());
            diagnosisPlanWithBLOBs.setDiagnosisM(planModel.getDiagnosisM());
            diagnosisPlanWithBLOBs.setDrugQ(planModel.getDrugQ());

            //将planModel部分变量拆分传递给对象otherTime
            OtherTime otherTime = new OtherTime();
            otherTime.setSearch_string(planModel.getSearch_string());
            otherTime.setS_breedingT(planModel.getS_breedingT());
            System.out.println(otherTime.getS_breedingT());
            otherTime.setS_gestationT(planModel.getS_gestationT());
            System.out.println(otherTime.getS_gestationT());
            otherTime.setS_prenatalIT(planModel.getS_prenatalIT());
            System.out.println(otherTime.getS_prenatalIT());
            otherTime.setS_cubT(planModel.getS_cubT());
            System.out.println(otherTime.getS_cubT());
            otherTime.setS_diagnosisT(planModel.getS_diagnosisT());
            otherTime.setS_nutritionT(planModel.getS_nutritionT());
            otherTime.setS_gmtCreate1(planModel.getS_gmtCreate1());
            otherTime.setS_gmtCreate2(planModel.getS_gmtCreate2());
            otherTime.setS_gmtModified1(planModel.getS_gmtModified1());
            otherTime.setS_gmtModified2(planModel.getS_gmtModified2());
            otherTime.setS_breedingT1(planModel.getS_breedingT1());
            otherTime.setS_breedingT2(planModel.getS_breedingT2());
            otherTime.setS_prenatalIT1(planModel.getS_prenatalIT1());
            otherTime.setS_prenatalIT2(planModel.getS_prenatalIT2());
            otherTime.setS_gestationT1(planModel.getS_gestationT1());
            otherTime.setS_gestationT2(planModel.getS_gestationT2());
            otherTime.setS_cubT1(planModel.getS_cubT1());
            otherTime.setS_cubT2(planModel.getS_cubT2());
            otherTime.setS_diagnosisT1(planModel.getS_diagnosisT1());
            otherTime.setS_diagnosisT2(planModel.getS_diagnosisT2());
            otherTime.setS_nutritionT1(planModel.getS_nutritionT1());
            otherTime.setS_nutritionT2(planModel.getS_nutritionT2());
            otherTime.setDownloadPath(planModel.getDownloadPath());
            otherTime.setPage(planModel.getPage());
            otherTime.setSize(planModel.getSize());

            Byte notPassed = 0;
            DiagnosisPlanExample diagnosisPlanExample = new DiagnosisPlanExample();
            DiagnosisPlanExample.Criteria criteria = diagnosisPlanExample.createCriteria();

            if(diagnosisPlanWithBLOBs.getId() != null && !diagnosisPlanWithBLOBs.getId().toString().isEmpty()){
                criteria.andIdEqualTo(diagnosisPlanWithBLOBs.getId());
            }
            if(diagnosisPlanWithBLOBs.getProfessor() != null && !diagnosisPlanWithBLOBs.getProfessor().isEmpty()){
                criteria.andProfessorEqualTo(diagnosisPlanWithBLOBs.getProfessor());
            }
            if(diagnosisPlanWithBLOBs.getUpassReason() != null && !diagnosisPlanWithBLOBs.getUpassReason().isEmpty()){
                criteria.andUpassReasonLike(diagnosisPlanWithBLOBs.getUpassReason());
            }
            criteria.andIsPassEqualTo(notPassed);
            List<DiagnosisPlanWithBLOBs> select = diagnosisPlanService.findPlanSelective(diagnosisPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
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
    @RequestMapping(value = "/diagnosisSelectBySupervisor/show",method = RequestMethod.POST)
    public Response findPlanSelectBySupervisor(@RequestBody @Valid DiagnosisPlanModel planModel,
                                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("育种实施档案(根据条件)查询失败");
        }else {
            //将planModel部分变量拆分传递给对象diagnosisPlanWithBLOBs
            DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs = new DiagnosisPlanWithBLOBs();
            diagnosisPlanWithBLOBs.setId(planModel.getId());
            diagnosisPlanWithBLOBs.setGmtCreate(planModel.getGmtCreate());
            diagnosisPlanWithBLOBs.setGmtModified(planModel.getGmtModified());
            diagnosisPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            diagnosisPlanWithBLOBs.setFactoryNum(planModel.getFactoryNum());
            diagnosisPlanWithBLOBs.setBuilding(planModel.getBuilding());
            diagnosisPlanWithBLOBs.setEtB(planModel.getEtB());
            diagnosisPlanWithBLOBs.setDiagnosisT(planModel.getDiagnosisT());
            diagnosisPlanWithBLOBs.setOperator(planModel.getOperator());
            diagnosisPlanWithBLOBs.setProfessor(planModel.getProfessor());
            diagnosisPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            diagnosisPlanWithBLOBs.setRemark(planModel.getRemark());
            diagnosisPlanWithBLOBs.setIsPass(planModel.getIsPass());
            diagnosisPlanWithBLOBs.setUpassReason(planModel.getUpassReason());
            diagnosisPlanWithBLOBs.setIsPass1(planModel.getIsPass1());
            diagnosisPlanWithBLOBs.setDiagnosisC(planModel.getDiagnosisC());
            diagnosisPlanWithBLOBs.setDiagnosisM(planModel.getDiagnosisM());
            diagnosisPlanWithBLOBs.setDrugQ(planModel.getDrugQ());

            //将planModel部分变量拆分传递给对象otherTime
            OtherTime otherTime = new OtherTime();
            otherTime.setSearch_string(planModel.getSearch_string());
            otherTime.setS_breedingT(planModel.getS_breedingT());
            System.out.println(otherTime.getS_breedingT());
            otherTime.setS_gestationT(planModel.getS_gestationT());
            System.out.println(otherTime.getS_gestationT());
            otherTime.setS_prenatalIT(planModel.getS_prenatalIT());
            System.out.println(otherTime.getS_prenatalIT());
            otherTime.setS_cubT(planModel.getS_cubT());
            System.out.println(otherTime.getS_cubT());
            otherTime.setS_diagnosisT(planModel.getS_diagnosisT());
            otherTime.setS_nutritionT(planModel.getS_nutritionT());
            otherTime.setS_gmtCreate1(planModel.getS_gmtCreate1());
            otherTime.setS_gmtCreate2(planModel.getS_gmtCreate2());
            otherTime.setS_gmtModified1(planModel.getS_gmtModified1());
            otherTime.setS_gmtModified2(planModel.getS_gmtModified2());
            otherTime.setS_breedingT1(planModel.getS_breedingT1());
            otherTime.setS_breedingT2(planModel.getS_breedingT2());
            otherTime.setS_prenatalIT1(planModel.getS_prenatalIT1());
            otherTime.setS_prenatalIT2(planModel.getS_prenatalIT2());
            otherTime.setS_gestationT1(planModel.getS_gestationT1());
            otherTime.setS_gestationT2(planModel.getS_gestationT2());
            otherTime.setS_cubT1(planModel.getS_cubT1());
            otherTime.setS_cubT2(planModel.getS_cubT2());
            otherTime.setS_diagnosisT1(planModel.getS_diagnosisT1());
            otherTime.setS_diagnosisT2(planModel.getS_diagnosisT2());
            otherTime.setS_nutritionT1(planModel.getS_nutritionT1());
            otherTime.setS_nutritionT2(planModel.getS_nutritionT2());
            otherTime.setDownloadPath(planModel.getDownloadPath());
            otherTime.setPage(planModel.getPage());
            otherTime.setSize(planModel.getSize());

            Byte notPassed1 = 0;
            DiagnosisPlanExample diagnosisPlanExample = new DiagnosisPlanExample();
            DiagnosisPlanExample.Criteria criteria = diagnosisPlanExample.createCriteria();

            if(diagnosisPlanWithBLOBs.getId() != null && !diagnosisPlanWithBLOBs.getId().toString().isEmpty()){
                criteria.andIdEqualTo(diagnosisPlanWithBLOBs.getId());
            }
            criteria.andIsPass1EqualTo(notPassed1);
            List<DiagnosisPlanWithBLOBs> select = diagnosisPlanService.findPlanSelective(diagnosisPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan",select);
            response.setData(data);
            return response;
        }
    }
}
