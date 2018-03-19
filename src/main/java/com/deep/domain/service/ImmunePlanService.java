package com.deep.domain.service;

import com.deep.domain.model.ImmunePlanModel;
import com.deep.infra.persistence.sql.mapper.ImmunePlanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class ImmunePlanService {
    @Resource
    private ImmunePlanMapper immunePlanMapper;

    public void setImmunePlanModel(ImmunePlanModel immunePlanModel){
        this.immunePlanMapper.setImmunePlanModel(immunePlanModel);
    }

    public List<ImmunePlanModel> getImmunePlanModel( BigInteger factoryNum,
                                                     String crowdNum,
                                                     String immuneEartagStart,
                                                     String immuneEartagEnd,
                                                     String immuneTimeStart,
                                                     String immuneTimeEnd,
                                                     String immuneKind,
                                                     String immuneWay,
                                                     String immuneQuality,
                                                     String immuneDuring,
                                                     String operator,
                                                     String professor,
                                                     String supervisor,
                                                     String remark,
                                                     String  isPass1,
                                                     String unpassReason1,
                                                     String isPass2,
                                                     String unpassReason2){
        List<ImmunePlanModel> models = this.immunePlanMapper.getImmunePlanModel(factoryNum,crowdNum, immuneEartagStart,immuneEartagEnd,
                                                        immuneTimeStart,immuneTimeEnd, immuneKind, immuneWay, immuneQuality,
                                                        immuneDuring, operator, professor, supervisor,
                                                        remark, isPass1, unpassReason1, isPass2, unpassReason2);
        return models;
    }


    public ImmunePlanModel getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(BigInteger factoryNum, String crowdNum, String immuneTime){
        ImmunePlanModel immunePlanModel = this.immunePlanMapper.getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(factoryNum,crowdNum,immuneTime);
        return  immunePlanModel;
    }
    public int deleteImmunePlanModelByfactoryNum(BigInteger factoryNum){
        int row = this.immunePlanMapper.deleteImmunePlanModelByfactoryNum(factoryNum);
        return row;
    }
    public int deleteImmunePlanModelByfactoryNumAndimmuneTime(BigInteger factoryNum,String immuneTime){
        int row = this.immunePlanMapper.deleteImmunePlanModelByfactoryNumAndimmuneTime(factoryNum,immuneTime);
        return row;
    }

}
