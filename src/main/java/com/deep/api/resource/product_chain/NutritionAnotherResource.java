package com.deep.api.resource.product_chain;

import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.Utils.TokenAnalysis;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.authorization.tools.Constants;
import com.deep.api.request.NutritionAddRequest;
import com.deep.api.request.ProfessorRequest;
import com.deep.api.request.SupervisorRequest;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.NutritionAnotherPlanModel;
import com.deep.domain.service.management_level.FactoryService;
import com.deep.domain.service.product_chain.NutritionPlanAnotherService;
import com.deep.domain.service.role_permit.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/nutrition")
public class NutritionAnotherResource {
    private final Logger logger = LoggerFactory.getLogger(NutritionAnotherResource.class);

    // 用于查询专家、监督员电话并抉择发送短信
    @Resource
    private UserService userService;

    // 用于查询羊场代理id
    @Resource
    private FactoryService factoryService;

    @Resource
    private NutritionPlanAnotherService nutritionPlanAnotherService;

    @Permit(authorities = "increase_phase_nutritional_profile")
    @PostMapping(value = "")
    public Response insert(@RequestBody @Valid NutritionAddRequest request, BindingResult bindingResult) {
        logger.info("invoke insert {}, url is nutrition", request.toString());
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案录入失败");
        }
        // 开始插入一条营养实施档案的记录
        if (nutritionPlanAnotherService.insertRecord(request)) {
            // TODO 发短信设置
            return Responses.successResponse();
        }
        return Responses.errorResponse("营养实施档案录入失败");
    }

    @Permit(authorities = "delete_stage_nutrition_file")
    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable("id") String id){
        logger.info("invoke delete {}, url is nutrition/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid <= 0) {
            return Responses.errorResponse("选定档案错误!");
        }
        if (nutritionPlanAnotherService.deleteRecord(uid)) {
            // TODO 发短信设置
            return Responses.successResponse();
        }
        return Responses.errorResponse("选定档案不存在或已被审核");
    }

    @Permit(authorities = "modification_phase_nutrition_file")
    @PutMapping(value = "/{id}")
    public Response updateOperator(@RequestBody @Valid NutritionAddRequest request, @PathVariable("id") String id, BindingResult bindingResult) {
        logger.info("invoke updateOperator {}, url is nutrition/{id}", request.toString());
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("营养实施档案更新(操作员页面)失败");
        }
        long uid = StringToLongUtil.stringToLong(id);
        if (uid <= 0) {
            return Responses.errorResponse("选定档案错误!");
        }
        if (nutritionPlanAnotherService.updateAllTermsOfOneRecord(request, uid)) {
            // TODO 发短信设置
            return Responses.successResponse();
        }
        return Responses.errorResponse("选定档案不存在或已被审核");
    }

    @Permit(authorities = "inquiry_phase_nutrition_file")
    @GetMapping(value = "/{id}")
    public Response selectRecords(@RequestParam(value = "size", defaultValue = "10") String size,
                                  @RequestParam(value = "page", defaultValue = "0") String page,
                                  @RequestParam(value = "factoryName", defaultValue = "") String factoryName,
                                  @RequestParam(value = "ispassCheck", defaultValue = "-1") String ispassCheck,
                                  @RequestParam(value = "startTime", defaultValue = "") String startTime,
                                  @RequestParam(value = "endTime", defaultValue = "") String endTime,
                                  @RequestParam(value = "earTag", defaultValue = "") String earTag,
                                  @PathVariable("id") String id, HttpServletRequest request) {
        System.out.println("size = " + size + ", page = " + page + ", factoryName = " + factoryName + ", ispassCheck = " + ispassCheck + "" +
                ", startTime = " + startTime + ", endTime = " + endTime + ", earTag = " + earTag);
        logger.info("invoke selectRecords, url is nutrition/{}", id, size, page, factoryName, ispassCheck, startTime, endTime, earTag, request.toString());
        if (size == null || page == null) {
            return Responses.errorResponse("查询参数错误");
        }
        int sizeObject = StringToLongUtil.stringToInt(size);
        int pageObject = StringToLongUtil.stringToInt(page);
        byte pass = StringToLongUtil.stringToByte(ispassCheck);
        long factoryOrAgentID = StringToLongUtil.stringToLong(id);
        byte flag = StringToLongUtil.stringToByte(TokenAnalysis.getFlag(request.getHeader(Constants.AUTHORIZATION)));

        List<NutritionAnotherPlanModel> models = new ArrayList<>();
        Long count = nutritionPlanAnotherService.countRecords(flag, factoryOrAgentID, factoryName, pass, startTime, endTime, earTag);
        if (count == null) count = 0L;
        else models = nutritionPlanAnotherService.selectRecords(flag, (long)pageObject*sizeObject, factoryOrAgentID, factoryName, pass, startTime, endTime, earTag);

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("List",models);
        data.put("size", count);

        if (flag == 1) {
            Long directCount = nutritionPlanAnotherService.countDirectRecords(factoryOrAgentID, factoryName, pass, startTime, endTime, earTag);
            if (directCount == null) directCount = 0L;
            data.put("directSize", directCount);
        }

        response.setData(data);
        return response;
    }

    @Permit(authorities = "inquiry_phase_nutrition_file")
    @GetMapping(value = "/find/{id}")
    public Response selectRecord(@PathVariable("id") String id) {
        logger.info("invoke selectRecord {}, url is nutrition/find/{id}", id);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询参数错误");
        }
        //查询语句的写法：一定要在声明对象时把值直接赋进去
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", nutritionPlanAnotherService.selectRecord(uid));
        response.setData(data);
        return response;
    }

    @Permit(authorities = "expert_review_nutrition_file")
    @PatchMapping(value = "/p/{id}")
    public Response updateProfessor(@RequestBody @Valid ProfessorRequest professorRequest,
                                    @PathVariable("id") String id, BindingResult bindingResult) {
        logger.info("invoke updateProfessor {}, url is nutrition/p", professorRequest, id);
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("专家审核参数错误！");
        }
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("专家审核参数错误！");
        }
        if (nutritionPlanAnotherService.updateProfessorTermsOfOneRecord(professorRequest, uid)) {
            // TODO 发短信设置
            return Responses.successResponse();
        }
        return Responses.errorResponse("专家审核营养实施档案错误！");
    }

    @Permit(authorities = "supervision_review_phase_nutrition_answer")
    @PatchMapping(value = "/s/{id}")
    public Response updateSupervisor(@RequestBody @Valid SupervisorRequest supervisorRequest, @PathVariable("id") String id, BindingResult bindingResult){
        logger.info("invoke updateSupervisor {}, url is nutrition/s", supervisorRequest);
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("监督员审核参数错误！");
        }
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("监督员审核参数错误！");
        }

        if (nutritionPlanAnotherService.updateSupervisorTermsOfOneRecord(supervisorRequest, uid)) {
            // TODO 发短信设置
            return Responses.successResponse();
        }
        return Responses.errorResponse("监督员审核营养实施档案错误");
    }
}
