package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.NutritionPlanExample;
import com.deep.domain.model.NutritionPlanWithBLOBs;
import com.deep.domain.model.OtherTime;
import com.deep.domain.service.NutritionPlanService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
@Controller
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
    public Response addPlan(@RequestBody NutritionPlanWithBLOBs insert
                            ) throws ParseException {
        Date nutritionT = new Date();
        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
        if (insert.getNutritionT().toString() != "") {
            nutritionT =  formatter.parse(insert.getNutritionT().toString());
        }
        Byte zero = 0;
        insert.setGmtCreate(new Date());
        insert.setFactoryNum(insert.getFactoryNum());
        insert.setBuilding(insert.getBuilding());
        insert.setNutritionT(nutritionT);
        insert.setQuantity(insert.getQuantity());
        insert.setAverage(insert.getAverage());
        insert.setPeriod(insert.getPeriod());
        insert.setWater(insert.getWater());
        insert.setOperator(insert.getOperator());
        insert.setRemark(insert.getRemark());
        insert.setIsPass(zero);
        insert.setIsPass1(zero);
        insert.setMaterialA(insert.getMaterialA());
        insert.setMaterialM(insert.getMaterialM());
        insert.setMaterialO(insert.getMaterialO());
        insert.setMaterialWM(insert.getMaterialWM());
        insert.setMaterialWO(insert.getMaterialWO());
        insert.setRoughageP(insert.getRoughageP());
        insert.setRoughageD(insert.getRoughageD());
        insert.setRoughageWP(insert.getRoughageWP());
        insert.setRoughageWD(insert.getRoughageWD());
        insert.setRoughageWO(insert.getRoughageWO());
        insert.setPickingM(insert.getPickingM());
        insert.setPickingR(insert.getPickingR());
        insert.setPickingO(insert.getPickingO());
        nutritionPlanService.addPlan(insert);

        NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
        NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();
        criteria.andFactoryNumEqualTo(insert.getFactoryNum());
        criteria.andBuildingEqualTo(insert.getBuilding());
        criteria.andNutritionTEqualTo(nutritionT);
        criteria.andQuantityEqualTo(insert.getQuantity());
        criteria.andAverageEqualTo(insert.getAverage());
        criteria.andPeriodEqualTo(insert.getPeriod());
        criteria.andWaterEqualTo(insert.getWater());
        criteria.andOperatorEqualTo(insert.getOperator());
        criteria.andRemarkEqualTo(insert.getRemark());
        criteria.andIsPassEqualTo(insert.getIsPass());
        criteria.andIsPass1EqualTo(insert.getIsPass1());

        List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample);

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("nutrition_plan",select);
        response.setData(data);
        return response;
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
    public Response dropPlan(@RequestBody NutritionPlanWithBLOBs nutritionPlanWithBLOBs){
        NutritionPlanWithBLOBs delete = new NutritionPlanWithBLOBs();
        nutritionPlanService.dropPlan(nutritionPlanWithBLOBs.getId());
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("nutrition_plan",delete);
        response.setData(data);
        return response;
    }

