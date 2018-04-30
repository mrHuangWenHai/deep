package com.deep.api.resource;


import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.request.NutritionPlanModel;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.*;
import com.deep.domain.service.NutritionPlanService;
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
 * date: 2018/2/21  19:34
 */
@RestController

@RequestMapping(value = "nutrition")
public class NutritionResource {

    private final Logger logger = LoggerFactory.getLogger(NutritionResource.class);


    @Resource
    private NutritionPlanService nutritionPlanService;

    /**
     * 插入接口：/nutritionInsert
     * 按主键插入的方法名：addPlan()
     * 接收参数： json格式类型
     * 参数类型为：
     * Long factoryNum; String building;Date nutritionT;Long quantity;String average;String period;String water;String operator;String remark;
     * String materialA;String materialM;String materialO;String materialWM;String materialWO;String roughageP;String roughageD;String roughageWP;String roughageWD;String roughageWO;String pickingM;String pickingR;String pickingO;
     * @param planModel
     * @param bindingResult
     * @return
     * @throws ParseException
     */
    @PostMapping(value = "")
    public Response addPlan(@RequestBody @Valid NutritionPlanModel planModel, BindingResult bindingResult) throws ParseException {

        logger.info("invoke addPlan {}, url is nutrition", planModel);

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


            Byte zero = 2;
            insert.setGmtCreate(new Date());
            insert.setIspassCheck(zero);
            insert.setIspassSup(zero);

            int success = nutritionPlanService.addPlan(insert);
            if (success <= 0) {
                return Responses.errorResponse("插入失败");
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

     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Response dropPlan(@PathVariable("id") String id){
        logger.info("invoke dropPlan {}, url is nutrition/{id}", id);
        int uid = StringToLongUtil.stringToInt(id);
        if (uid == -1) {
            return Responses.errorResponse("错误");
        }
        int success = nutritionPlanService.dropPlan(uid);
        if (success <= 0) {
            return Responses.errorResponse("删除失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("success", success);
        response.setData(data);
        return response;
    }

    /**
     * 操作员使用按主键修改的接口：/nutritionUpdateByOperator
     * 操作员使用按主键修改的方法名：changePlanByOperator()
     * 操作员使用接收参数：整个表单类型（整型id必填，各参数选填）
     * @param planModel
     * @param bindingResult
     * @return
     * @throws ParseException
     */

    @PatchMapping(value = "/{id}")
    public Response changePlanByOperator(@RequestBody @Valid NutritionPlanModel planModel, @PathVariable("id") String id, BindingResult bindingResult) throws ParseException {
        logger.info("invoke changePlanByOperator {}, url is nutrition/{id}", planModel);

        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案更新(操作员页面)失败");
        }else {
            int uid = StringToLongUtil.stringToInt(id);
            if (uid <= 0) {
                return Responses.errorResponse("错误!");
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


            int success = nutritionPlanService.changePlanSelective(operator);
            if (success <= 0) {
                return Responses.errorResponse("操作员审核失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success",success);
            response.setData(data);
            return response;
        }
    }

    /**
     * 按主键查询的接口：/nutritionSelectById
     * 按主键查询的方法名：findPlanById()
     * 接收参数：整型的主键号（保留接口查询，前端不调用此接口）

     * @return
     */
    @GetMapping(value = "/{id}")
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
     * 获取该羊场的所有数据
     * @param factoryNum
     * @param size
     * @param page
     * @return
     */
    @GetMapping(value = "")
    public Response findAllOfOneFactory(@RequestParam(value = "factoryNum") Long factoryNum,
                                        @RequestParam(value = "size", defaultValue = "10") String size,
                                        @RequestParam(value = "page", defaultValue = "0") String page,
                                        @RequestParam(value = "factoryName", defaultValue = "") String factoryName,
                                        @RequestParam(value = "ispassCheck", defaultValue = "-1") String ispassCheck) {
        logger.info("invoke findAllOfOneFactory, url is nutrition");
        if (factoryNum == null || size == null || page == null) {
            return Responses.errorResponse("失败");
        }

        int usize = StringToLongUtil.stringToInt(size);
        int upage = StringToLongUtil.stringToInt(page);
        byte pass = StringToLongUtil.stringToByte(ispassCheck);

        System.out.println("usize = " + usize);
        System.out.println("upage = " + upage);
        System.out.println("pass = " + pass);

        NutritionPlanExample nutritionPlanExample = new NutritionPlanExample();
        NutritionPlanExample.Criteria criteria = nutritionPlanExample.createCriteria();

        criteria.andFactoryNumEqualTo(factoryNum);

        if (pass != -1) {
            criteria.andIsPassCheckEqualTo(pass);
        }

        List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample,new RowBounds(upage, usize));
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("List",select);
        data.put("size", select.size());
        response.setData(data);
        return response;

    }

    /**
     * 按条件查询接口：/nutritionSelective
     * 按条件查询方法名：findPlanSelective()
     * 接收的参数：前端的各参数，以及两个("s_nutritionT1")("s_nutritionT2")时间字符串（所有参数可以选填）
     * @param planModel
     * @param bindingResult
     * @return
     * @throws ParseException
     */

    @PostMapping(value = "/selective")
    public Response findPlanSelective(@RequestBody @Valid NutritionPlanModel planModel, BindingResult bindingResult) throws ParseException {
        logger.info("invoke findPlanSelective {}, url is nutrition/selective", planModel);

        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案(根据条件)查询失败");
        }else {
            //将planModel部分变量拆分传递给对象nutritionPlanWithBLOBs
            NutritionPlanWithBLOBs nutritionPlanWithBLOBs = new NutritionPlanWithBLOBs();
            nutritionPlanWithBLOBs.setId(planModel.getId());
            nutritionPlanWithBLOBs.setGmtCreate(planModel.getGmtCreate());
            nutritionPlanWithBLOBs.setGmtModified(planModel.getGmtModified());
            nutritionPlanWithBLOBs.setSupervisorName(planModel.getSupervisorName());
            nutritionPlanWithBLOBs.setFactoryNum(planModel.getFactoryNum());
            nutritionPlanWithBLOBs.setBuilding(planModel.getBuilding());
            nutritionPlanWithBLOBs.setNutritionT(planModel.getNutritionT());
            nutritionPlanWithBLOBs.setQuantity(planModel.getQuantity());
            nutritionPlanWithBLOBs.setAverage(planModel.getAverage());
            nutritionPlanWithBLOBs.setPeriod(planModel.getPeriod());
            nutritionPlanWithBLOBs.setWater(planModel.getWater());
            nutritionPlanWithBLOBs.setOperatorName(planModel.getOperatorName());
            nutritionPlanWithBLOBs.setProfessorName(planModel.getProfessorName());
            nutritionPlanWithBLOBs.setSupervisorName(planModel.getSupervisorName());
            nutritionPlanWithBLOBs.setRemark(planModel.getRemark());
            nutritionPlanWithBLOBs.setIspassCheck(planModel.getIspassCheck());
            nutritionPlanWithBLOBs.setUpassReason(planModel.getUpassReason());
            nutritionPlanWithBLOBs.setIspassSup(planModel.getIspassSup());
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
            if(nutritionPlanWithBLOBs.getOperatorName() != null && !nutritionPlanWithBLOBs.getOperatorName().isEmpty()){
                criteria.andOperatorNameEqualTo(nutritionPlanWithBLOBs.getOperatorName());
            }
            if(nutritionPlanWithBLOBs.getProfessorName() != null && !nutritionPlanWithBLOBs.getProfessorName().isEmpty()){
                criteria.andProfessorNameEqualTo(nutritionPlanWithBLOBs.getProfessorName());
            }
            if(nutritionPlanWithBLOBs.getSupervisorName() != null && !nutritionPlanWithBLOBs.getSupervisorName().isEmpty()){
                criteria.andSupervisorNameEqualTo(nutritionPlanWithBLOBs.getSupervisorName());
            }
            if(nutritionPlanWithBLOBs.getIspassCheck() != null && !nutritionPlanWithBLOBs.getIspassCheck().toString().isEmpty()){
                criteria.andIsPassCheckEqualTo(nutritionPlanWithBLOBs.getIspassCheck());
            }
            if(nutritionPlanWithBLOBs.getUpassReason() != null && !nutritionPlanWithBLOBs.getUpassReason().isEmpty()){
                criteria.andUpassReasonLike(nutritionPlanWithBLOBs.getUpassReason());
            }
            if(nutritionPlanWithBLOBs.getIspassSup() != null && !nutritionPlanWithBLOBs.getIspassSup().toString().isEmpty()){
                criteria.andIsPassSupEqualTo(nutritionPlanWithBLOBs.getIspassSup());
            }
            List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));

            if (select == null) {
                return Responses.errorResponse("错误");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("List", select);
            data.put("size", select.size());

            response.setData(data);
            return response;
        }
    }

    /**
     * 供技术审核查询信息: /nutritionSelectByProfessor
     * 供技术审核查询方法名：findPlanSelectBySupervisor()
     * 接收的参数：前端的各参数，（所有参数可以选填）
     * @param planModel
     * @param bindingResult
     * @return
     */

    @PostMapping(value = "/professor/selective")
    public Response findPlanSelectByProfessor(@RequestBody @Valid NutritionPlanModel planModel, BindingResult bindingResult) {
        logger.info("invoke findPlanSelectByProfessor {}, url is nutrition/professor/selective", planModel);

        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案(根据条件)查询失败");
        }else {
            //将planModel部分变量拆分传递给对象nutritionPlanWithBLOBs
            NutritionPlanWithBLOBs nutritionPlanWithBLOBs = new NutritionPlanWithBLOBs();
            nutritionPlanWithBLOBs.setId(planModel.getId());
            nutritionPlanWithBLOBs.setGmtCreate(planModel.getGmtCreate());
            nutritionPlanWithBLOBs.setGmtModified(planModel.getGmtModified());
            nutritionPlanWithBLOBs.setSupervisorName(planModel.getSupervisorName());
            nutritionPlanWithBLOBs.setFactoryNum(planModel.getFactoryNum());
            nutritionPlanWithBLOBs.setBuilding(planModel.getBuilding());
            nutritionPlanWithBLOBs.setNutritionT(planModel.getNutritionT());
            nutritionPlanWithBLOBs.setQuantity(planModel.getQuantity());
            nutritionPlanWithBLOBs.setAverage(planModel.getAverage());
            nutritionPlanWithBLOBs.setPeriod(planModel.getPeriod());
            nutritionPlanWithBLOBs.setWater(planModel.getWater());
            nutritionPlanWithBLOBs.setOperatorName(planModel.getOperatorName());
            nutritionPlanWithBLOBs.setProfessorName(planModel.getProfessorName());
            nutritionPlanWithBLOBs.setSupervisorName(planModel.getSupervisorName());
            nutritionPlanWithBLOBs.setRemark(planModel.getRemark());
            nutritionPlanWithBLOBs.setIspassCheck(planModel.getIspassCheck());
            nutritionPlanWithBLOBs.setUpassReason(planModel.getUpassReason());
            nutritionPlanWithBLOBs.setIspassSup(planModel.getIspassSup());
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
            if(nutritionPlanWithBLOBs.getProfessorName() != null && !nutritionPlanWithBLOBs.getProfessorName().isEmpty()){
                criteria.andProfessorNameEqualTo(nutritionPlanWithBLOBs.getProfessorName());
            }
            if(nutritionPlanWithBLOBs.getUpassReason() != null && !nutritionPlanWithBLOBs.getUpassReason().isEmpty()){
                criteria.andUpassReasonLike(nutritionPlanWithBLOBs.getUpassReason());
            }
            criteria.andIsPassCheckEqualTo(notPassed);
            List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));

            if (select == null) {
                return Responses.errorResponse("错误");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("List", select);
            data.put("size", select.size());

            response.setData(data);
            return response;
        }
    }

