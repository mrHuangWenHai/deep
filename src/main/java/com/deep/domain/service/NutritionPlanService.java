package com.deep.domain.service;

import com.deep.domain.model.NutritionPlanExample;
import com.deep.domain.model.NutritionPlanWithBLOBs;
import com.deep.infra.persistence.sql.mapper.NutritionPlanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author: Created  By  Caojiawei
 * date: 2018/2/21  19:36
 */
@Service
public class NutritionPlanService {
    @Resource
    private NutritionPlanMapper nutritionPlanMapper;

    public int addPlan(NutritionPlanWithBLOBs nutritionPlanWithBLOBs){
        int add = this.nutritionPlanMapper.insert(nutritionPlanWithBLOBs);
        return add;
    }
    public int dropPlan(Integer id){
        int drop = this.nutritionPlanMapper.deleteByPrimaryKey(id);
        return drop;
    }
    public int changePlanByProfessor(NutritionPlanWithBLOBs nutritionPlanWithBLOBs){
        int change = this.nutritionPlanMapper.updateByPrimaryKeySelective(nutritionPlanWithBLOBs);
        return change;
    }
    public int changePlanBySupervisor(NutritionPlanWithBLOBs nutritionPlanWithBLOBs){
        int change = this.nutritionPlanMapper.updateByPrimaryKeySelective(nutritionPlanWithBLOBs);
        return change;
    }
    public NutritionPlanWithBLOBs findPlanById(Integer id){
        NutritionPlanWithBLOBs find = this.nutritionPlanMapper.selectByPrimaryKey(id);
        return find;
    }
    public List<NutritionPlanWithBLOBs> findPlanSelective(NutritionPlanExample nutritionPlanExample){
        List<NutritionPlanWithBLOBs> find = this.nutritionPlanMapper.selectByExampleWithBLOBs(nutritionPlanExample);
        return find;
    }

    public List<NutritionPlanWithBLOBs> findPlanSelectByDate(NutritionPlanExample nutritionPlanExample){
        List<NutritionPlanWithBLOBs> find = this.nutritionPlanMapper.selectByExampleWithBLOBs(nutritionPlanExample);
        return find;
    }
    public List<NutritionPlanWithBLOBs> findPlanSelectByProfessor(NutritionPlanExample nutritionPlanExample){
        List<NutritionPlanWithBLOBs> find = this.nutritionPlanMapper.selectByExampleWithBLOBs(nutritionPlanExample);
        return find;
    }
    public List<NutritionPlanWithBLOBs> findPlanSelectBySupervisor(NutritionPlanExample nutritionPlanExample){
        List<NutritionPlanWithBLOBs> find = this.nutritionPlanMapper.selectByExampleWithBLOBs(nutritionPlanExample);
        return find;
    }
}
