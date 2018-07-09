package com.deep.api.resource;

import com.deep.api.Utils.AgentUtil;
import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.Utils.TokenAnalysis;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.request.NutritionPlanModel;
import com.deep.api.request.ProfessorRequest;
import com.deep.api.request.SupervisorRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.*;
import com.deep.domain.service.FactoryService;
import com.deep.domain.service.NutritionPlanService;
import com.deep.domain.service.UserService;
import com.deep.domain.util.JedisUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.*;

/**
 * author: Created  By  Caojiawei
 * date: 2018/2/21  19:34
 */
@RestController

@RequestMapping(value = "nutrition")
public class NutritionResource {

    private final Logger logger = LoggerFactory.getLogger(NutritionResource.class);

    //用于查询专家/监督员电话并抉择发送短信
    @Resource
    private UserService userService;

    //用于查询羊厂代理id
    @Resource
    private FactoryService factoryService;

    @Resource
    private NutritionPlanService nutritionPlanService;

    /**
     * 插入接口：/nutritionInsert
     * 按主键插入的方法名：addPlan()
     * 接收参数： json格式类型
     * 参数类型为：
     * Long factoryNum; String building;Date nutritionT;Long quantity;String average;String period;String water;String operator;String remark;
     * String materialA;String materialM;String materialO;String materialWM;String materialWO;String roughageP;String roughageD;String roughageWP;String roughageWD;String roughageWO;String pickingM;String pickingR;String pickingO;
     * @param planModel planModel
     * @param bindingResult bindingResult
     * @return response
     * @throws ParseException parseException
     */
    @Permit(authorities = "increase_phase_nutritional_profile")
    @PostMapping(value = "")
    public Response addPlan(@RequestBody @Valid NutritionPlanModel planModel, BindingResult bindingResult) throws ParseException {
        logger.info("invoke addPlan {}, url is nutrition", planModel.toString());
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案录入失败");
        }else {
            //将planModel部分变量拆分传递给对象insert
            NutritionPlanWithBLOBs insert = new NutritionPlanWithBLOBs();
            insert.setFactoryNum(planModel.getFactoryNum());
            insert.setFactoryName(planModel.getFactoryName());
            insert.setBuilding(planModel.getBuilding());
            insert.setQuantity(planModel.getQuantity());
            // 羊只均重, 后来不使用, 以后需要等待换成耳牌号附件
            insert.setAverage(planModel.getAverage());
            insert.setPeriod(planModel.getPeriod());
            insert.setWater(planModel.getWater());
            insert.setOperatorName(planModel.getOperatorName());
            insert.setOperatorId(planModel.getOperatorId());
            insert.setMaterialA(planModel.getMaterialA());
            insert.setMaterialM(planModel.getMaterialM());
            insert.setMaterialO(planModel.getMaterialO());
            insert.setMaterialWM(planModel.getMaterialWM());
            insert.setMaterialWO(planModel.getMaterialWO());
            insert.setRoughageP(planModel.getRoughageP());
            insert.setRoughageD(planModel.getRoughageD());
            insert.setRoughageO(planModel.getRoughageO());
            insert.setRoughageWP(planModel.getRoughageWP());
            insert.setRoughageWD(planModel.getRoughageWD());
            insert.setRoughageWO(planModel.getRoughageWO());
            insert.setPickingM(planModel.getPickingM());
            insert.setPickingR(planModel.getPickingR());
            insert.setPickingO(planModel.getPickingO());
            insert.setNutritionT(planModel.getNutritionT());
            insert.setAverage(planModel.getAverage());
            insert.setGmtCreate(new Date());

            Byte zero = 2;
            insert.setGmtCreate(new Date());
            insert.setIspassCheck(zero);
            insert.setIspassSup(zero);

            int success = nutritionPlanService.addPlan(insert);
            if (success <= 0) {
                return Responses.errorResponse("插入失败");
            }
            short agentID = this.factoryService.queryOneAgentByID(planModel.getFactoryNum());
            String professorKey = agentID + "_professor";
            String supervisorKey = planModel.getFactoryNum().toString() + "_supervisor";

            String testSendProfessor = agentID + "_professor_AlreadySend";
            String testSendSupervisor = planModel.getFactoryNum().toString() + "_supervisor_AlreadySend";

            JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
            JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);

            if( !("1".equals(JedisUtil.getCertainKeyValue(testSendProfessor)))) {
                if( JedisUtil.redisJudgeTime(professorKey) ) {
                    System.out.println("in redis:");
                    List<String> phone = userService.getProfessorTelephoneByFactoryNum(BigInteger.valueOf(planModel.getFactoryNum()));

                    if (phone.size() != 0) {
                        if (JedisUtil.redisSendMessage(phone, JedisUtil.getCertainKeyValue("Message"))) {
                            JedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor, "1", Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime")) * 24 * 60 * 60);
                        }
                    }
                }
            }

