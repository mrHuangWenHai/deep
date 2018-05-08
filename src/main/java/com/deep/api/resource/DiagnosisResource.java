package com.deep.api.resource;

import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.request.DiagnosisPlanModel;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.DiagnosisPlanExample;
import com.deep.domain.model.DiagnosisPlanWithBLOBs;
import com.deep.domain.model.OtherTime;
import com.deep.domain.service.DiagnosisPlanService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestController
@RequestMapping(value = "diagnosis/")
public class DiagnosisResource {

    private final Logger logger = LoggerFactory.getLogger(AgentResource.class);

    @Resource
    private DiagnosisPlanService diagnosisPlanService;

    /**
     * 添加的接口：/diagnosisInsert
     * 接收参数：整个表单信息（所有参数必填）
     * 参数类型为：Long factoryNum;Date diagnosisT;String building;String etB;String operator;String remark;String diagnosisC;String diagnosisM;String drugQ;
     */
    @PostMapping(value = "/insert")
    public Response addPlan(@RequestBody @Valid DiagnosisPlanModel planModel, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("诊疗实施档案录入失败");
        } else {
            //将planModel部分变量拆分传递给对象insert
            DiagnosisPlanWithBLOBs insert = new DiagnosisPlanWithBLOBs();
            insert.setFactoryNum(planModel.getFactoryNum());
            insert.setBuilding(planModel.getBuilding());
            insert.setEtB(planModel.getEtB());
            insert.setOperator(planModel.getOperator());
            insert.setDiagnosisC(planModel.getDiagnosisC());
            insert.setDiagnosisM(planModel.getDiagnosisM());
            insert.setDrugQ(planModel.getDrugQ());

            //将planModel部分变量拆分传递给对象otherTime
            OtherTime otherTime = new OtherTime();
            otherTime.setS_diagnosisT(planModel.getS_diagnosisT());

            Byte zero = 0;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            Date diagnosisT = !otherTime.getS_diagnosisT().isEmpty() ? formatter.parse(otherTime.getS_diagnosisT()) : new Date();
            insert.setGmtCreate(new Date());
            insert.setDiagnosisT(diagnosisT);
            insert.setIsPass(zero);
            insert.setIsPass1(zero);
            int addID = diagnosisPlanService.addPlan(insert);
            if (addID <= 0) {
                return Responses.errorResponse("插入错误");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan", addID);
            response.setData(data);
            return response;
        }
    }

