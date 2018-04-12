package com.deep.domain.service;

import com.deep.domain.model.DiagnosisPlanExample;
import com.deep.domain.model.DiagnosisPlanWithBLOBs;
import com.deep.infra.persistence.sql.mapper.DiagnosisPlanMapper;
import org.apache.ibatis.session.RowBounds;
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
        return this.diagnosisPlanMapper.insert(diagnosisPlanWithBLOBs);
    }
    public int dropPlan(Integer id){
        return this.diagnosisPlanMapper.deleteByPrimaryKey(id);
    }
    public int changePlanSelective(DiagnosisPlanWithBLOBs diagnosisPlanWithBLOBs){
        return this.diagnosisPlanMapper.updateByPrimaryKeySelective(diagnosisPlanWithBLOBs);
    }
    public DiagnosisPlanWithBLOBs findPlanById(Integer id){
        return this.diagnosisPlanMapper.selectByPrimaryKey(id);
    }
    public List<DiagnosisPlanWithBLOBs> findPlanSelective(DiagnosisPlanExample diagnosisPlanExample, RowBounds rowBounds){
        return this.diagnosisPlanMapper.selectByExampleWithBLOBsWithRowbounds(diagnosisPlanExample,rowBounds);
    }
}
