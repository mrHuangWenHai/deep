package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.BreedingPlan;
import com.deep.domain.model.BreedingPlanExample;
import com.deep.domain.service.BreedingPlanService;
import org.springframework.stereotype.Controller;
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
 * date: 2018/2/2  12:52
 */
@Controller
public class BreedingController {

    @Resource
    private BreedingPlanService breedingPlanService;

    @ResponseBody
    @RequestMapping(value = "/BreedingPlan",method = RequestMethod.GET)
    public String helloBreed() {
        return "Hello BreedingPlan!";
    }

//    按主键删除的接口：/BreedingInsert
//    按主键删除的方法名：addPlan()
//    接收参数：整个表单信息（所有参数必填）
//    参数类型为：Long factoryNum;String building;String mEtI;String mEtB;String fEtI;String fEtB;Date breedingT; Date gestationT;Date prenatalIT;Date cubT;Integer quantity;String operator;String remark；
    @RequestMapping(value = "/BreedingInsert",method = RequestMethod.GET)
    public String addPlan(){
        return "BreedingInsert";
    }
    @ResponseBody
    @RequestMapping(value = "/BreedingInsert/show",method = RequestMethod.GET)
    public Response addPlan(@Valid BreedingPlan insert
                            ) throws ParseException {
        Date breedingT = new Date();
        Date gestationT = new Date();
        Date prenatalIT = new Date();
        Date cubT = new Date();
        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
        if (insert.getBreedingT().toString() != ""){
            breedingT =  formatter.parse(insert.getBreedingT().toString());
        }
        if (insert.getGestationT().toString() != ""){
            gestationT = formatter.parse(insert.getGestationT().toString());
        }
        if (insert.getPrenatalIT().toString() != ""){
            prenatalIT = formatter.parse(insert.getPrenatalIT().toString());
        }
        if (insert.getCubT().toString() != ""){
            cubT = formatter.parse(insert.getCubT().toString());
        }

        Byte zero = 0;
        insert.setGmtCreate(new Date());
        insert.setFactoryNum(insert.getFactoryNum());
        insert.setBuilding(insert.getBuilding());
        insert.setmEtI(insert.getmEtI());
        insert.setmEtB(insert.getmEtB());
        insert.setfEtI(insert.getfEtI());
        insert.setfEtB(insert.getfEtB());
        insert.setBreedingT(breedingT);
        insert.setGestationT(gestationT);
        insert.setPrenatalIT(prenatalIT);
        insert.setCubT(cubT);
        insert.setQuantity(insert.getQuantity());
        insert.setOperator(insert.getOperator());
        insert.setRemark(insert.getRemark());
        insert.setIsPass(zero);
        insert.setIsPass1(zero);
        breedingPlanService.addPlan(insert);

        BreedingPlanExample breedingPlanExample = new BreedingPlanExample();
        BreedingPlanExample.Criteria criteria = breedingPlanExample.createCriteria();
        criteria.andFactoryNumEqualTo(insert.getFactoryNum());
        criteria.andBuildingEqualTo(insert.getBuilding());
        criteria.andMEtIEqualTo(insert.getmEtI());
        criteria.andMEtBEqualTo(insert.getmEtB());
        criteria.andFEtIEqualTo(insert.getfEtI());
        criteria.andFEtBEqualTo(insert.getfEtB());
        criteria.andBreedingTEqualTo(insert.getBreedingT());
        criteria.andGestationTEqualTo(insert.getGestationT());
        criteria.andPrenatalITEqualTo(insert.getPrenatalIT());
        criteria.andCubTEqualTo(insert.getCubT());
        criteria.andQuantityEqualTo(insert.getQuantity());
        criteria.andOperatorEqualTo(insert.getOperator());
        criteria.andRemarkEqualTo(insert.getRemark());

        List<BreedingPlan> select = breedingPlanService.findPlanSelective(breedingPlanExample);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("breeding_plan",select);
        response.setData(data);
        return response;
    }

//    按主键删除的接口：/BreedingDeleteById
//    按主键删除的方法名：dropPlan()
//    接收参数：整型id，根据主键号删除
    @RequestMapping(value = "/BreedingDeleteById",method = RequestMethod.GET)
    public String dropPlan(){
        return "BreedingDeleteById";
    }
    @ResponseBody
    @RequestMapping(value = "/BreedingDeleteById/show",method = RequestMethod.GET)
    public Response dropPlan(@RequestParam("id") Integer id){
        BreedingPlan delete = new BreedingPlan();
        breedingPlanService.dropPlan(id);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("breeding_plan",delete);
        response.setData(data);
        return response;
    }

//    专家使用按主键修改的接口：/BreedingUpdateByProfessor
//    专家使用按主键修改的方法名：changePlanByProfessor()
//    专家使用接收参数：整个表单类型
    @RequestMapping(value = "/BreedingUpdateByProfessor",method = RequestMethod.GET)
    public String changePlanByProfessor(){
        return "BreedingUpdateByProfessor";
    }
    @ResponseBody
    @RequestMapping(value = "/BreedingUpdateByProfessor/show",method = RequestMethod.GET)
    public Response changePlanByProfessor(@Valid BreedingPlan update
                                          ) throws ParseException {
        Date breedingT = new Date();
        Date gestationT = new Date();
        Date prenatalIT = new Date();
        Date cubT = new Date();
        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
        if (update.getBreedingT().toString() != ""){
            breedingT =  formatter.parse(update.getBreedingT().toString());
        }
        if (update.getGestationT().toString() != ""){
            gestationT = formatter.parse(update.getGestationT().toString());
        }
        if (update.getPrenatalIT().toString() != ""){
            prenatalIT = formatter.parse(update.getPrenatalIT().toString());
        }
        if (update.getCubT().toString() != ""){
            cubT = formatter.parse(update.getCubT().toString());
        }

        update.setId(update.getId());
        update.setGmtModified(new Date());
        update.setFactoryNum(update.getFactoryNum());
        update.setBuilding(update.getBuilding());
        update.setmEtI(update.getmEtI());
        update.setmEtB(update.getmEtB());
        update.setfEtI(update.getfEtI());
        update.setfEtB(update.getfEtB());
        update.setBreedingT(breedingT);
        update.setGestationT(gestationT);
        update.setPrenatalIT(prenatalIT);
        update.setCubT(cubT);
        update.setQuantity(update.getQuantity());
        update.setProfessor(update.getProfessor());
        update.setRemark(update.getRemark());
        update.setIsPass(update.getIsPass());
        update.setUpassReason(update.getUpassReason());

        breedingPlanService.changePlanByProfessor(update);
        BreedingPlan selectById = breedingPlanService.findPlanById(update.getId());
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("breeding_plan",selectById);
        response.setData(data);
        return response;
    }

//    监督者使用按主键修改的接口：/BreedingUpdateBySupervisor
//    监督者使用按主键修改的方法名：changePlanBySupervisor()
//    监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
    @RequestMapping(value = "/BreedingUpdateBySupervisor",method = RequestMethod.GET)
    public String changePlanBySupervisor(){
        return "BreedingUpdateBySupervisor";
    }
    @ResponseBody
    @RequestMapping(value = "/BreedingUpdateBySupervisor/show",method = RequestMethod.GET)
    public Response changePlanBySupervisor(@Valid BreedingPlan update) {
        update.setId(update.getId());
        update.setGmtSupervised(new Date());
        update.setSupervisor(update.getSupervisor());
        update.setIsPass1(update.getIsPass1());

        breedingPlanService.changePlanBySupervisor(update);
        BreedingPlan selectById = breedingPlanService.findPlanById(update.getId());
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("breeding_plan",selectById);
        response.setData(data);
        return response;
    }

//    按主键查询的接口：/BreedingSelectById
//    按主键查询的方法名：findPlanById()
//    接收参数：整型的主键号（保留接口查询，前端不调用此接口）
    @RequestMapping(value = "/BreedingSelectById",method = RequestMethod.GET)
    public String findPlanById(){
        return "BreedingSelectById";
    }
    @ResponseBody
    @RequestMapping(value = "/BreedingSelectById/show",method = RequestMethod.GET)
    public Response findPlanById(@RequestParam Integer id){
        //查询语句的写法：一定要在声明对象时把值直接赋进去
        BreedingPlan selectById = breedingPlanService.findPlanById(id);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("breeding_plan",selectById);
        response.setData(data);
        return response;
    }

//    按条件查询接口：/BreedingSelective
//    按条件查询方法名：findPlanSelective()
//    接收的参数：前端的各参数，以及八个("s_breedingT1")("s_breedingT2")("s_gestationT1")("s_gestationT2")("s_prenatalIT1")("s_prenatalIT2")("s_cubT1")("s_cubT2")时间字符串（所有参数可以选填）
    @RequestMapping(value = "/BreedingSelective",method = RequestMethod.GET)
    public String findPlanSelective(){
        return "BreedingSelective";
    }
    @ResponseBody
    @RequestMapping(value = "/BreedingSelective/show",method = RequestMethod.GET)
    public Response findPlanSelective(@Valid BreedingPlan breedingPlan,
                                      @RequestParam("s_breedingT1") String s_breedingT1,
                                      @RequestParam("s_breedingT2") String s_breedingT2,
                                      @RequestParam("s_gestationT1") String s_gestationT1,
                                      @RequestParam("s_gestationT2") String s_gestationT2,
                                      @RequestParam("s_prenatalIT1") String s_prenatalIT1,
                                      @RequestParam("s_prenatalIT2") String s_prenatalIT2,
                                      @RequestParam("s_cubT1") String s_cubT1,
                                      @RequestParam("s_cubT2") String s_cubT2
                                      ) throws ParseException {
        Date breedingT1 = null;
        Date breedingT2 = null;
        Date gestationT1 = null;
        Date gestationT2 = null;
        Date prenatalIT1 = null;
        Date prenatalIT2 = null;
        Date cubT1 = null;
        Date cubT2 = null;
        java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
        if (s_breedingT1 != "" && s_breedingT2 != ""){
            breedingT1 =  formatter.parse(s_breedingT1);
            breedingT2 =  formatter.parse(s_breedingT2);
        }
        if (s_gestationT1 != "" && s_gestationT2 != ""){
            gestationT1 =  formatter.parse(s_gestationT1);
            gestationT2 =  formatter.parse(s_gestationT2);
        }
        if (s_prenatalIT1 != "" && s_prenatalIT2 != ""){
            prenatalIT1 =  formatter.parse(s_prenatalIT1);
            prenatalIT2 =  formatter.parse(s_prenatalIT2);
        }
        if (s_cubT1 != "" && s_cubT2 != ""){
            cubT1 =  formatter.parse(s_cubT1);
            cubT2 =  formatter.parse(s_cubT2);
        }

        BreedingPlanExample breedingPlanExample = new BreedingPlanExample();
        BreedingPlanExample.Criteria criteria = breedingPlanExample.createCriteria();

        if(breedingPlan.getId() != null && breedingPlan.getId().toString() !=""){
            criteria.andIdEqualTo(breedingPlan.getId());
        }
        if(breedingPlan.getFactoryNum() != null && breedingPlan.getFactoryNum().toString() !=""){
            criteria.andFactoryNumEqualTo(breedingPlan.getFactoryNum());
        }
        if(breedingPlan.getBuilding() != null && breedingPlan.getBuilding() !=""){
            criteria.andBuildingEqualTo(breedingPlan.getBuilding());
        }
        if(breedingPlan.getmEtI() != null && breedingPlan.getmEtI() !=""){
            criteria.andMEtIEqualTo(breedingPlan.getmEtI());
        }
        if(breedingPlan.getmEtB() != null && breedingPlan.getmEtB() !=""){
            criteria.andMEtBEqualTo(breedingPlan.getmEtI());
        }
        if(breedingPlan.getfEtI() != null && breedingPlan.getfEtI() !=""){
            criteria.andFEtIEqualTo(breedingPlan.getfEtI());
        }
        if(breedingPlan.getfEtB() != null && breedingPlan.getfEtB() !=""){
            criteria.andFEtBEqualTo(breedingPlan.getfEtB());
        }
        if(breedingT1 != null && breedingT2 != null ){
            criteria.andBreedingTBetween(breedingT1,breedingT2);
        }
        if(gestationT1 != null && gestationT2 != null ){
            criteria.andGestationTBetween(gestationT1,gestationT2);
        }
        if(prenatalIT1 != null && breedingT2 != null ){
            criteria.andPrenatalITBetween(prenatalIT1,prenatalIT2);
        }
        if(cubT1 != null && cubT2 != null ){
            criteria.andCubTBetween(cubT1,cubT2);
        }
        if(breedingPlan.getQuantity() != null && breedingPlan.getQuantity().toString() !=""){
            criteria.andQuantityEqualTo(breedingPlan.getQuantity());
        }
        if(breedingPlan.getOperator() != null && breedingPlan.getOperator() !=""){
            criteria.andOperatorEqualTo(breedingPlan.getOperator());
        }
        if(breedingPlan.getProfessor() != null && breedingPlan.getProfessor() !=""){
            criteria.andProfessorEqualTo(breedingPlan.getProfessor());
        }
        if(breedingPlan.getSupervisor() != null && breedingPlan.getSupervisor() !=""){
            criteria.andSupervisorEqualTo(breedingPlan.getSupervisor());
        }
        if(breedingPlan.getIsPass() != null && breedingPlan.getIsPass().toString() !=""){
            criteria.andIsPassEqualTo(breedingPlan.getIsPass());
        }
        if(breedingPlan.getUpassReason() != null && breedingPlan.getUpassReason() !=""){
            criteria.andUpassReasonLike(breedingPlan.getUpassReason());
        }
        if(breedingPlan.getIsPass1() != null && breedingPlan.getIsPass1().toString() !=""){
            criteria.andIsPassEqualTo(breedingPlan.getIsPass1());
        }
        List<BreedingPlan> select = breedingPlanService.findPlanSelective(breedingPlanExample);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("breeding_plan",select);
        response.setData(data);
        return response;
    }

//    供技术审核查询信息
//    供技术审核查询方法名：findPlanSelectByProfessor()
//    接收的参数：前端的各参数，（所有参数可以选填）
    @RequestMapping(value = "/BreedingSelectByProfessor",method = RequestMethod.GET)
    public String findPlanSelectByProfessor(){
        return "BreedingSelectByProfessor";
    }
    @ResponseBody
    @RequestMapping(value = "/BreedingSelectByProfessor/show",method = RequestMethod.GET)
    public Response findPlanSelectByProfessor(@Valid BreedingPlan breedingPlan
                                      ) throws ParseException {
        BreedingPlanExample breedingPlanExample = new BreedingPlanExample();
        BreedingPlanExample.Criteria criteria = breedingPlanExample.createCriteria();

        if(breedingPlan.getId() != null && breedingPlan.getId().toString() !=""){
            criteria.andIdEqualTo(breedingPlan.getId());
        }
        if(breedingPlan.getProfessor() != null && breedingPlan.getProfessor() !=""){
            criteria.andProfessorEqualTo(breedingPlan.getProfessor());
        }
        if(breedingPlan.getIsPass() != null && breedingPlan.getIsPass().toString() !=""){
            criteria.andIsPassEqualTo(breedingPlan.getIsPass());
        }
        if(breedingPlan.getUpassReason() != null && breedingPlan.getUpassReason() !=""){
            criteria.andUpassReasonLike(breedingPlan.getUpassReason());
        }
        List<BreedingPlan> select = breedingPlanService.findPlanSelective(breedingPlanExample);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("breeding_plan",select);
        response.setData(data);
        return response;
    }

//    供监督者查询信息
//    供监督者查询方法名：findPlanSelectBySupervisor()
//    接收的参数：前端的各参数，（所有参数可以选填）
    @RequestMapping(value = "/BreedingSelectBySupervisor",method = RequestMethod.GET)
    public String findPlanSelectBySupervisor(){
        return "BreedingSelectSupervisor";
    }
    @ResponseBody
    @RequestMapping(value = "/BreedingSelectBySupervisor/show",method = RequestMethod.GET)
    public Response findPlanSelectBySupervisor(@Valid BreedingPlan breedingPlan
                                               ) throws ParseException {
        BreedingPlanExample breedingPlanExample = new BreedingPlanExample();
        BreedingPlanExample.Criteria criteria = breedingPlanExample.createCriteria();

        if(breedingPlan.getId() != null && breedingPlan.getId().toString() !=""){
            criteria.andIdEqualTo(breedingPlan.getId());
        }
        if(breedingPlan.getSupervisor() != null && breedingPlan.getSupervisor() !=""){
            criteria.andSupervisorEqualTo(breedingPlan.getSupervisor());
        }
        if(breedingPlan.getIsPass1() != null && breedingPlan.getIsPass1().toString() !=""){
            criteria.andIsPassEqualTo(breedingPlan.getIsPass1());
        }
        List<BreedingPlan> select = breedingPlanService.findPlanSelective(breedingPlanExample);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("breeding_plan",select);
        response.setData(data);
        return response;
    }
}