            if( !("1".equals(JedisUtil.getCertainKeyValue(testSendSupervisor)))) {
                if(JedisUtil.redisJudgeTime(supervisorKey)) {
                    List<String> phone = userService.getSuperiorTelephoneByFactoryNum(BigInteger.valueOf(planModel.getFactoryNum()));

                    if (phone.size() != 0) {

                        if( JedisUtil.redisSendMessage(phone, JedisUtil.getCertainKeyValue("Message"))) {
                            JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor,"1",Integer.parseInt(JedisUtil.getCertainKeyValue("ExpireTime"))*24*60*60);
                        }
                    }
                }
            }

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", success);

            response.setData(data);
            return response;
        }
    }

    /**
     * 按主键删除的接口：/nutritionDeleteById
     * 按主键删除的方法名：dropPlan()
     * 接收参数：整型id，根据主键号删除
     * @return response
     */
    @Permit(authorities = "delete_stage_nutrition_file")
    @DeleteMapping(value = "/{id}")
    public Response dropPlan(@PathVariable("id") String id){
        logger.info("invoke dropPlan {}, url is nutrition/{id}", id);
        int uid = StringToLongUtil.stringToInt(id);
        NutritionPlanWithBLOBs selectById = nutritionPlanService.findPlanById(uid);
        if (uid == -1) {
            return Responses.errorResponse("错误");
        }
        if (selectById.getIspassCheck() != 1) {
            int success = nutritionPlanService.dropPlan(uid);
            if (success <= 0) {
                return Responses.errorResponse("删除失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", success);
            response.setData(data);
            return response;
        } else {
            return Responses.errorResponse("该记录已经被审核过，不能删除！");
        }
    }

    /**
     * 操作员使用按主键修改的接口：/nutritionUpdateByOperator
     * 操作员使用按主键修改的方法名：changePlanByOperator()
     * 操作员使用接收参数：整个表单类型（整型id必填，各参数选填）
     * @param planModel planModel
     * @param bindingResult bindingResult
     * @return response
     * @throws ParseException parseException
     */
    @Permit(authorities = "modification_phase_nutrition_file")
    @PutMapping(value = "/{id}")
    public Response changePlanByOperator(@RequestBody @Valid NutritionPlanModel planModel, @PathVariable("id") String id, BindingResult bindingResult) throws ParseException {
        logger.info("invoke changePlanByOperator {}, url is nutrition/{id}", planModel);

        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案更新(操作员页面)失败");
        }else {
            int uid = StringToLongUtil.stringToInt(id);
            if (uid <= 0) {
                return Responses.errorResponse("错误!");
            }

            // 查询数据库相关记录
            NutritionPlanWithBLOBs waitToModify = nutritionPlanService.findPlanById(uid);
            if (waitToModify != null) {
                if (waitToModify.getIspassCheck() == '1') {
                    return Responses.errorResponse("该条记录已被审核，不能进行修改！");
                }
                //将planModel部分变量拆分传递给对象operator
                NutritionPlanWithBLOBs operator = new NutritionPlanWithBLOBs();
                operator.setId(uid);
                operator.setFactoryNum(planModel.getFactoryNum());
                operator.setFactoryName(planModel.getFactoryName());
                operator.setBuilding(planModel.getBuilding());
                operator.setNutritionT(planModel.getNutritionT());
                operator.setQuantity(planModel.getQuantity());
                operator.setAverage(planModel.getAverage());
                operator.setPeriod(planModel.getPeriod());
                operator.setWater(planModel.getWater());
                operator.setOperatorName(planModel.getOperatorName());
                operator.setOperatorId(planModel.getOperatorId());


                operator.setMaterialA(planModel.getMaterialA());
                operator.setMaterialM(planModel.getMaterialM());
                operator.setMaterialO(planModel.getMaterialO());
                operator.setMaterialWM(planModel.getMaterialWM());
                operator.setMaterialWO(planModel.getMaterialWO());
                operator.setRoughageP(planModel.getRoughageP());
                operator.setRoughageD(planModel.getRoughageD());
                operator.setRoughageO(planModel.getRoughageO());

                operator.setRoughageWP(planModel.getRoughageWP());
                operator.setRoughageWD(planModel.getRoughageWD());
                operator.setRoughageWO(planModel.getRoughageWO());
                operator.setPickingM(planModel.getPickingM());
                operator.setPickingR(planModel.getPickingR());
                operator.setPickingO(planModel.getPickingO());
                operator.setNutritionT(planModel.getNutritionT());
                operator.setIspassCheck(Byte.valueOf(String.valueOf(2)));


                int success = nutritionPlanService.changePlanSelective(operator);
                if (success <= 0) {
                    return Responses.errorResponse("操作员审核失败");
                }
                Response response = Responses.successResponse();
                HashMap<String, Object> data = new HashMap<>();
                data.put("success",success);
                response.setData(data);
                return response;

            } else {
                return Responses.errorResponse("无此记录信息，请检查后重试！");
            }
        }
    }

    /**
     * 获取该羊场的所有数据
     * @param size size
     * @param page page
     * @return Response
     */
    @Permit(authorities = "inquiry_phase_nutrition_file")
    @GetMapping(value = "/{id}")
    public Response findAllOfOneFactory(@PathVariable("id") String id,
                                        @RequestParam(value = "size", defaultValue = "10") String size,
                                        @RequestParam(value = "page", defaultValue = "0") String page,
                                        @RequestParam(value = "factoryName", defaultValue = "") String factoryName,
                                        @RequestParam(value = "ispassCheck", defaultValue = "-1") String ispassCheck, HttpServletRequest request) {
        logger.info("invoke findAllOfOneFactory, url is nutrition/{id} {}", id, size, page, factoryName, ispassCheck, request);
        if (size == null || page == null) {
            return Responses.errorResponse("失败");
        }
        int usize = StringToLongUtil.stringToInt(size);
        int upage = StringToLongUtil.stringToInt(page);
        byte pass = StringToLongUtil.stringToByte(ispassCheck);
        Long factoryOrAgentID = StringToLongUtil.stringToLong(id);
        Byte which = StringToLongUtil.stringToByte(TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION)));
        if (which == 0) {
            NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
            NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();
            criteria.andFactoryNumEqualTo(factoryOrAgentID);
            if (pass != -1) {
                criteria.andIsPassCheckEqualTo(pass);
            }
            List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample,new RowBounds(0, 1000000));

            List<NutritionPlanWithBLOBs> results = new ArrayList<>();
            for (int i = upage*usize; i < select.size() && i < upage*usize + usize; i++) {
                results.add(select.get(i));
            }

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("List",results);
            data.put("size", select.size());
            response.setData(data);
            return response;
        } else if (which == 1) {
            int count = 0;
            // user is a agent user
            NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
            NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();

            if (pass != -1) {
                criteria.andIsPassCheckEqualTo(pass);
            }
            // It's agent message
            // find his all subordinate factory
            Map<Long, List<Long> > factories = AgentUtil.getAllSubordinateFactory(String.valueOf(factoryOrAgentID));
            if (factories == null) {
                return Responses.errorResponse("request error!");
            }
            List<NutritionPlanWithBLOBs> plans = new ArrayList<>();

            // not direct people
            List<Long> allFactories = factories.get((long) 0);
            // direct people
            List<Long> directFactories = factories.get((long) -1);

            if (allFactories == null && directFactories == null) {
                return Responses.errorResponse("该代理没有发展羊场和代理！");
            }

            List<NutritionPlanWithBLOBs> theOne = new ArrayList<>();
            if (allFactories != null) {
                for (Long allFactory : allFactories) {
                    List<NutritionPlanWithBLOBs> temp = new ArrayList<>();
                    if (pass != -1) {
                        temp = nutritionPlanService.findAllRecords(allFactory, pass);
                    } else {
                        temp = nutritionPlanService.getAll(allFactory);
                    }
                    System.out.println("temp = " + temp.size());
                    theOne.addAll(temp);
                }
            }
            System.out.println("--------------------------------------------------------------------------------------------");
            count += theOne.size();

            List<NutritionPlanWithBLOBs> theOther = new ArrayList<>();
            if (directFactories != null) {
                for (Long directFactory : directFactories) {
                    List<NutritionPlanWithBLOBs> temp = new ArrayList<>();
                    if (pass != -1) {
                        temp = nutritionPlanService.findAllRecords(directFactory, pass);
                    } else {
                        temp = nutritionPlanService.getAll(directFactory);
                    }
                    System.out.println("temp = " + temp.size());
                    theOther.addAll(temp);
                }
            }
            count += theOther.size();

            System.out.println("new count = " + count);

            plans.addAll(theOther);
            plans.addAll(theOne);
            List<NutritionPlanWithBLOBs> results = new ArrayList<>();
            for (int i = upage*usize; i < plans.size() && i < upage*usize + usize; i++) {
                results.add(plans.get(i));
            }

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("List",results);
            data.put("size", count);
            data.put("directSize", theOther.size());
            response.setData(data);
            return response;
        } else {
            return Responses.errorResponse("permit error, user can not get factory's message");
        }
    }


    /**
     * 按主键查询的接口：/nutritionSelectById
     * 按主键查询的方法名：findPlanById()
     * 接收参数：整型的主键号（保留接口查询，前端不调用此接口）
     * @return response
     */
    @Permit(authorities = "inquiry_phase_nutrition_file")
    @GetMapping(value = "/find/{id}")
    public Response findPlanById(@PathVariable("id") String id) {
        logger.info("invoke findPlanById {}, url is nutrition/{id}", id);
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        //查询语句的写法：一定要在声明对象时把值直接赋进去
        NutritionPlanWithBLOBs selectById = nutritionPlanService.findPlanById(uid);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", selectById);
        response.setData(data);
        return response;
    }

    /**
     * 专家使用按主键修改的接口：/nutritionUpdateByProfessor
     * 专家使用按主键修改的方法名：changePlanByProfessor()
     * 专家使用接收参数：整个表单类型（整型id必填，各参数选填）
     * @param professorRequest request
     * @param bindingResult other
     * @return response
     */
    @Permit(authorities = "expert_review_nutrition_file")
    @PatchMapping(value = "/p/{id}")
    public Response changePlanByProfessor(@RequestBody @Valid ProfessorRequest professorRequest, @PathVariable("id") String id, BindingResult bindingResult) {
        logger.info("invoke changePlanByProfessor {}, url is nutrition/professor", professorRequest, id);
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案更新(专家页面)失败");
        }else {
            int uid = StringToLongUtil.stringToInt(id);
            if (uid == -1) {
                return Responses.errorResponse("error!");
            }
            NutritionPlanWithBLOBs professor = nutritionPlanService.findPlanById(uid);
            if (professor == null) return Responses.errorResponse("该记录不存在");
            if (professor.getIspassCheck() != 2) {
                return Responses.errorResponse("已经审核!");
            }

            // time of the professor modify
            professor.setId(uid);
            professor.setGmtModified(new Date());
            professor.setProfessorId(professorRequest.getProfessor().longValue());
            professor.setProfessorName(professorRequest.getName());
            professor.setUpassReason(professorRequest.getUnpassReason());
            professor.setIspassCheck(professorRequest.getIspassCheck());

            int success = nutritionPlanService.changePlanSelective(professor);
            if (success <= 0) {
                return Responses.errorResponse("错误");
            }
            String professorKey = this.factoryService.getAgentIDByFactoryNumber(Long.valueOf(professorRequest.getFactoryNum().toString())) + "_professor";;
            if (1 == professorRequest.getIspassCheck() && !JedisUtil.redisCancelProfessorSupervisorWorks(professorKey)) {
                return Responses.errorResponse("审核成功,短信服务器错误!");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", success);
            response.setData(data);
            return response;
        }
    }

    /**
     * 监督者使用按主键修改的接口：/nutritionUpdateBySupervisor
     * 监督者使用按主键修改的方法名：changePlanBySupervisor()
     * 监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
     * @param supervisorRequest supervisor
     * @param bindingResult bindingResult
     * @return response
     */
    @Permit(authorities = "supervision_review_phase_nutrition_answer")
    @PatchMapping(value = "/s/{id}")
    public Response changePlanBySupervisor(@RequestBody @Valid SupervisorRequest supervisorRequest, @PathVariable("id") String id, BindingResult bindingResult){
        logger.info("invoke changePlanBySupervisor {}, url is nutrition/supervisor", supervisorRequest);
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案更新(监督页面)失败");
        }else {
            int uid = StringToLongUtil.stringToInt(id);
            if (uid == -1) {
                return Responses.errorResponse("error!");
            }
            NutritionPlanWithBLOBs supervisor = nutritionPlanService.findPlanById(uid);
            if (supervisor == null) return Responses.errorResponse("该记录不存在");
            // modify the time of the supervised
            supervisor.setId(uid);
            supervisor.setGmtSupervised(new Date());
            supervisor.setSupervisorId(supervisorRequest.getSupervisor().longValue());
            supervisor.setSupervisorName(supervisorRequest.getName());
            supervisor.setIspassSup(supervisorRequest.getIspassSup());

            int success = nutritionPlanService.changePlanSelective(supervisor);
            if (success <= 0) {
                return Responses.errorResponse("失败");
            }
            String supervisorKey = supervisorRequest.getFactoryNum().toString() + "_supervisor";
            if (!JedisUtil.redisCancelProfessorSupervisorWorks(supervisorKey)){
                return Responses.errorResponse("审核成功,短信服务器错误!");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", success);
            response.setData(data);
            return response;
        }
    }
}
