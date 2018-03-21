package com.deep.domain.service;

import com.deep.domain.model.ImmunePlanModel;
import com.deep.infra.persistence.sql.mapper.ImmunePlanMapper;
import org.apache.ibatis.session.RowBounds;
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

    public List<ImmunePlanModel> getImmunePlanModel(BigInteger factoryNum,
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
                                                    String unpassReason2,
                                                    RowBounds bounds){
        List<ImmunePlanModel> models = this.immunePlanMapper.getImmunePlanModel(factoryNum,crowdNum, immuneEartagStart,immuneEartagEnd,
                                                        immuneTimeStart,immuneTimeEnd, immuneKind, immuneWay, immuneQuality,
                                                        immuneDuring, operator, professor, supervisor,
                                                        remark, isPass1, unpassReason1, isPass2, unpassReason2,bounds);
        return models;
    }


    public ImmunePlanModel getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(BigInteger factoryNum, String crowdNum, String immuneTime){
        ImmunePlanModel immunePlanModel = this.immunePlanMapper.getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(factoryNum,crowdNum,immuneTime);
        return  immunePlanModel;
    }

    public List<ImmunePlanModel> getImmunePlanModelByProfessor(RowBounds bounds){
        List<ImmunePlanModel> models = this.immunePlanMapper.getImmunePlanModelByProfessor(bounds);
        return models;
    }

    public int updateImmunePlanModelByProfessor(String professor, String  isPass1, String unpassReason1, String gmtProfessor){
        int row = this.immunePlanMapper.updateImmunePlanModelByProfessor(professor, isPass1, unpassReason1, gmtProfessor);
        return row;
    }

    public int updateImmunePlanModelBySupervisor(String supervisor, String isPass2, String unpassReason2, String gmtSupervise){
        int row = this.immunePlanMapper.updateImmunePlanModelBySupervisor(supervisor, isPass2, unpassReason2, gmtSupervise);
        return row;
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
