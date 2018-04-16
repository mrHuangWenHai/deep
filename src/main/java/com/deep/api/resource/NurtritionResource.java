package com.deep.api.resource;

import com.deep.api.request.NutritionPlanModel;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.NutritionPlanExample;
import com.deep.domain.model.NutritionPlanWithBLOBs;
import com.deep.domain.model.OtherTime;
import com.deep.domain.service.NutritionPlanService;
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
 * date: 2018/2/21  19:34
 */
@RestController
@RequestMapping(value = "nutrition/")
public class NurtritionResource {

    @Resource
    private NutritionPlanService nutritionPlanService;

    @ResponseBody
    @RequestMapping(value = "/nutritionPlan",method = RequestMethod.GET)
    public String helloNutrition() {
        return "Hello NutritionPlan!";
    }

//    按主键删除的接口：/nutritionInsert
//    按主键删除的方法名：addPlan()
//    接收参数：整个表单信息（所有参数必填）
//    参数类型为：
//    Long factoryNum; String building;Date nutritionT;Long quantity;String average;String period;String water;String operator;String remark;
//    String materialA;String materialM;String materialO;String materialWM;String materialWO;String roughageP;String roughageD;String roughageWP;String roughageWD;String roughageWO;String pickingM;String pickingR;String pickingO;
    @RequestMapping(value = "/nutritionInsert",method = RequestMethod.GET)
    public String addPlan(){
        return "NutritionInsert";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionInsert/show",method = RequestMethod.POST)
    public Response addPlan(@RequestBody @Valid NutritionPlanModel planModel,
                            BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案录入失败");
        }else {
            //将planModel部分变量拆分传递给对象insert
            NutritionPlanWithBLOBs insert = new NutritionPlanWithBLOBs();
            insert.setId(planModel.getId());
            insert.setGmtCreate(planModel.getGmtCreate());
            insert.setGmtModified(planModel.getGmtModified());
            insert.setSupervisor(planModel.getSupervisor());
            insert.setFactoryNum(planModel.getFactoryNum());
            insert.setBuilding(planModel.getBuilding());
            insert.setNutritionT(planModel.getNutritionT());
            insert.setQuantity(planModel.getQuantity());
            insert.setAverage(planModel.getAverage());
            insert.setPeriod(planModel.getPeriod());
            insert.setWater(planModel.getWater());
            insert.setOperator(planModel.getOperator());
            insert.setProfessor(planModel.getProfessor());
            insert.setSupervisor(planModel.getSupervisor());
            insert.setRemark(planModel.getRemark());
            insert.setIsPass(planModel.getIsPass());
            insert.setUpassReason(planModel.getUpassReason());
            insert.setIsPass1(planModel.getIsPass1());
            insert.setMaterialA(planModel.getMaterialA());
            insert.setMaterialM(planModel.getMaterialM());
            insert.setMaterialO(planModel.getMaterialO());
            insert.setMaterialWM(planModel.getMaterialWM());
            insert.setMaterialWO(planModel.getMaterialWO());
            insert.setRoughageP(planModel.getRoughageP());
            insert.setRoughageD(planModel.getRoughageD());
            insert.setRoughageWP(planModel.getRoughageWP());
            insert.setRoughageWD(planModel.getRoughageWD());
            insert.setRoughageWO(planModel.getRoughageWO());
            insert.setPickingM(planModel.getPickingM());
            insert.setPickingR(planModel.getPickingR());
            insert.setPickingO(planModel.getPickingO());

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
            Date nutritionT = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
            if (!otherTime.getS_nutritionT().isEmpty()) {
                nutritionT =  formatter.parse(otherTime.getS_nutritionT());
            }
            insert.setGmtCreate(new Date());
            insert.setNutritionT(nutritionT);
            insert.setIsPass(zero);
            insert.setIsPass1(zero);
            nutritionPlanService.addPlan(insert);

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",insert);
            response.setData(data);
            return response;
        }
    }

//    按主键删除的接口：/nutritionDeleteById
//    按主键删除的方法名：dropPlan()
//    接收参数：整型id，根据主键号删除
    @RequestMapping(value = "/nutritionDeleteById",method = RequestMethod.GET)
    public String dropPlan(){
        return "NutritionDeleteById";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionDeleteById/show",method = RequestMethod.DELETE)
    public Response dropPlan(@RequestBody @Valid NutritionPlanWithBLOBs nutritionPlanWithBLOBs,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案删除失败");
        }else {
            NutritionPlanWithBLOBs delete = new NutritionPlanWithBLOBs();
            nutritionPlanService.dropPlan(nutritionPlanWithBLOBs.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",delete);
            response.setData(data);
            return response;
        }
    }

//    专家使用按主键修改的接口：/nutritionUpdateByOperator
//    专家使用按主键修改的方法名：changePlanByOperator()
//    专家使用接收参数：整个表单类型（整型id必填，各参数选填）
    @RequestMapping(value = "/nutritionUpdateByOperator",method = RequestMethod.GET)
    public String changePlanByOperator(){
        return "NutritionUpdateByOperator";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionUpdateByOperator/show",method = RequestMethod.POST)
    public Response changePlanByOperator(@RequestBody @Valid NutritionPlanModel planModel,
                                         BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案更新(操作员页面)失败");
        }else {
            //将planModel部分变量拆分传递给对象operator
            NutritionPlanWithBLOBs operator = new NutritionPlanWithBLOBs();
            operator.setId(planModel.getId());
            operator.setGmtCreate(planModel.getGmtCreate());
            operator.setGmtModified(planModel.getGmtModified());
            operator.setSupervisor(planModel.getSupervisor());
            operator.setFactoryNum(planModel.getFactoryNum());
            operator.setBuilding(planModel.getBuilding());
            operator.setNutritionT(planModel.getNutritionT());
            operator.setQuantity(planModel.getQuantity());
            operator.setAverage(planModel.getAverage());
            operator.setPeriod(planModel.getPeriod());
            operator.setWater(planModel.getWater());
            operator.setOperator(planModel.getOperator());
            operator.setProfessor(planModel.getProfessor());
            operator.setSupervisor(planModel.getSupervisor());
            operator.setRemark(planModel.getRemark());
            operator.setIsPass(planModel.getIsPass());
            operator.setUpassReason(planModel.getUpassReason());
            operator.setIsPass1(planModel.getIsPass1());
            operator.setMaterialA(planModel.getMaterialA());
            operator.setMaterialM(planModel.getMaterialM());
            operator.setMaterialO(planModel.getMaterialO());
            operator.setMaterialWM(planModel.getMaterialWM());
            operator.setMaterialWO(planModel.getMaterialWO());
            operator.setRoughageP(planModel.getRoughageP());
            operator.setRoughageD(planModel.getRoughageD());
            operator.setRoughageWP(planModel.getRoughageWP());
            operator.setRoughageWD(planModel.getRoughageWD());
            operator.setRoughageWO(planModel.getRoughageWO());
            operator.setPickingM(planModel.getPickingM());
            operator.setPickingR(planModel.getPickingR());
            operator.setPickingO(planModel.getPickingO());

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

            Date nutritionT = null;
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
            if (!otherTime.getS_nutritionT().isEmpty()){
                nutritionT =  formatter.parse(otherTime.getS_nutritionT());
            }
            operator.setNutritionT(nutritionT);
            nutritionPlanService.changePlanSelective(operator);

            NutritionPlanWithBLOBs selectById = nutritionPlanService.findPlanById(operator.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    专家使用按主键修改的接口：/nutritionUpdateByProfessor
//    专家使用按主键修改的方法名：changePlanByProfessor()
//    专家使用接收参数：整个表单类型（整型id必填，各参数选填）
    @RequestMapping(value = "/nutritionUpdateByProfessor",method = RequestMethod.GET)
    public String changePlanByProfessor(){
        return "NutritionUpdateByProfessor";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionUpdateByProfessor/show",method = RequestMethod.POST)
    public Response changePlanByProfessor(@RequestBody @Valid NutritionPlanWithBLOBs professor,
                                          BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案更新(专家页面)失败");
        }else {
            professor.setGmtModified(new Date());
            if (professor.getIsPass() == 1){
                professor.setUpassReason("操作员已经修改档案并通过技术审核");
            }
            nutritionPlanService.changePlanSelective(professor);

            NutritionPlanWithBLOBs selectById = nutritionPlanService.findPlanById(professor.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",selectById);
            response.setData(data);
            return response;
        }
    }
//    监督者使用按主键修改的接口：/nutritionUpdateBySupervisor
//    监督者使用按主键修改的方法名：changePlanBySupervisor()
//    监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
    @RequestMapping(value = "/nutritionUpdateBySupervisor",method = RequestMethod.GET)
    public String changePlanBySupervisor(){
        return "NutritionUpdateBySupervisor";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionUpdateBySupervisor/show",method = RequestMethod.POST)
    public Response changePlanBySupervisor(@RequestBody @Valid NutritionPlanWithBLOBs supervisor,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案更新(监督页面)失败");
        }else {
            supervisor.setGmtSupervised(new Date());
            nutritionPlanService.changePlanSelective(supervisor);

            NutritionPlanWithBLOBs selectById = nutritionPlanService.findPlanById(supervisor.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    按主键查询的接口：/nutritionSelectById
//    按主键查询的方法名：findPlanById()
//    接收参数：整型的主键号（保留接口查询，前端不调用此接口）
    @RequestMapping(value = "/nutritionSelectById",method = RequestMethod.GET)
    public String findPlanById(){
        return "NutritionSelectById";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionSelectById/show",method = RequestMethod.GET)
    public Response findPlanById(@Valid NutritionPlanWithBLOBs nutritionPlanWithBLOBs,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案(根据条件)查询失败");
        } else {
            //查询语句的写法：一定要在声明对象时把值直接赋进去
            NutritionPlanWithBLOBs selectById = nutritionPlanService.findPlanById(nutritionPlanWithBLOBs.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    按条件查询接口：/nutritionSelective
//    按条件查询方法名：findPlanSelective()
//    接收的参数：前端的各参数，以及两个("s_nutritionT1")("s_nutritionT2")时间字符串（所有参数可以选填）
    @RequestMapping(value = "/nutritionSelective",method = RequestMethod.GET)
    public String findPlanSelective(){
        return "NutritionSelective";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionSelective/show",method = RequestMethod.POST)
    public Response findPlanSelective(@RequestBody @Valid NutritionPlanModel planModel,
                                      BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案(根据条件)查询失败");
        }else {
            //将planModel部分变量拆分传递给对象nutritionPlanWithBLOBs
            NutritionPlanWithBLOBs nutritionPlanWithBLOBs = new NutritionPlanWithBLOBs();
            nutritionPlanWithBLOBs.setId(planModel.getId());
            nutritionPlanWithBLOBs.setGmtCreate(planModel.getGmtCreate());
            nutritionPlanWithBLOBs.setGmtModified(planModel.getGmtModified());
            nutritionPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            nutritionPlanWithBLOBs.setFactoryNum(planModel.getFactoryNum());
            nutritionPlanWithBLOBs.setBuilding(planModel.getBuilding());
            nutritionPlanWithBLOBs.setNutritionT(planModel.getNutritionT());
            nutritionPlanWithBLOBs.setQuantity(planModel.getQuantity());
            nutritionPlanWithBLOBs.setAverage(planModel.getAverage());
            nutritionPlanWithBLOBs.setPeriod(planModel.getPeriod());
            nutritionPlanWithBLOBs.setWater(planModel.getWater());
            nutritionPlanWithBLOBs.setOperator(planModel.getOperator());
            nutritionPlanWithBLOBs.setProfessor(planModel.getProfessor());
            nutritionPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            nutritionPlanWithBLOBs.setRemark(planModel.getRemark());
            nutritionPlanWithBLOBs.setIsPass(planModel.getIsPass());
            nutritionPlanWithBLOBs.setUpassReason(planModel.getUpassReason());
            nutritionPlanWithBLOBs.setIsPass1(planModel.getIsPass1());
            nutritionPlanWithBLOBs.setMaterialA(planModel.getMaterialA());
            nutritionPlanWithBLOBs.setMaterialM(planModel.getMaterialM());
            nutritionPlanWithBLOBs.setMaterialO(planModel.getMaterialO());
            nutritionPlanWithBLOBs.setMaterialWM(planModel.getMaterialWM());
            nutritionPlanWithBLOBs.setMaterialWO(planModel.getMaterialWO());
            nutritionPlanWithBLOBs.setRoughageP(planModel.getRoughageP());
            nutritionPlanWithBLOBs.setRoughageD(planModel.getRoughageD());
            nutritionPlanWithBLOBs.setRoughageWP(planModel.getRoughageWP());
            nutritionPlanWithBLOBs.setRoughageWD(planModel.getRoughageWD());
            nutritionPlanWithBLOBs.setRoughageWO(planModel.getRoughageWO());
            nutritionPlanWithBLOBs.setPickingM(planModel.getPickingM());
            nutritionPlanWithBLOBs.setPickingR(planModel.getPickingR());
            nutritionPlanWithBLOBs.setPickingO(planModel.getPickingO());

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

            Date nutritionT1 = null;
            Date nutritionT2 = null;
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
            NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
            NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();

            if (otherTime.getS_nutritionT1() != null && !otherTime.getS_nutritionT1().isEmpty() && otherTime.getS_nutritionT2() != null && !otherTime.getS_nutritionT2().isEmpty()){
                nutritionT1 =  formatter.parse(otherTime.getS_nutritionT1());
                nutritionT2 =  formatter.parse(otherTime.getS_nutritionT2());
            }
            if(nutritionT1 != null && nutritionT2 != null){
                criteria.andNutritionTBetween(nutritionT1,nutritionT2);
            }
            if(nutritionPlanWithBLOBs.getId() != null && !nutritionPlanWithBLOBs.getId().toString().isEmpty()){
                criteria.andIdEqualTo(nutritionPlanWithBLOBs.getId());
            }
            if(nutritionPlanWithBLOBs.getFactoryNum() != null && !nutritionPlanWithBLOBs.getFactoryNum().toString().isEmpty()){
                criteria.andFactoryNumEqualTo(nutritionPlanWithBLOBs.getFactoryNum());
            }
            if(nutritionPlanWithBLOBs.getBuilding() != null && !nutritionPlanWithBLOBs.getBuilding().isEmpty()){
                criteria.andBuildingEqualTo(nutritionPlanWithBLOBs.getBuilding());
            }
            if(nutritionPlanWithBLOBs.getQuantity() != null && !nutritionPlanWithBLOBs.getQuantity().toString().isEmpty()){
                criteria.andQuantityEqualTo(nutritionPlanWithBLOBs.getQuantity());
            }
            if(nutritionPlanWithBLOBs.getAverage() != null && !nutritionPlanWithBLOBs.getAverage().isEmpty()){
                criteria.andAverageGreaterThanOrEqualTo(nutritionPlanWithBLOBs.getAverage());
            }
            if (nutritionPlanWithBLOBs.getPeriod()!= null && !nutritionPlanWithBLOBs.getPeriod().isEmpty()){
                criteria.andPeriodEqualTo(nutritionPlanWithBLOBs.getPeriod());
            }
            if (nutritionPlanWithBLOBs.getWater()!= null && !nutritionPlanWithBLOBs.getWater().isEmpty()){
                criteria.andWaterEqualTo(nutritionPlanWithBLOBs.getWater());
            }
            if(nutritionPlanWithBLOBs.getOperator() != null && !nutritionPlanWithBLOBs.getOperator().isEmpty()){
                criteria.andOperatorEqualTo(nutritionPlanWithBLOBs.getOperator());
            }
            if(nutritionPlanWithBLOBs.getProfessor() != null && !nutritionPlanWithBLOBs.getProfessor().isEmpty()){
                criteria.andProfessorEqualTo(nutritionPlanWithBLOBs.getProfessor());
            }
            if(nutritionPlanWithBLOBs.getSupervisor() != null && !nutritionPlanWithBLOBs.getSupervisor().isEmpty()){
                criteria.andSupervisorEqualTo(nutritionPlanWithBLOBs.getSupervisor());
            }
            if(nutritionPlanWithBLOBs.getIsPass() != null && !nutritionPlanWithBLOBs.getIsPass().toString().isEmpty()){
                criteria.andIsPassEqualTo(nutritionPlanWithBLOBs.getIsPass());
            }
            if(nutritionPlanWithBLOBs.getUpassReason() != null && !nutritionPlanWithBLOBs.getUpassReason().isEmpty()){
                criteria.andUpassReasonLike(nutritionPlanWithBLOBs.getUpassReason());
            }
            if(nutritionPlanWithBLOBs.getIsPass1() != null && !nutritionPlanWithBLOBs.getIsPass1().toString().isEmpty()){
                criteria.andIsPass1EqualTo(nutritionPlanWithBLOBs.getIsPass1());
            }
            List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",select);
            response.setData(data);
            return response;
        }
    }

//    供技术审核查询信息: /nutritionSelectByProfessor
//    供技术审核查询方法名：findPlanSelectBySupervisor()
//    接收的参数：前端的各参数，（所有参数可以选填）
    @RequestMapping(value = "/nutritionSelectByProfessor",method = RequestMethod.GET)
    public String findPlanSelectByProfessor(){
        return "NutritionSelectByProfessor";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionSelectByProfessor/show",method = RequestMethod.POST)
    public Response findPlanSelectByProfessor(@RequestBody @Valid NutritionPlanModel planModel,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案(根据条件)查询失败");
        }else {
            //将planModel部分变量拆分传递给对象nutritionPlanWithBLOBs
            NutritionPlanWithBLOBs nutritionPlanWithBLOBs = new NutritionPlanWithBLOBs();
            nutritionPlanWithBLOBs.setId(planModel.getId());
            nutritionPlanWithBLOBs.setGmtCreate(planModel.getGmtCreate());
            nutritionPlanWithBLOBs.setGmtModified(planModel.getGmtModified());
            nutritionPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            nutritionPlanWithBLOBs.setFactoryNum(planModel.getFactoryNum());
            nutritionPlanWithBLOBs.setBuilding(planModel.getBuilding());
            nutritionPlanWithBLOBs.setNutritionT(planModel.getNutritionT());
            nutritionPlanWithBLOBs.setQuantity(planModel.getQuantity());
            nutritionPlanWithBLOBs.setAverage(planModel.getAverage());
            nutritionPlanWithBLOBs.setPeriod(planModel.getPeriod());
            nutritionPlanWithBLOBs.setWater(planModel.getWater());
            nutritionPlanWithBLOBs.setOperator(planModel.getOperator());
            nutritionPlanWithBLOBs.setProfessor(planModel.getProfessor());
            nutritionPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            nutritionPlanWithBLOBs.setRemark(planModel.getRemark());
            nutritionPlanWithBLOBs.setIsPass(planModel.getIsPass());
            nutritionPlanWithBLOBs.setUpassReason(planModel.getUpassReason());
            nutritionPlanWithBLOBs.setIsPass1(planModel.getIsPass1());
            nutritionPlanWithBLOBs.setMaterialA(planModel.getMaterialA());
            nutritionPlanWithBLOBs.setMaterialM(planModel.getMaterialM());
            nutritionPlanWithBLOBs.setMaterialO(planModel.getMaterialO());
            nutritionPlanWithBLOBs.setMaterialWM(planModel.getMaterialWM());
            nutritionPlanWithBLOBs.setMaterialWO(planModel.getMaterialWO());
            nutritionPlanWithBLOBs.setRoughageP(planModel.getRoughageP());
            nutritionPlanWithBLOBs.setRoughageD(planModel.getRoughageD());
            nutritionPlanWithBLOBs.setRoughageWP(planModel.getRoughageWP());
            nutritionPlanWithBLOBs.setRoughageWD(planModel.getRoughageWD());
            nutritionPlanWithBLOBs.setRoughageWO(planModel.getRoughageWO());
            nutritionPlanWithBLOBs.setPickingM(planModel.getPickingM());
            nutritionPlanWithBLOBs.setPickingR(planModel.getPickingR());
            nutritionPlanWithBLOBs.setPickingO(planModel.getPickingO());

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
            NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
            NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();

            if(nutritionPlanWithBLOBs.getId() != null && !nutritionPlanWithBLOBs.getId().toString().isEmpty()){
                criteria.andIdEqualTo(nutritionPlanWithBLOBs.getId());
            }
            if(nutritionPlanWithBLOBs.getProfessor() != null && !nutritionPlanWithBLOBs.getProfessor().isEmpty()){
                criteria.andProfessorEqualTo(nutritionPlanWithBLOBs.getProfessor());
            }
            if(nutritionPlanWithBLOBs.getUpassReason() != null && !nutritionPlanWithBLOBs.getUpassReason().isEmpty()){
                criteria.andUpassReasonLike(nutritionPlanWithBLOBs.getUpassReason());
            }
            criteria.andIsPassEqualTo(notPassed);
            List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",select);
            response.setData(data);
            return response;
        }
    }

//    供监督者查询信息:nutritionSelectBySupervisor
//    供监督者查询方法名：findPlanSelectBySupervisor()
//    接收的参数：前端的各参数，（所有参数可以选填）
    @RequestMapping(value = "/nutritionSelectBySupervisor",method = RequestMethod.GET)
    public String findPlanSelectBySupervisor(){
        return "NutritionSelectBySupervisor";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionSelectBySupervisor/show",method = RequestMethod.POST)
    public Response findPlanSelectBySupervisor(@RequestBody @Valid NutritionPlanModel planModel,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案(根据条件)查询失败");
        }else {
            //将planModel部分变量拆分传递给对象nutritionPlanWithBLOBs
            NutritionPlanWithBLOBs nutritionPlanWithBLOBs = new NutritionPlanWithBLOBs();
            nutritionPlanWithBLOBs.setId(planModel.getId());
            nutritionPlanWithBLOBs.setGmtCreate(planModel.getGmtCreate());
            nutritionPlanWithBLOBs.setGmtModified(planModel.getGmtModified());
            nutritionPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            nutritionPlanWithBLOBs.setFactoryNum(planModel.getFactoryNum());
            nutritionPlanWithBLOBs.setBuilding(planModel.getBuilding());
            nutritionPlanWithBLOBs.setNutritionT(planModel.getNutritionT());
            nutritionPlanWithBLOBs.setQuantity(planModel.getQuantity());
            nutritionPlanWithBLOBs.setAverage(planModel.getAverage());
            nutritionPlanWithBLOBs.setPeriod(planModel.getPeriod());
            nutritionPlanWithBLOBs.setWater(planModel.getWater());
            nutritionPlanWithBLOBs.setOperator(planModel.getOperator());
            nutritionPlanWithBLOBs.setProfessor(planModel.getProfessor());
            nutritionPlanWithBLOBs.setSupervisor(planModel.getSupervisor());
            nutritionPlanWithBLOBs.setRemark(planModel.getRemark());
            nutritionPlanWithBLOBs.setIsPass(planModel.getIsPass());
            nutritionPlanWithBLOBs.setUpassReason(planModel.getUpassReason());
            nutritionPlanWithBLOBs.setIsPass1(planModel.getIsPass1());
            nutritionPlanWithBLOBs.setMaterialA(planModel.getMaterialA());
            nutritionPlanWithBLOBs.setMaterialM(planModel.getMaterialM());
            nutritionPlanWithBLOBs.setMaterialO(planModel.getMaterialO());
            nutritionPlanWithBLOBs.setMaterialWM(planModel.getMaterialWM());
            nutritionPlanWithBLOBs.setMaterialWO(planModel.getMaterialWO());
            nutritionPlanWithBLOBs.setRoughageP(planModel.getRoughageP());
            nutritionPlanWithBLOBs.setRoughageD(planModel.getRoughageD());
            nutritionPlanWithBLOBs.setRoughageWP(planModel.getRoughageWP());
            nutritionPlanWithBLOBs.setRoughageWD(planModel.getRoughageWD());
            nutritionPlanWithBLOBs.setRoughageWO(planModel.getRoughageWO());
            nutritionPlanWithBLOBs.setPickingM(planModel.getPickingM());
            nutritionPlanWithBLOBs.setPickingR(planModel.getPickingR());
            nutritionPlanWithBLOBs.setPickingO(planModel.getPickingO());

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
            NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
            NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();
            if(nutritionPlanWithBLOBs.getId() != null && !nutritionPlanWithBLOBs.getId().toString().isEmpty()){
                criteria.andIdEqualTo(nutritionPlanWithBLOBs.getId());
            }
            criteria.andIsPass1EqualTo(notPassed1);
            List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",select);
            response.setData(data);
            return response;
        }
    }
}
