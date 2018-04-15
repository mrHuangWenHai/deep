package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.NutritionPlanExample;
import com.deep.domain.model.NutritionPlanWithBLOBs;
import com.deep.domain.model.OtherTime;
import com.deep.domain.service.NutritionPlanService;
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
 * date: 2018/2/21  19:34
 */
@Controller
public class NurtritionResource {

    private final Logger logger = LoggerFactory.getLogger(NurtritionResource.class);
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
    public Response addPlan(@Valid NutritionPlanWithBLOBs insert,
                            @Valid OtherTime otherTime,
                            BindingResult bindingResult) throws ParseException {
        logger.info("invoke nutritionInsert/show {}",insert,otherTime,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("营养实施档案录入失败");
            return response;
        }
        Date nutritionT = new Date();
        Byte zero = 0;
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

//    按主键删除的接口：/nutritionDeleteById
//    按主键删除的方法名：dropPlan()
//    接收参数：整型id，根据主键号删除
    @RequestMapping(value = "/nutritionDeleteById",method = RequestMethod.GET)
    public String dropPlan(){
        return "NutritionDeleteById";
    }
    @ResponseBody
    @RequestMapping(value = "/nutritionDeleteById/show",method = RequestMethod.DELETE)
    public Response dropPlan(@Valid NutritionPlanWithBLOBs nutritionPlanWithBLOBs,
                             BindingResult bindingResult){
        logger.info("invoke nutritionDeleteById/show {}",nutritionPlanWithBLOBs,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("营养实施档案删除失败");
            return response;
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
    public Response changePlanByOperator(@Valid NutritionPlanWithBLOBs operator,
                                         @Valid OtherTime otherTime,
                                         BindingResult bindingResult) throws ParseException {
        logger.info("invoke nutritionUpdateByOperator/show {}",operator,otherTime,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("营养实施档案更新(操作员页面)失败");
            return response;
        }else {
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
    public Response changePlanByProfessor(@Valid NutritionPlanWithBLOBs professor,
                                          BindingResult bindingResult){
        logger.info("invoke nutritionUpdateByProfessor/show {}",professor,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("营养实施档案更新(专家页面)失败");
            return response;
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
    public Response changePlanBySupervisor(@Valid NutritionPlanWithBLOBs supervisor,
                                           BindingResult bindingResult){
        logger.info("invoke nutritionUpdateBySupervisor/show {}",supervisor,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("营养实施档案更新(监督页面)失败");
            return response;
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
        logger.info("invoke nutritionSelectById/show {}",nutritionPlanWithBLOBs,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("营养实施档案(根据条件)查询失败");
            return response;
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
    @RequestMapping(value = "/nutritionSelective/show",method = RequestMethod.GET)
    public Response findPlanSelective(@Valid NutritionPlanWithBLOBs nutritionPlanWithBLOBs,
                                      @Valid OtherTime otherTime,
                                      BindingResult bindingResult) throws ParseException {
        logger.info("invoke nutritionSelective/show {}",nutritionPlanWithBLOBs,otherTime,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("营养实施档案(根据条件)查询失败");
            return response;
        }else {
            Date nutritionT1 = null;
            Date nutritionT2 = null;
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
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
    public Response findPlanSelectByProfessor(@Valid NutritionPlanWithBLOBs nutritionPlanWithBLOBs,
                                              @Valid OtherTime otherTime,
                                              BindingResult bindingResult) {
        logger.info("invoke nutritionSelectByProfessor/show {}",nutritionPlanWithBLOBs,otherTime,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("营养实施档案(根据条件)查询失败");
            return response;
        }else {
            Byte notPassed = 0;
            NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
            NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();

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
            List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample);
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
    @RequestMapping(value = "/nutritionSelectBySupervisor/show",method = RequestMethod.GET)
    public Response findPlanSelectBySupervisor(@Valid NutritionPlanWithBLOBs nutritionPlanWithBLOBs,
                                               @Valid OtherTime otherTime,
                                               BindingResult bindingResult) {
        logger.info("invoke nutritionSelectBySupervisor/show {}",nutritionPlanWithBLOBs,otherTime,bindingResult);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("营养实施档案(根据条件)查询失败");
            return response;
        }else {
            Byte notPassed1 = 0;
            NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
            NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();
            if(nutritionPlanWithBLOBs.getId() != null && nutritionPlanWithBLOBs.getId().toString() !=""){
                criteria.andIdEqualTo(nutritionPlanWithBLOBs.getId());
            }
            if(nutritionPlanWithBLOBs.getSupervisor() != null && nutritionPlanWithBLOBs.getSupervisor() !=""){
                criteria.andSupervisorEqualTo(nutritionPlanWithBLOBs.getSupervisor());
            }
            criteria.andIsPass1EqualTo(notPassed1);
            List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample);
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",select);
            response.setData(data);
            return response;
        }
    }
}
