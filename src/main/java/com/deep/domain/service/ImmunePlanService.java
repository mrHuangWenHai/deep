package com.deep.domain.service;


import com.deep.api.request.ImmuneRequest;
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

    public int setImmunePlanModel(ImmunePlanModel immunePlanModel){
        return this.immunePlanMapper.setImmunePlanModel(immunePlanModel);
    }


    public List<ImmunePlanModel> getImmunePlanModel(ImmuneRequest immunePlanModel,
                                                    RowBounds bounds){
        return this.immunePlanMapper.getImmunePlanModel(immunePlanModel,bounds);
    }

    public ImmunePlanModel getImmunePlanModelById(long id){
        return this.immunePlanMapper.getImmunePlanModelById(id);
    }

    public int updateImmunePlanModelByProfessor(ImmunePlanModel immunePlanModel) {
        return this.immunePlanMapper.updateImmunePlanModelByProfessor(immunePlanModel);
    }

    public int updateImmunePlanModelBySupervisor(ImmunePlanModel immunePlanModel){
        return this.immunePlanMapper.updateImmunePlanModelBySupervisor(immunePlanModel);
    }

    public int updateImmunePlanModelByOperator(ImmunePlanModel immunePlanModel){
        return this.immunePlanMapper.updateImmunePlanModelByOperator(immunePlanModel);
    }


    public int deleteImmunePlanModelById(Long id){
        return this.immunePlanMapper.deleteImmunePlanModelById(id);
    }


}