    /**
     * 供监督者查询信息:nutritionSelectBySupervisor
     * 供监督者查询方法名：findPlanSelectBySupervisor()
     * 接收的参数：前端的各参数，（所有参数可以选填）
     * @param planModel
     * @param bindingResult
     * @return
     */

    @PostMapping(value = "/supervisor/selective")
    public Response findPlanSelectBySupervisor(@RequestBody @Valid NutritionPlanModel planModel, BindingResult bindingResult) {
        logger.info("invoke findPlanSelectBySupervisor {}, url is nutrition/supervisor/selective", planModel);

        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案(根据条件)查询失败");
        }else {
            //将planModel部分变量拆分传递给对象nutritionPlanWithBLOBs
            NutritionPlanWithBLOBs nutritionPlanWithBLOBs = new NutritionPlanWithBLOBs();
            nutritionPlanWithBLOBs.setId(planModel.getId());
            nutritionPlanWithBLOBs.setGmtCreate(planModel.getGmtCreate());
            nutritionPlanWithBLOBs.setGmtModified(planModel.getGmtModified());
            nutritionPlanWithBLOBs.setSupervisorName(planModel.getSupervisorName());
            nutritionPlanWithBLOBs.setFactoryNum(planModel.getFactoryNum());
            nutritionPlanWithBLOBs.setBuilding(planModel.getBuilding());
            nutritionPlanWithBLOBs.setNutritionT(planModel.getNutritionT());
            nutritionPlanWithBLOBs.setQuantity(planModel.getQuantity());
            nutritionPlanWithBLOBs.setAverage(planModel.getAverage());
            nutritionPlanWithBLOBs.setPeriod(planModel.getPeriod());
            nutritionPlanWithBLOBs.setWater(planModel.getWater());
            nutritionPlanWithBLOBs.setOperatorName(planModel.getOperatorName());
            nutritionPlanWithBLOBs.setProfessorName(planModel.getProfessorName());
            nutritionPlanWithBLOBs.setSupervisorName(planModel.getSupervisorName());
            nutritionPlanWithBLOBs.setRemark(planModel.getRemark());
            nutritionPlanWithBLOBs.setIspassCheck(planModel.getIspassCheck());
            nutritionPlanWithBLOBs.setUpassReason(planModel.getUpassReason());
            nutritionPlanWithBLOBs.setIspassSup(planModel.getIspassSup());
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
            criteria.andIsPassCheckEqualTo(notPassed1);
            List<NutritionPlanWithBLOBs> select = nutritionPlanService.findPlanSelective(nutritionPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",select);
            response.setData(data);
            return response;
        }
    }

