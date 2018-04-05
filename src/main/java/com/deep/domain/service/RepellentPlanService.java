package com.deep.domain.service;

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
    public void setRepellentPlanModel(RepellentPlanModel repellentPlanModel){
        this.repellentPlanMapper.setRepellentPlanModel(repellentPlanModel);
    }

    public List<RepellentPlanModel> getRepellentPlanModel(RepellentPlanModel repellentPlanModel,
                                                          RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModel(repellentPlanModel,bounds);
    }

    public RepellentPlanModel getRepellentPlanModelByfactoryNumAndcrowdNumAndrepellentTimeAndrepellentName(BigInteger factoryNum,String crowdNum,String repellentTime,String repellentName){
        return this.repellentPlanMapper.getRepellentPlanModelByfactoryNumAndcrowdNumAndrepellentTimeAndrepellentName(factoryNum, crowdNum, repellentTime, repellentName);
    }

    public List<RepellentPlanModel> getRepellentPlanModelByProfessor(Integer isPass1, RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModelByProfessor(isPass1,bounds);
    }

    public List<RepellentPlanModel> getRepellentPlanModelBySupervisor(Integer isPass2, RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModelBySupervisor(isPass2,bounds);
    }

    public RepellentPlanModel getRepellentPlanModelByid(Long id){
        return this.repellentPlanMapper.getRepellentPlanModelByid(id);
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
