package com.deep.api.resource;

import com.deep.api.resource.product_chain.DisinfectFilesResource;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.service.product_chain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.HashMap;

/**
 * Create By Zhongrui
 */
@RestController

@RequestMapping(value = "/count")
public class CountResource {

    @Resource
    private DisinfectFilesService disinfectFilesService;

    @Resource
    private ImmunePlanService immunePlanService;

    @Resource
    private NutritionPlanService nutritionPlanService;

    @Resource
    private RepellentPlanService repellentPlanService;

    @Resource
    private OperationFileService operationFileService;
    private final Logger logger = LoggerFactory.getLogger(DisinfectFilesResource.class);

    // 查询所有模块未审核条数
    @GetMapping(value = "/")
    public Response count(@RequestParam("factoryNum") BigInteger factoryNum) {
        logger.info("invoke get /{} {}", factoryNum);
        int sum = this.disinfectFilesService.getDisinfectFilesModelCount(factoryNum) +
                this.immunePlanService.getImmunePlanModelCount(factoryNum) +
                this.nutritionPlanService.getNutritionPlanModelCount(factoryNum.longValue()) +
                this.repellentPlanService.getRepellentPlanModelCount(factoryNum) +
                this.operationFileService.getOperationFileCount(factoryNum);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("tips", sum);
        return Responses.successResponse(map);
    }
}