    /**
     * 专家使用按主键修改的接口：/nutritionUpdateByProfessor
     * 专家使用按主键修改的方法名：changePlanByProfessor()
     * 专家使用接收参数：整个表单类型（整型id必填，各参数选填）
     * @param professor
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/professor")
    public Response changePlanByProfessor(@RequestBody @Valid NutritionPlanWithBLOBs professor, BindingResult bindingResult) {
        logger.info("invoke changePlanByProfessor {}, url is nutrition/professor", professor);
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案更新(专家页面)失败");
        }else {
            professor.setGmtModified(new Date());
            if (professor.getIspassCheck() == 1){
                professor.setUpassReason("操作员已经修改档案并通过技术审核");
            }
            int changeID = nutritionPlanService.changePlanSelective(professor);
            if (changeID <= 0) {
                return Responses.errorResponse("错误");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",changeID);
            response.setData(data);
            return response;
        }
    }

    /**
     * 监督者使用按主键修改的接口：/nutritionUpdateBySupervisor
     * 监督者使用按主键修改的方法名：changePlanBySupervisor()
     * 监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
     * @param supervisor
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/supervisor")
    public Response changePlanBySupervisor(@RequestBody @Valid NutritionPlanWithBLOBs supervisor, BindingResult bindingResult){
        logger.info("invoke changePlanBySupervisor {}, url is nutrition/supervisor", supervisor);
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案更新(监督页面)失败");
        }else {
            supervisor.setGmtSupervised(new Date());
            int changeID = nutritionPlanService.changePlanSelective(supervisor);
            if (changeID <= 0) {
                return Responses.errorResponse("失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("nutrition_plan",changeID);
            response.setData(data);
            return response;
        }
    }
}
