package com.deep.api.resource.product_chain;

import com.deep.api.Utils.AgentUtil;
import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.Utils.TimeUtil;
import com.deep.api.Utils.TokenAnalysis;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.request.*;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.BreedingPlanAnotherModel;
import com.deep.domain.model.NutritionAnotherPlanModel;
import com.deep.domain.service.SheepInfo.BuildingFactoryService;
import com.deep.domain.service.SheepInfo.SheepInformationService;
import com.deep.domain.service.product_chain.BreedingPlanAnotherService;
import com.deep.domain.service.management_level.FactoryService;
import com.deep.domain.service.product_chain.NutritionPlanAnotherService;
import com.deep.domain.service.role_permit.UserService;
import com.deep.domain.util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

import static com.deep.domain.util.JedisUtil.getCertainKeyValue;

@RestController
@RequestMapping(value = "/breeding")
public class BreedingAnotherResource {
    private final Logger logger = LoggerFactory.getLogger(BreedingAnotherResource.class);
    @Resource
    private BreedingPlanAnotherService breedingPlanAnotherService;
    @Resource
    private NutritionPlanAnotherService nutritionPlanAnotherService;
    @Resource
    private FactoryService factoryService;
    @Resource
    private UserService userService;
    @Resource
    private BuildingFactoryService buildingFactoryService;
    @Resource
    private SheepInformationService sheepInformationService;