//    专家使用按主键修改的接口：/nutritionUpdateByProfessor
//    专家使用按主键修改的方法名：changePlanByProfessor()
//    专家使用接收参数：整个表单类型
    @RequestMapping(value = "/nutritionUpdateByProfessor",method = RequestMethod.GET)
    public String changePlanByProfessor(){
        return "NutritionUpdateByProfessor";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionUpdateByProfessor/show",method = RequestMethod.PUT)
    public Response changePlanByProfessor(@RequestBody NutritionPlanWithBLOBs update) throws ParseException {
        Date nutritionT = new Date();
        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
        if (update.getNutritionT().toString() != "") {
            nutritionT =  formatter.parse(update.getNutritionT().toString());
        }
        update.setId(update.getId());
        update.setGmtModified(new Date());
        update.setFactoryNum(update.getFactoryNum());
        update.setBuilding(update.getBuilding());
        update.setNutritionT(nutritionT);
        update.setQuantity(update.getQuantity());
        update.setAverage(update.getAverage());
        update.setPeriod(update.getPeriod());
        update.setWater(update.getWater());
        update.setProfessor(update.getProfessor());
        update.setRemark(update.getRemark());
        update.setIsPass(update.getIsPass());
        update.setUpassReason(update.getUpassReason());
        update.setMaterialA(update.getMaterialA());
        update.setMaterialM(update.getMaterialM());
        update.setMaterialO(update.getMaterialO());
        update.setMaterialWM(update.getMaterialWM());
        update.setMaterialWO(update.getMaterialWO());
        update.setRoughageP(update.getRoughageP());
        update.setRoughageD(update.getRoughageD());
        update.setRoughageWP(update.getRoughageWP());
        update.setRoughageWD(update.getRoughageWD());
        update.setRoughageWO(update.getRoughageWO());
        update.setPickingM(update.getPickingM());
        update.setPickingR(update.getPickingR());
        update.setPickingO(update.getPickingO());
        nutritionPlanService.changePlanByProfessor(update);

        NutritionPlanWithBLOBs selectById = nutritionPlanService.findPlanById(update.getId());
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("nutrition_plan",selectById);
        response.setData(data);
        return response;
    }

//    监督者使用按主键修改的接口：/nutritionUpdateBySupervisor
//    监督者使用按主键修改的方法名：changePlanBySupervisor()
//    监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
    @RequestMapping(value = "/nutritionUpdateBySupervisor",method = RequestMethod.GET)
    public String changePlanBySupervisor(){
        return "NutritionUpdateBySupervisor";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionUpdateBySupervisor/show",method = RequestMethod.PUT)
    public Response changePlanBySupervisor(@RequestBody NutritionPlanWithBLOBs update){
        update.setId(update.getId());
        update.setGmtSupervised(new Date());
        update.setOperator(update.getSupervisor());
        update.setIsPass(update.getIsPass1());
        nutritionPlanService.changePlanBySupervisor(update);

        NutritionPlanWithBLOBs selectById = nutritionPlanService.findPlanById(update.getId());
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("nutrition_plan",selectById);
        response.setData(data);
        return response;
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
    public Response findPlanById(@Valid NutritionPlanWithBLOBs nutritionPlanWithBLOBs) {
        NutritionPlanWithBLOBs selectById = nutritionPlanService.findPlanById(nutritionPlanWithBLOBs.getId());
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("nutrition_plan",selectById);
        response.setData(data);
        return response;
    }

//    按条件查询接口：/nutritionSelective
//    按条件查询方法名：findPlanSelective()
//    接收的参数：前端的各参数，以及两个("s_nutritionT1")("s_nutritionT2")时间字符串（所有参数可以选填）
    @RequestMapping(value = "/nutritionSelective",method = RequestMethod.GET)
    public String findPlanSelective(){
        return "NutritionSelective";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionSelective/show",method = RequestMethod.GET)
    public Response findPlanSelective(@Valid NutritionPlanWithBLOBs nutritionPlanWithBLOBs,
                                      @Valid OtherTime otherTime
                                      ) throws ParseException {
        Date nutritionT1 = null;
        Date nutritionT2 = null;
        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
        NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
        NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();

        if (otherTime.getS_nutritionT1() != null && otherTime.getS_nutritionT1() != "" && otherTime.getS_nutritionT2() != null && otherTime.getS_nutritionT2() != ""){
            nutritionT1 =  formatter.parse(otherTime.getS_nutritionT1());
            nutritionT2 =  formatter.parse(otherTime.getS_nutritionT2());
        }
        if(nutritionT1 != null && nutritionT2 != null){
            criteria.andNutritionTBetween(nutritionT1,nutritionT2);
        }
        if(nutritionPlanWithBLOBs.getId() != null && nutritionPlanWithBLOBs.getId().toString() !=""){
            criteria.andIdEqualTo(nutritionPlanWithBLOBs.getId());
        }
        if(nutritionPlanWithBLOBs.getFactoryNum() != null && nutritionPlanWithBLOBs.getFactoryNum().toString() !=""){
            criteria.andFactoryNumEqualTo(nutritionPlanWithBLOBs.getFactoryNum());
        }
        if(nutritionPlanWithBLOBs.getBuilding() != null && nutritionPlanWithBLOBs.getBuilding() !=""){
            criteria.andBuildingEqualTo(nutritionPlanWithBLOBs.getBuilding());
        }
        if(nutritionPlanWithBLOBs.getQuantity() != null && nutritionPlanWithBLOBs.getQuantity().toString() !=""){
            criteria.andQuantityEqualTo(nutritionPlanWithBLOBs.getQuantity());
        }
        if(nutritionPlanWithBLOBs.getAverage() != null && nutritionPlanWithBLOBs.getAverage() !=""){
            criteria.andAverageGreaterThanOrEqualTo(nutritionPlanWithBLOBs.getAverage());
        }
        if (nutritionPlanWithBLOBs.getPeriod()!= null && nutritionPlanWithBLOBs.getPeriod() !=""){
            criteria.andPeriodEqualTo(nutritionPlanWithBLOBs.getPeriod());
        }
        if (nutritionPlanWithBLOBs.getWater()!= null && nutritionPlanWithBLOBs.getWater() !=""){
            criteria.andWaterEqualTo(nutritionPlanWithBLOBs.getWater());
        }
        if(nutritionPlanWithBLOBs.getOperator() != null && nutritionPlanWithBLOBs.getOperator() !=""){
            criteria.andOperatorEqualTo(nutritionPlanWithBLOBs.getOperator());
        }
        if(nutritionPlanWithBLOBs.getProfessor() != null && nutritionPlanWithBLOBs.getProfessor() !=""){
            criteria.andProfessorEqualTo(nutritionPlanWithBLOBs.getProfessor());
        }
        if(nutritionPlanWithBLOBs.getSupervisor() != null && nutritionPlanWithBLOBs.getSupervisor() !=""){
            criteria.andSupervisorEqualTo(nutritionPlanWithBLOBs.getSupervisor());
        }
        if(nutritionPlanWithBLOBs.getIsPass() != null && nutritionPlanWithBLOBs.getIsPass().toString() !=""){
            criteria.andIsPassEqualTo(nutritionPlanWithBLOBs.getIsPass());
        }
        if(nutritionPlanWithBLOBs.getUpassReason() != null && nutritionPlanWithBLOBs.getUpassReason() !=""){
            criteria.andUpassReasonLike(nutritionPlanWithBLOBs.getUpassReason());
        }
        if(nutritionPlanWithBLOBs.getIsPass1() != null && nutritionPlanWithBLOBs.getIsPass1().toString() !=""){
            criteria.andIsPass1EqualTo(nutritionPlanWithBLOBs.getIsPass1());
        }
        List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("nutrition_plan",select);
        response.setData(data);
        return response;
    }

//    供技术审核查询信息: /nutritionSelectByProfessor
//    供技术审核查询方法名：findPlanSelectBySupervisor()
//    接收的参数：前端的各参数，（所有参数可以选填）
    @RequestMapping(value = "/nutritionSelectByProfessor",method = RequestMethod.GET)
    public String findPlanSelectByProfessor(){
        return "NutritionSelectByProfessor";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionSelectByProfessor/show",method = RequestMethod.GET)
    public Response findPlanSelectByProfessor(@Valid NutritionPlanWithBLOBs nutritionPlanWithBLOBs) {
        NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
        NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();
        Byte notPassed = 0;
        if(nutritionPlanWithBLOBs.getId() != null && nutritionPlanWithBLOBs.getId().toString() !=""){
            criteria.andIdEqualTo(nutritionPlanWithBLOBs.getId());
        }
        if(nutritionPlanWithBLOBs.getProfessor() != null && nutritionPlanWithBLOBs.getProfessor() !=""){
            criteria.andProfessorEqualTo(nutritionPlanWithBLOBs.getProfessor());
        }
        if(nutritionPlanWithBLOBs.getUpassReason() != null && nutritionPlanWithBLOBs.getUpassReason() !=""){
            criteria.andUpassReasonLike(nutritionPlanWithBLOBs.getUpassReason());
        }
        criteria.andIsPassEqualTo(notPassed);
        List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelectByProfessor(nutritionPlanExample);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("nutrition_plan",select);
        response.setData(data);
        return response;
    }

//    供监督者查询信息:nutritionSelectBySupervisor
//    供监督者查询方法名：findPlanSelectBySupervisor()
//    接收的参数：前端的各参数，（所有参数可以选填）
    @RequestMapping(value = "/nutritionSelectBySupervisor",method = RequestMethod.GET)
    public String findPlanSelectBySupervisor(){
        return "NutritionSelectBySupervisor";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionSelectBySupervisor/show",method = RequestMethod.GET)
    public Response findPlanSelectBySupervisor(@Valid NutritionPlanWithBLOBs nutritionPlanWithBLOBs,BindingResult result) {
        if(result.hasErrors()){
            List<ObjectError> ls = result.getAllErrors();
            for (int i = 0; i < ls.size(); i++) {
                System.out.println("error:"+ls.get(i));
            }
        }
        NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
        NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();
        Byte notPassed1 = 0;
        if(nutritionPlanWithBLOBs.getId() != null && nutritionPlanWithBLOBs.getId().toString() !=""){
            criteria.andIdEqualTo(nutritionPlanWithBLOBs.getId());
        }
        if(nutritionPlanWithBLOBs.getSupervisor() != null && nutritionPlanWithBLOBs.getSupervisor() !=""){
            criteria.andSupervisorEqualTo(nutritionPlanWithBLOBs.getSupervisor());
        }
        criteria.andIsPass1EqualTo(notPassed1);
        List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelectBySupervisor(nutritionPlanExample);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("nutrition_plan",select);
        response.setData(data);
        return response;
    }
}
