package com.deep.domain.service;

import com.deep.domain.model.DiagnosisPlanExample;
import com.deep.domain.model.DiagnosisPlanWithBLOBs;
import com.deep.infra.persistence.sql.mapper.DiagnosisPlanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author: Created  By  Caojiawei
 * date: 2018/2/18  11:19
 */
@Service
public class DiagnosisPlanService {
    @Resource
    private DiagnosisPlanMapper diagnosisPlanMapper;

    public int addPlan(DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs){
        int add = this.diagnosisPlanMapper.insert(diagnosisPlanWithBLOBs);
        return add;
    }
    public int dropPlan(Integer id){
        int drop = this.diagnosisPlanMapper.deleteByPrimaryKey(id);
        return drop;
    }
    public int changePlanSelective(DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs){
        int change = this.diagnosisPlanMapper.updateByPrimaryKeySelective(diagnosisPlanWithBLOBs);
        return change;
    }
    public DiagnosisPlanWithBLOBs findPlanById(Integer id){
        DiagnosisPlanWithBLOBs find = this.diagnosisPlanMapper.selectByPrimaryKey(id);
        return find;
    }
    public List<DiagnosisPlanWithBLOBs> findPlanSelective(DiagnosisPlanExample diagnosisPlanExample){
        List<DiagnosisPlanWithBLOBs> find = this.diagnosisPlanMapper.selectByExampleWithBLOBs(diagnosisPlanExample);
        return find;
    }
}
