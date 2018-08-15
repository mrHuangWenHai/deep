package com.deep.domain.service.product_chain;

import com.deep.api.request.NutritionRequest;
import com.deep.domain.model.NutritionPlanExample;
import com.deep.domain.model.NutritionPlanWithBLOBs;
import com.deep.infra.persistence.sql.mapper.product_chain.NutritionPlanMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * author: Created  By  Caojiawei
 * date: 2018/2/21  19:36
 */
@Service
public class NutritionPlanService {
    @Resource
    private NutritionPlanMapper nutritionPlanMapper;

    public int getNutritionPlanModelCount(Long factoryNum) {
        return this.nutritionPlanMapper.getNutritionPlanModelCount(factoryNum);
    }

    public Long getImmunePlanModelOperator(Long id) {
        return this.nutritionPlanMapper.getImmunePlanModelOperator(id);
    }

    public int addPlan(NutritionPlanWithBLOBs nutritionPlanWithBLOBs){
        return this.nutritionPlanMapper.insert(nutritionPlanWithBLOBs);
    }

    public int dropPlan(Integer id){
        return this.nutritionPlanMapper.deleteByPrimaryKey(id);
    }

    public int changePlanSelective(NutritionPlanWithBLOBs nutritionPlanWithBLOBs){
        return this.nutritionPlanMapper.updateByPrimaryKeySelective(nutritionPlanWithBLOBs);
    }

    public NutritionPlanWithBLOBs findPlanById(Integer id){
        return this.nutritionPlanMapper.selectByPrimaryKey(id);
    }

    public List<NutritionPlanWithBLOBs> findPlanSelective(NutritionPlanExample nutritionPlanExample, RowBounds rowBounds){
        return this.nutritionPlanMapper.selectByExampleWithBLOBsWithRowbounds(nutritionPlanExample,rowBounds);
    }

    public List<NutritionPlanWithBLOBs> findPlanBetweenTimes(Date date1, Date date2, Integer number) {
        return this.nutritionPlanMapper.selectByTwoDate(date1, date2, number);
    }
// Long factory, Byte pass, String startTime, String endTime, String factoryName, String earTag
    public List<NutritionPlanWithBLOBs> findAllRecords(NutritionRequest nutritionRequest) {
        return this.nutritionPlanMapper.findAllRecords(nutritionRequest);
    }

    public List<NutritionPlanWithBLOBs> getAll(NutritionRequest nutritionRequest) {
        return this.nutritionPlanMapper.getAll(nutritionRequest);
    }
}
