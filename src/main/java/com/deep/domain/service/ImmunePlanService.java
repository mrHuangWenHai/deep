package com.deep.domain.service;

import com.deep.domain.model.ImmunePlanModel;
import com.deep.infra.persistence.sql.mapper.ImmunePlanMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ImmunePlanService {
    @Resource
    private ImmunePlanMapper immunePlanMapper;

    public void setImmunePlanModel(BigInteger factoryNum,
                                   String crowdNum,
                                   String immuneEartag,
                                   String immuneTime,
                                   String immuneKind,
                                   String immuneWay,
                                   String immuneQuality,
                                   String immuneDuring,
                                   String operator,
                                   String remark,
                                   String  isPass1,
                                   String isPass2,
                                   Timestamp gmtCreate){
        this.immunePlanMapper.setImmunePlanModel(factoryNum, crowdNum, immuneEartag, immuneTime,
                immuneKind, immuneWay, immuneQuality, immuneDuring, operator, remark, isPass1, isPass2, gmtCreate);
    }

    public List<ImmunePlanModel> getImmunePlanModel(ImmunePlanModel immunePlanModel,
                                                    RowBounds bounds){
        List<ImmunePlanModel> models = this.immunePlanMapper.getImmunePlanModel(immunePlanModel,bounds);
        return models;
    }


    public ImmunePlanModel getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(BigInteger factoryNum, String crowdNum, String immuneTime){
        ImmunePlanModel immunePlanModel = this.immunePlanMapper.getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(factoryNum,crowdNum,immuneTime);
        return  immunePlanModel;
    }

    public List<ImmunePlanModel> getImmunePlanModelByProfessor(Integer isPass1, RowBounds bounds){
        List<ImmunePlanModel> models = this.immunePlanMapper.getImmunePlanModelByProfessor(isPass1,bounds);
        return models;
    }

    public List<ImmunePlanModel> getImmunePlanModelBySupervisor(Integer isPass2, RowBounds bounds){
        List<ImmunePlanModel> models = this.immunePlanMapper.getImmunePlanModelBySupervisor(isPass2,bounds);
        return models;
    }

    public ImmunePlanModel getImmunePlanModelByid(BigInteger id){
        ImmunePlanModel model = this.immunePlanMapper.getImmunePlanModelByid(id);
        return model;
    }

    public int updateImmunePlanModelByProfessor(ImmunePlanModel immunePlanModel){
        int row = this.immunePlanMapper.updateImmunePlanModelByProfessor(immunePlanModel);
        return row;
    }

    public int updateImmunePlanModelBySupervisor(ImmunePlanModel immunePlanModel){
        int row = this.immunePlanMapper.updateImmunePlanModelBySupervisor(immunePlanModel);
        return row;
    }

    public int updateImmunePlanModelByOperator(ImmunePlanModel immunePlanModel){
        int row = this.immunePlanMapper.updateImmunePlanModelByOperator(immunePlanModel);
        return row;
    }


    public int deleteImmunePlanModelByid(BigInteger id){
        int row = this.immunePlanMapper.deleteImmunePlanModelByid(id);
        return row;
    }


}
