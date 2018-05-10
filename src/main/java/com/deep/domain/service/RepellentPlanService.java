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


    public List<RepellentPlanModel> getRepellentPlanModel(RepellentRequest repellentPlanModel){
        return this.repellentPlanMapper.getRepellentPlanModel(repellentPlanModel);
    }


    public RepellentPlanModel getRepellentPlanModelById(long id){
        return this.repellentPlanMapper.getRepellentPlanModelById(id);
    }

    public List<RepellentPlanModel> getRepellentPlanModelByTradeMarkEarTag(List<String[]> repellentEartag, RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModelByTradeMarkEarTag(repellentEartag,bounds);
    }

    public List<RepellentPlanModel> getRepellentPlanModelByFactoryNum(BigInteger factoryNum){
        return this.repellentPlanMapper.getRepellentPlanModelByFactoryNum(factoryNum );
    }


    public List<RepellentPlanModel> getRepellentPlanModelByFactoryNumAndIsPassCheck(BigInteger factoryNum , String ispassCheck, RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModelByFactoryNumAndIsPassCheck(factoryNum, ispassCheck, bounds);
    }

    public List<RepellentPlanModel> getRepellentPlanModelByFactoryNumAndIsPassSup(BigInteger factoryNum ,String ispassSup, RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModelByFactoryNumAndIsPassSup(factoryNum, ispassSup, bounds);

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