    /**
     * 按照主键删除的接口
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Response dropPlan(@PathVariable("id") String id) {
        logger.info("invoke findOne{}, url is agent/{id}", id);
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("删除错误");
        } else {
            DiagnosisPlanWithBLOBs delete = new DiagnosisPlanWithBLOBs();
            int deleteID = diagnosisPlanService.dropPlan(uid);
            if (deleteID <= 0) {
                return Responses.errorResponse("删除失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan", deleteID);
            response.setData(data);
            return response;
        }
    }

    /**
     * 操作员使用按主键修改的接口：/operator
     * 操作员使用按主键修改的方法名：changePlanByOperator()
     * 操作员使用接收参数：整个表单信息（整型id必填，各参数选填）
     *
     * @param planModel
     * @param bindingResult
     * @return
     * @throws ParseException
     */
    @PostMapping(value = "/operator")
    public Response changePlanByOperator(@RequestBody @Valid DiagnosisPlanModel planModel, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("诊疗实施档案更新(操作员页面)失败");
        } else {
            //将planModel部分变量拆分传递给对象operator
            DiagnosisPlanWithBLOBs operator = new DiagnosisPlanWithBLOBs();
            operator.setId(planModel.getId());
            operator.setFactoryNum(planModel.getFactoryNum());
            operator.setBuilding(planModel.getBuilding());
            operator.setEtB(planModel.getEtB());
            operator.setOperator(planModel.getOperator());
            operator.setDiagnosisC(planModel.getDiagnosisC());
            operator.setDiagnosisM(planModel.getDiagnosisM());
            operator.setDrugQ(planModel.getDrugQ());

            //将planModel部分变量拆分传递给对象otherTime
            OtherTime otherTime = new OtherTime();
            otherTime.setS_diagnosisT(planModel.getS_diagnosisT());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            Date diagnosisT = !otherTime.getS_diagnosisT().isEmpty() ? formatter.parse(otherTime.getS_diagnosisT()) : new Date();
            operator.setDiagnosisT(diagnosisT);

            int updateID = diagnosisPlanService.changePlanSelective(operator);
            if (updateID <= 0) {
                return Responses.errorResponse("修改失败");
            }

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan", updateID);
            response.setData(data);
            return response;
        }
    }

    /**
     * 监督者使用按主键修改的接口：/diagnosisUpdateByProfessor
     * 监督者使用按主键修改的方法名：changePlanByProfessor()
     * 监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
     *
     * @param professor
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/professor")
    public Response changePlanByProfessor(@RequestBody @Valid DiagnosisPlanWithBLOBs professor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("诊疗实施档案更新(专家页面)失败");
        } else {
            professor.setGmtModified(new Date());
            if (professor.getIsPass() == 1) {
                professor.setUpassReason("操作员已经修改档案并通过技术审核");
            }
            int updateID = diagnosisPlanService.changePlanSelective(professor);
            if (updateID <= 0) {
                return Responses.errorResponse("错误");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan", updateID);
            response.setData(data);
            return response;
        }
    }

    /**
     * 监督者使用按主键修改的接口：/diagnosisUpdateBySupervisor
     * 监督者使用按主键修改的方法名：changePlanBySupervisor()
     * 监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
     *
     * @param supervisor
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/supervisor")
    public Response changePlanBySupervisor(@RequestBody @Valid DiagnosisPlanWithBLOBs supervisor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("诊疗实施档案更新(监督页面)失败");
        } else {
            supervisor.setGmtSupervised(new Date());
            int updateID = diagnosisPlanService.changePlanSelective(supervisor);
            if (updateID <= 0) {
                return Responses.errorResponse("监督失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan", updateID);
            response.setData(data);
            return response;
        }
    }

    /**
     * 按主键查询的接口：/select
     * 按主键查询的方法名：findPlanById()
     * 接收参数：整型的主键号（保留接口查询，前端不调用此接口）
     *
     * @return
     */
    @GetMapping(value = "/id/{id}")
    public Response findPlanById(@PathVariable("id") String id) {
        int selectID = StringToLongUtil.stringToInt(id);
        if (selectID == -1) {
            return Responses.errorResponse("error");
        }
        //查询语句的写法：一定要在声明对象时把值直接赋进去
        DiagnosisPlanWithBLOBs selectById = diagnosisPlanService.findPlanById(selectID);
        if (selectById == null) {
            return Responses.errorResponse("查询失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("diagnosis_plan", selectById);
        response.setData(data);
        return response;
    }

    /**
     * 按条件查询接口：/diagnosisSelective
     * 按条件查询方法名：findPlanSelective()
     * 接收的参数：前端的各参数，以及两个("s_diagnosisT1")("s_diagnosisT2")时间字符串（所有参数可以选填）
     *
     * @param planModel
     * @param bindingResult
     * @return
     * @throws ParseException
     */
    @PostMapping(value = "/select")
    public Response findPlanSelective(@RequestBody @Valid DiagnosisPlanModel planModel, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("诊疗实施档案(根据条件)查询失败");
        } else {
            //将planModel部分变量拆分传递给对象diagnosisPlanWithBLOBs
            DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs = new DiagnosisPlanWithBLOBs();
            diagnosisPlanWithBLOBs.setId(planModel.getId());
            diagnosisPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            diagnosisPlanWithBLOBs.setFactoryNum(planModel.getFactoryNum());
            diagnosisPlanWithBLOBs.setBuilding(planModel.getBuilding());
            diagnosisPlanWithBLOBs.setEtB(planModel.getEtB());
            diagnosisPlanWithBLOBs.setOperator(planModel.getOperator());
            diagnosisPlanWithBLOBs.setProfessor(planModel.getProfessor());
            diagnosisPlanWithBLOBs.setRemark(planModel.getRemark());
            diagnosisPlanWithBLOBs.setIsPass(planModel.getIsPass());
            diagnosisPlanWithBLOBs.setUpassReason(planModel.getUpassReason());
            diagnosisPlanWithBLOBs.setIsPass1(planModel.getIsPass1());

            //将planModel部分变量拆分传递给对象otherTime
            OtherTime otherTime = new OtherTime();
            otherTime.setS_diagnosisT1(planModel.getS_diagnosisT1());
            otherTime.setS_diagnosisT2(planModel.getS_diagnosisT2());
            otherTime.setPage(planModel.getPage());
            otherTime.setSize(planModel.getSize());

            Date diagnosisT1 = null;
            Date diagnosisT2 = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            DiagnosisPlanExample diagnosisPlanExample = new DiagnosisPlanExample();
            DiagnosisPlanExample.Criteria criteria = diagnosisPlanExample.createCriteria();

            if (otherTime.getS_diagnosisT1() != null && !otherTime.getS_diagnosisT1().isEmpty() && otherTime.getS_diagnosisT2() != null && !otherTime.getS_diagnosisT2().isEmpty()) {
                diagnosisT1 = formatter.parse(otherTime.getS_diagnosisT1());
                diagnosisT2 = formatter.parse(otherTime.getS_diagnosisT2());
            }
            if (diagnosisT1 != null && diagnosisT2 != null) {
                criteria.andDiagnosisTBetween(diagnosisT1, diagnosisT2);
            }

            if (diagnosisPlanWithBLOBs.getId() != null && !diagnosisPlanWithBLOBs.getId().toString().isEmpty()) {
                criteria.andIdEqualTo(diagnosisPlanWithBLOBs.getId());
            }
            if (diagnosisPlanWithBLOBs.getFactoryNum() != null && !diagnosisPlanWithBLOBs.getFactoryNum().toString().isEmpty()) {
                criteria.andFactoryNumEqualTo(diagnosisPlanWithBLOBs.getFactoryNum());
            }
            if (diagnosisPlanWithBLOBs.getBuilding() != null && !diagnosisPlanWithBLOBs.getBuilding().isEmpty()) {
                criteria.andBuildingEqualTo(diagnosisPlanWithBLOBs.getBuilding());
            }
            if (diagnosisPlanWithBLOBs.getEtB() != null && !diagnosisPlanWithBLOBs.getEtB().isEmpty()) {
                criteria.andEtBEqualTo(diagnosisPlanWithBLOBs.getEtB());
            }
            if (diagnosisPlanWithBLOBs.getOperator() != null && !diagnosisPlanWithBLOBs.getOperator().isEmpty()) {
                criteria.andOperatorEqualTo(diagnosisPlanWithBLOBs.getOperator());
            }
            if (diagnosisPlanWithBLOBs.getProfessor() != null && !diagnosisPlanWithBLOBs.getProfessor().isEmpty()) {
                criteria.andProfessorEqualTo(diagnosisPlanWithBLOBs.getProfessor());
            }
            if (diagnosisPlanWithBLOBs.getSupervisor() != null && !diagnosisPlanWithBLOBs.getSupervisor().isEmpty()) {
                criteria.andSupervisorEqualTo(diagnosisPlanWithBLOBs.getSupervisor());
            }
            if (diagnosisPlanWithBLOBs.getRemark() != null && !diagnosisPlanWithBLOBs.getRemark().isEmpty()) {
                criteria.andRemarkLike(diagnosisPlanWithBLOBs.getRemark());
            }
            if (diagnosisPlanWithBLOBs.getIsPass() != null && !diagnosisPlanWithBLOBs.getIsPass().toString().isEmpty()) {
                criteria.andIsPassEqualTo(diagnosisPlanWithBLOBs.getIsPass());
            }
            if (diagnosisPlanWithBLOBs.getUpassReason() != null && !diagnosisPlanWithBLOBs.getUpassReason().isEmpty()) {
                criteria.andUpassReasonLike(diagnosisPlanWithBLOBs.getUpassReason());
            }
            if (diagnosisPlanWithBLOBs.getIsPass1() != null && !diagnosisPlanWithBLOBs.getIsPass1().toString().isEmpty()) {
                criteria.andIsPassEqualTo(diagnosisPlanWithBLOBs.getIsPass1());
            }
            List<DiagnosisPlanWithBLOBs> selective = diagnosisPlanService.findPlanSelective(diagnosisPlanExample, new RowBounds(otherTime.getPage(), otherTime.getSize()));
            if (selective == null) {
                return Responses.errorResponse("错误");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan", selective);
            data.put("size", selective.size());
            response.setData(data);
            return response;
        }
    }

    /**
     * 供技术审核查询信息:/diagnosisSelectByProfessor
     * 供技术审核查询方法名：findPlanSelectByProfessor()
     * 接收的参数：前端的各参数，（所有参数可以选填）
     *
     * @param planModel
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/professor/select")
    public Response findPlanByProfessor(@RequestBody @Valid DiagnosisPlanModel planModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("育种实施档案(根据条件)查询失败");
        } else {
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

            if (diagnosisPlanWithBLOBs.getId() != null && !diagnosisPlanWithBLOBs.getId().toString().isEmpty()) {
                criteria.andIdEqualTo(diagnosisPlanWithBLOBs.getId());
            }
            if (diagnosisPlanWithBLOBs.getProfessor() != null && !diagnosisPlanWithBLOBs.getProfessor().isEmpty()) {
                criteria.andProfessorEqualTo(diagnosisPlanWithBLOBs.getProfessor());
            }
            if (diagnosisPlanWithBLOBs.getUpassReason() != null && !diagnosisPlanWithBLOBs.getUpassReason().isEmpty()) {
                criteria.andUpassReasonLike(diagnosisPlanWithBLOBs.getUpassReason());
            }
            criteria.andIsPassEqualTo(notPassed);
            List<DiagnosisPlanWithBLOBs> select = diagnosisPlanService.findPlanSelective(diagnosisPlanExample, new RowBounds(otherTime.getPage(), otherTime.getSize()));
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan", select);
            response.setData(data);
            return response;
        }
    }

    /**
     * 供监督者查询信息:/diagnosisSelectBySupervisor
     * 供监督者查询方法名：findPlanSelectBySupervisor()
     * 接收的参数：前端的各参数，（所有参数可以选填）
     *
     * @param planModel
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/supervisor/select")
    public Response findPlanSelectBySupervisor(@RequestBody @Valid DiagnosisPlanModel planModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("育种实施档案(根据条件)查询失败");
        } else {
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

            if (diagnosisPlanWithBLOBs.getId() != null && !diagnosisPlanWithBLOBs.getId().toString().isEmpty()) {
                criteria.andIdEqualTo(diagnosisPlanWithBLOBs.getId());
            }
            criteria.andIsPass1EqualTo(notPassed1);
            List<DiagnosisPlanWithBLOBs> select = diagnosisPlanService.findPlanSelective(diagnosisPlanExample, new RowBounds(otherTime.getPage(), otherTime.getSize()));
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("diagnosis_plan", select);
            response.setData(data);
            return response;
        }
    }
}
