package com.deep.domain.service;


import com.deep.api.request.RepellentRequest;
import com.deep.domain.model.RepellentPlanModel;
import com.deep.infra.persistence.sql.mapper.RepellentPlanMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class RepellentPlanService {
    @Resource
    private RepellentPlanMapper repellentPlanMapper;
    public int setRepellentPlanModel(RepellentPlanModel repellentPlanModel) {
        return this.repellentPlanMapper.setRepellentPlanModel(repellentPlanModel);
    }


    public List<RepellentPlanModel> getRepellentPlanModel(RepellentRequest repellentPlanModel,
                                                          RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModel(repellentPlanModel,bounds);
    }

    public int updateRepellentPlanModelByProfessor(RepellentPlanModel repellentPlanModel){
        return this.repellentPlanMapper.updateRepellentPlanModelByProfessor(repellentPlanModel);
    }

    public int updateRepellentPlanModelBySupervisor(RepellentPlanModel repellentPlanModel){
        return this.repellentPlanMapper.updateRepellentPlanModelBySupervisor(repellentPlanModel);
    }

    public int updateRepellentPlanModelByOperator(RepellentPlanModel repellentPlanModel){
        return this.repellentPlanMapper.updateRepellentPlanModelByOperator(repellentPlanModel);
    }

    public int deleteRepellentPlanModelByid(Long id){
        return this.repellentPlanMapper.deleteRepellentPlanModelByid(id);
    }

}
