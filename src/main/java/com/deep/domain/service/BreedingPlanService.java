package com.deep.domain.service;

import com.deep.domain.model.BreedingPlan;
import com.deep.domain.model.BreedingPlanExample;
import com.deep.infra.persistence.sql.mapper.BreedingPlanMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author: Created  By  Caojiawei
 * date: 2018/2/2  17:08
 */
@Service
public class BreedingPlanService {
    
    @Resource
    private BreedingPlanMapper breedingPlanMapper;
        public int addPlan(BreedingPlan breedingPlan){
            return this.breedingPlanMapper.insert(breedingPlan);
    }
    public int dropPlan(Integer id){
        return this.breedingPlanMapper.deleteByPrimaryKey(id);
    }
    public int changePlanSelective(BreedingPlan breedingPlan){
        return this.breedingPlanMapper.updateByPrimaryKeySelective(breedingPlan);
    }
    public BreedingPlan findPlanById(Integer id){
        return this.breedingPlanMapper.selectByPrimaryKey(id);
    }
    public List<BreedingPlan> findPlanSelective(BreedingPlanExample breedingPlanExample, RowBounds rowBounds){
        return this.breedingPlanMapper.selectByExampleWithRowbounds(breedingPlanExample,rowBounds);
    }
}