    /**
     * 查询某个羊场或者某个代理下面的所有记录（包括子代理）
     * @param page 页号
     * @param size 每页记录条数
     * @param factoryName 羊场name
     * @param ispassCheck pass
     * @param id 羊场号或者代理号，需要进行判断
     * @param request Request请求
     * @return Response响应
     */
    @Permit(authorities = "search_for_breeding_child_files")
    @GetMapping(value = "/{id}")
    public Response getAllRecords(@RequestParam(value = "page", defaultValue = "0") String page,
                                  @RequestParam(value = "size", defaultValue = "10") String size,
                                  @RequestParam(value = "factoryName", defaultValue = "") String factoryName,
                                  @RequestParam(value = "ispassCheck", defaultValue = "-1") String ispassCheck,
                                  @RequestParam(value = "startTime", defaultValue = "") String startTime,
                                  @RequestParam(value = "endTime", defaultValue = "") String endTime,
                                  @RequestParam(value = "earTag", defaultValue = "") String earTag,
                                  @PathVariable("id") String id, HttpServletRequest request) {
        logger.info("invoke getAllRecords, url is /b {}", id, page, size, factoryName, ispassCheck);
        Long uid = StringToLongUtil.stringToLong(id);
        Integer upage = StringToLongUtil.stringToInt(page);
        Byte usize = StringToLongUtil.stringToByte(size);
        byte pass = StringToLongUtil.stringToByte(ispassCheck);
        byte flag = Byte.parseByte(TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION)));
        if (uid < 0 || upage < 0 || usize < 0) {
            return Responses.errorResponse("错误");
        }
        HashMap<String, Object> data = new HashMap<>();
        List<Long> someResult = new ArrayList<>();
        if (flag == 0) {
            int count = 0;
            List<BreedingPlanAnotherModel> models;
            if (pass == -1 || pass == 0 || pass == 1 || pass == 2) {
                someResult.add(uid);
                count = breedingPlanAnotherService.queryCountBySelective(someResult, pass, factoryName, startTime, endTime, earTag);
                models = breedingPlanAnotherService.queryAllBySelective(someResult, upage, usize, pass, factoryName, startTime, endTime, earTag);
                data.put("List", models);
                data.put("size", count);
            }
            else {
                return Responses.errorResponse("参数错误!");
            }
        } else if (flag == 1) {
            if (pass  != -1 && pass != 0 && pass != 1 && pass != 2) return Responses.errorResponse("参数错误!");
            // 如果是代理，查询下级所有的羊场
            Map<Long, List<Long> > factories = AgentUtil.getAllSubordinateFactory(String.valueOf(uid));
            if (factories == null) {
                return Responses.errorResponse("当前代理下没有数据");
            } else {
                List<BreedingPlanAnotherModel> models = new ArrayList<>();
                Integer directCount = 0, undirectCount = 0;         // record number
                // direct factories
                List<Long> directFactories = factories.get((long)-1);
                // undirect factories
                List<Long> undirectFactories = factories.get((long)0);
                // all factories
                List<Long> allFactories = new ArrayList<>();
                if (directFactories == null && undirectFactories == null) {
                    return Responses.errorResponse("该代理没有发展羊场和代理！");
                }
                if (directFactories != null && directFactories.size() != 0) {
                    allFactories.addAll(directFactories);
                    directCount = breedingPlanAnotherService.queryCountBySelective(directFactories, pass, factoryName, startTime, endTime, earTag);
                    System.out.println("directCount = " + directCount);
                }
                if (undirectFactories != null && undirectFactories.size() != 0) {
                    allFactories.addAll(undirectFactories);
                    System.out.println(undirectFactories.toString());
                    undirectCount = breedingPlanAnotherService.queryCountBySelective(undirectFactories, pass, factoryName, startTime, endTime, earTag);
                    System.out.println("undirectCount = " + undirectCount);
                }
                models = breedingPlanAnotherService.queryAllBySelective(allFactories, upage, usize, pass, factoryName, startTime, endTime, earTag);
                data.put("List", models);
                data.put("size", directCount + undirectCount);
                data.put("directSize", directCount);
            }
        } else {
            return Responses.errorResponse("没有权限查询相关信息");
        }
        return Responses.successResponse(data);
    }

    /**
     * 查询一条记录
     * @param id 记录主键
     * @return Response相关信息
     */
    @Permit(authorities = "search_for_breeding_child_files")
    @GetMapping(value = "/find/{id}")
    public Response findARecord(@PathVariable("id") String id) {
        logger.info("invoke findARecord {}, url is b/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
        if (model == null) {
            return Responses.errorResponse("错误！");
        }

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", model);
//
//        Date date = null, nextDate = null;
//        // 获取执行妊娠前期营养标准
//        Timestamp beforePregnancy = model.getNutritionBeforePregnancy();
//        if (beforePregnancy != null) {
//            date = TimeUtil.Translate(beforePregnancy);
//            nextDate = TimeUtil.getNextDay(date);
//            List<NutritionPlanWithBLOBs> nutritionBeforePregnancy = nutritionPlanService.findPlanBetweenTimes(date, nextDate, model.getFactoryNumber());
//            data.put("beforePregnancy", nutritionBeforePregnancy);
//            data.put("beforePregnancySize", nutritionBeforePregnancy.size());
//        }
//
//        // 获取执行妊娠后期营养标准
//        Timestamp afterPregnancy = model.getNutritionAfterPregnancy();
//        if (afterPregnancy != null) {
//            date = TimeUtil.Translate(afterPregnancy);
//            nextDate = TimeUtil.getNextDay(date);
//            List<NutritionPlanWithBLOBs> nutritionAfterPregnancy = nutritionPlanService.findPlanBetweenTimes(date, nextDate, model.getFactoryNumber());
//            data.put("afterPregnancy", nutritionAfterPregnancy);
//            data.put("afterPregnancySize", nutritionAfterPregnancy.size());
//        }
//
//        // 执行产前营养标准
//        Timestamp beforeLambing = model.getNutritionBeforeLambing();
//        if (beforeLambing != null) {
//            date = TimeUtil.Translate(beforeLambing);
//            nextDate = TimeUtil.getNextDay(date);
//            List<NutritionPlanWithBLOBs> nutritionBeforeLambing = nutritionPlanService.findPlanBetweenTimes(date, nextDate, model.getFactoryNumber());
//            data.put("beforeLambing", nutritionBeforeLambing);
//            data.put("beforeLambingSize", nutritionBeforeLambing.size());
//        }
//
//        // 执行哺乳期营养标准（产后一周）
//        Timestamp breastFeeding = model.getNutritionBreastFeeding();
//        if (breastFeeding != null) {
//            date = TimeUtil.Translate(breastFeeding);
//            nextDate = TimeUtil.getNextDay(date);
//            List<NutritionPlanWithBLOBs> nutritionBreastFeeding = nutritionPlanService.findPlanBetweenTimes(date, nextDate, model.getFactoryNumber());
//            data.put("breastFeeding", nutritionBreastFeeding);
//            data.put("breastFeedingSize", nutritionBreastFeeding.size());
//        }
//
//        // 执行羔羊代乳料营养标准（羔羊一月龄）
//        Timestamp insteadBreastFeeding = model.getNutritionInsteadBreastFeeding();
//        if (insteadBreastFeeding != null) {
//            date = TimeUtil.Translate(insteadBreastFeeding);
//            nextDate = TimeUtil.getNextDay(date);
//            List<NutritionPlanWithBLOBs> nutritionInsteadBreastFeeding = nutritionPlanService.findPlanBetweenTimes(date, nextDate, model.getFactoryNumber());
//            data.put("insteadBreastFeeding", nutritionInsteadBreastFeeding);
//            data.put("insteadBreastFeedingSize", nutritionInsteadBreastFeeding.size());
//        }
//
//        // 执行断奶前母羊营养标准
//        Timestamp beforeCutBreastFeeding = model.getNutritionBeforeCutBreastFeeding();
//        if (beforeCutBreastFeeding != null) {
//            date = TimeUtil.Translate(beforeCutBreastFeeding);
//            nextDate = TimeUtil.getNextDay(date);
//            List<NutritionPlanWithBLOBs> nutritionBeforeCutBreastFeeding = nutritionPlanService.findPlanBetweenTimes(date, nextDate, model.getFactoryNumber());
//            data.put("beforeCutBreastFeeding", nutritionBeforeCutBreastFeeding);
//            data.put("beforeCutBreastFeedingSize", nutritionBeforeCutBreastFeeding.size());
//        }
//
//        // 执行羔羊断奶期营养标准
//        Timestamp cutBreastFeeding = model.getNutritionCutBreastFeeding();
//        if (cutBreastFeeding != null) {
//            date = TimeUtil.Translate(cutBreastFeeding);
//            nextDate = TimeUtil.getNextDay(date);
//            List<NutritionPlanWithBLOBs> nutritionCutBreastFeeding = nutritionPlanService.findPlanBetweenTimes(date, nextDate, model.getFactoryNumber());
//            data.put("CutBreastFeeding", nutritionCutBreastFeeding);
//            data.put("CutBreastFeedingSize", nutritionCutBreastFeeding.size());
//        }
        response.setData(data);
        return response;
    }

    /**
     * 查询对应营养档案
     * @return response
     */
    @Permit(authorities = "search_for_breeding_child_files")
    @PostMapping(value = "/findN")
    public Response findANutritionRecord(@RequestBody @Valid BreedingNutritionRequest breedingNutritionRequest, BindingResult bindingResult) {
        logger.info("invoke findARecord {}, url is b/findN", breedingNutritionRequest.toString());
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("参数错误");
            HashMap<String, Object> data = new HashMap<>();
            data.put("errMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        Date date = null, nextDate = null;
        if (breedingNutritionRequest.getTime() == null) {
            return Responses.errorResponse("查询时间格式不对");
        }
        // 获取执行妊娠前期营养标准
        date = TimeUtil.TranslateToDate(breedingNutritionRequest.getTime());
        nextDate = TimeUtil.getNextDay(date);
        List<NutritionAnotherPlanModel> models = nutritionPlanAnotherService.selectRecordByNutritionT(date, nextDate, Long.valueOf(breedingNutritionRequest.getFactoryNumber()));
        data.put("List", models);
        data.put("size", models.size());
        response.setData(data);
        return response;
    }

    /**
     * 操作员添加一条记录
     * @param breedingRequest 添加对象模型
     * @param bindingResult 验证信息
     * @return response 响应
     */
    @Permit(authorities = "increase_breeding_maternity_file")
    @PostMapping(value = "")
    public Response addARecord(@Valid @RequestBody BreedingRequest breedingRequest, BindingResult bindingResult) {
        logger.info("invoke addARecord {}", breedingRequest.toString());
        HashMap<String, Object> data = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("请规范表单信息！");
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }

        if (breedingRequest.getBuildingAfterBreeding() != null) {
            // 设置配种后移至栏栋
            System.out.println(breedingRequest.getBuildingAfterBreeding().length());
            // 栏的信息：col
            int col = breedingRequest.getBuildingAfterBreeding().charAt(0) - '0';
            // 栋的信息：building
            int building = breedingRequest.getBuildingAfterBreeding().charAt(3) - '0';
            System.out.println("col = " + col + " and building = " + building);

            BCRequest bcRequest = new BCRequest();
            bcRequest.setFactory(Long.valueOf(String.valueOf(breedingRequest.getFactoryNum())));
            bcRequest.setBuilding(building);
            bcRequest.setColNum(col);

            // 根据商标耳牌号获取羊的耳牌号码
            Long sheepId = sheepInformationService.getSheepIdByTradeMarkTag(breedingRequest.getRamSheepTrademark(), bcRequest.getFactory());
            if (sheepId == null) {
                return Responses.errorResponse("错误，羊只信息不存在");
            }

            Long updateBreeding = buildingFactoryService.moveSheep(bcRequest, sheepId);
            // 设置待产后移至栏栋
            Long locationBreeding = buildingFactoryService.moveSheep(bcRequest, sheepId);
            if (updateBreeding == null) {
                return Responses.errorResponse("配种后移至栏栋错误，待搬迁栏栋不存在！");
            } else if (locationBreeding == null) {
                return Responses.errorResponse("配种后移至栏栋错误，待搬迁栏栋不存在！");
            } else if (updateBreeding < 0) {
                return Responses.errorResponse("待产栏栋错误，待搬迁栏栋失败！");
            } else if (locationBreeding < 0) {
                return Responses.errorResponse("待产栏栋错误，待搬迁栏栋失败！");
            }
        }

        breedingRequest.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        breedingRequest.setGmtModify(new Timestamp(System.currentTimeMillis()));
        breedingRequest.setOperatorTime(new Timestamp(System.currentTimeMillis()));

        Long success = breedingPlanAnotherService.addARecordByOperator(breedingRequest);
        if (success > 0) {
            data.put("success", success);
            short agentID = this.factoryService.queryOneAgentByID(breedingRequest.getFactoryNum().longValue());
            String professorKey = agentID + "_professor";
            String supervisorKey = breedingRequest.getFactoryNum().toString() + "_supervisor";
            String testSendProfessor = agentID + "_professor_AlreadySend";
            String testSendSupervisor = breedingRequest.getFactoryNum().toString() + "_supervisor_AlreadySend";
            JedisUtil.redisSaveProfessorSupervisorWorks(professorKey);
            JedisUtil.redisSaveProfessorSupervisorWorks(supervisorKey);
            System.out.println(testSendProfessor);
            System.out.println(professorKey);

            if( !("1".equals(getCertainKeyValue(testSendProfessor)))) {
                if( JedisUtil.redisJudgeTime(professorKey) ) {
                    System.out.println("in redis:");
                    List<String> phone = userService.getProfessorTelephoneByFactoryNum(BigInteger.valueOf(breedingRequest.getFactoryNum()));
                    System.out.println("send");
                    if (phone.size() != 0) {
                        if (JedisUtil.redisSendMessage(phone, getCertainKeyValue("Message"))) {
                            JedisUtil.setCertainKeyValueWithExpireTime(testSendProfessor, "1", Integer.parseInt(Objects.requireNonNull(getCertainKeyValue("ExpireTime"))) * 24 * 60 * 60);
                        }
                    }
                }
            }
            if( !("1".equals(getCertainKeyValue(testSendSupervisor)))) {
                if(JedisUtil.redisJudgeTime(supervisorKey)) {
                    List<String> phone = userService.getSuperiorTelephoneByFactoryNum(BigInteger.valueOf(breedingRequest.getFactoryNum()));
                    if (phone.size() != 0) {
                        if( JedisUtil.redisSendMessage(phone, getCertainKeyValue("Message"))) {
                            JedisUtil.setCertainKeyValueWithExpireTime(testSendSupervisor,"1",Integer.parseInt(Objects.requireNonNull(getCertainKeyValue("ExpireTime")))*24*60*60);
                        }
                    }
                    System.out.println("send");
                }
            }

            return Responses.successResponse(data);
        }
        return Responses.errorResponse("添加失败");
    }

    /**
     * 操作员修改信息
     * @param id 记录主键
     * @param breedingModifyRequest 添加对象模型
     * @param bindingResult 验证信息
     * @return response 响应
     */
    @Permit(authorities = "modify_breeding_seed_file")
    @PatchMapping(value = "/{id}")
    public Response addARecordFirst(@PathVariable("id")String id,  @Valid @RequestBody BreedingModifyRequest breedingModifyRequest, BindingResult bindingResult) {
        logger.info("invoke addARecordFirst {}", breedingModifyRequest.toString());
        HashMap<String, Object> data = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("操作员修改信息失败！");
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        long uid = StringToLongUtil.stringToLong(id);
        if (uid <= 0) {
            return Responses.errorResponse("修改第一阶段信息失败！");
        }
        // 首先查询是否符合修改信息的条件
        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
        if (model == null || model.getIspassCheck() == 1) {
            return Responses.errorResponse("该条记录已经审核过!");
        }
        breedingModifyRequest.setGmtModify(new Timestamp(System.currentTimeMillis()));
        breedingModifyRequest.setOperatorTime(new Timestamp(System.currentTimeMillis()));
        Long success = breedingPlanAnotherService.updateARecordFirstByOperator(breedingModifyRequest, uid);
        if (success > 0) {
            data.put("success", success);
            return Responses.successResponse(data);
        }
        return Responses.errorResponse("修改第一阶段信息失败");
    }

    /**
     * 操作员删除一条记录
     * @param id 记录主键
     * @return response响应
     */
    @Permit(authorities = "delete_breeding_child_files")
    @DeleteMapping(value = "/{id}")
    public Response deleteARecord(@PathVariable("id") String id){
        logger.info("invoke deleteARecord {}, url is b/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("错误");
        }
        // 首先查询是否符合修改信息的条件
        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
        if (model != null && model.getIspassCheck() != 1) {
            long success = breedingPlanAnotherService.deleteARecord(uid);
            if (success <= 0) {
                return Responses.errorResponse("删除失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", success);
            response.setData(data);
            return response;
        }
        return Responses.errorResponse("该记录已经审核过, 不能删除!");
    }

    /**
     * 监督员审核一条记录
     * @param id 修改记录的主键
     * @return response相应信息
     */
    @Permit(authorities = "supervise_auditing_breeding_child_files")
    @PatchMapping(value = "/s/{id}")
    public Response supervisorARecordFirst(@PathVariable("id")String id, @Valid @RequestBody SupervisorRequest supervisorRequest, BindingResult bindingResult) {
        logger.info("invoke supervisorARecordFirst {}, url is b/s/{id}", id, supervisorRequest.toString(), bindingResult.toString());
        if (bindingResult.hasErrors()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            Response response = Responses.errorResponse("监督员审核错误！");
            response.setData(data);
            return response;
        }
        long uid = StringToLongUtil.stringToLong(id);
        if (uid < 0) {
            return Responses.errorResponse("监督员审核错误!");
        }
        // 首先查询是否符合修改信息的条件
        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
        if (model == null || model.getIspassSup() != 2) {
            return Responses.errorResponse("该记录已经审核过!");
        }
        Long success = breedingPlanAnotherService.updateARecordBySupervisor(uid, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), supervisorRequest.getIspassSup(), supervisorRequest.getSupervisor(), supervisorRequest.getName());
        if (success <= 0) {
            return Responses.errorResponse("监督员审核错误！");
        } else {
            String supervisorKey = supervisorRequest.getFactoryNum().toString() + "_supervisor";
            if (!JedisUtil.redisCancelProfessorSupervisorWorks(supervisorKey)){
                return Responses.errorResponse("审核成功,短信服务器错误");
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", "监督员审核成功！");
            return Responses.successResponse(data);
        }
    }

    /**
     * 技术员审核一条记录
     * @param id 修改记录的主键
     * @return response相应信息
     */
    @Permit(authorities = "experts_review_mating_and_childbirth_files")
    @PatchMapping(value = "/p/{id}")
    public Response professorARecordFirst(@PathVariable("id")String id, @Valid @RequestBody ProfessorRequest professorRequest, BindingResult bindingResult) {
        logger.info("invoke professorARecordFirst {}, url is b/p/{id}", id, professorRequest.toString(), bindingResult.toString());
        if (bindingResult.hasErrors()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            Response response = Responses.errorResponse("技术员审核错误！");
            response.setData(data);
            return response;
        }
        long uid = StringToLongUtil.stringToLong(id);
        if (uid < 0) {
            return Responses.errorResponse("error!");
        }
        // 首先查询是否符合修改信息的条件
        BreedingPlanAnotherModel model = breedingPlanAnotherService.findARecord(uid);
        if (model == null || model.getIspassCheck() != 2) {
            return Responses.errorResponse("该记录已经审核过!");
        }
        Long success = breedingPlanAnotherService.updateARecordByProfessor(uid, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), professorRequest.getIspassCheck(), professorRequest.getProfessor(), professorRequest.getName(), professorRequest.getUnpassReason());
        if (success <= 0) {
            return Responses.errorResponse("技术员审核错误！");
        } else {
            String professorKey = this.factoryService.getAgentIDByFactoryNumber(Long.valueOf(professorRequest.getFactoryNum().toString())) + "_professor";
            if (1 == professorRequest.getIspassCheck() && !JedisUtil.redisCancelProfessorSupervisorWorks(professorKey)) {
                return Responses.errorResponse("审核成功,短信服务器错误");
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", "技术员审核成功！");
            return Responses.successResponse(data);
        }
    }
}
