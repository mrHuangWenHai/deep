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

    public int addPlan(BreedingPlan breedingPlan) {
        int add = this.breedingPlanMapper.insert(breedingPlan);
        return add;
    }

    public int dropPlan(Integer id) {
        int drop = this.breedingPlanMapper.deleteByPrimaryKey(id);
        return drop;
    }

    public int changePlanByProfessor(BreedingPlan professor) {
        int change = this.breedingPlanMapper.updateByPrimaryKeySelective(professor);
        return change;
    }

    public int changePlanBySupervisor(BreedingPlan supervisor) {
        int change = this.breedingPlanMapper.updateByPrimaryKeySelective(supervisor);
        return change;
    }

    public BreedingPlan findPlanById(Integer id) {
        BreedingPlan find = this.breedingPlanMapper.selectByPrimaryKey(id);
        return find;
    }

    public List<BreedingPlan> findPlanSelective(BreedingPlanExample breedingPlanExample) {
        List<BreedingPlan> find = this.breedingPlanMapper.selectByExample(breedingPlanExample);
        return find;

    }
}

