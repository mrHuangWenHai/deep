package com.deep.domain.service;


import com.deep.api.request.ModuleFindRequest;
import com.deep.api.request.ModuleUpdateRequest;
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

    public RepellentPlanModel getRepellentPlanModelById(long id){
        return this.repellentPlanMapper.getRepellentPlanModelById(id);
    }

    public List<RepellentPlanModel> getRepellentPlanModelByFactoryNum(BigInteger factoryNum , RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModelByFactoryNum(factoryNum , bounds);
    }

    public List<RepellentPlanModel> getRepellentPlanModelByIsPassCheck(ModuleFindRequest request , RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModelByIsPassCheck(request , bounds);
    }

    public List<RepellentPlanModel> getRepellentPlanModelByIsPassSup(ModuleFindRequest request , RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModelByIsPassSup(request, bounds);
    }

    public int updateRepellentPlanModelByProfessor(ModuleUpdateRequest request){
        return this.repellentPlanMapper.updateRepellentPlanModelByProfessor(request);
    }

    public int updateRepellentPlanModelBySupervisor(ModuleUpdateRequest request){
        return this.repellentPlanMapper.updateRepellentPlanModelBySupervisor(request);
    }

    public int updateRepellentPlanModelByOperator(RepellentPlanModel repellentPlanModel){
        return this.repellentPlanMapper.updateRepellentPlanModelByOperator(repellentPlanModel);
    }

    public int deleteRepellentPlanModelByid(Long id){
        return this.repellentPlanMapper.deleteRepellentPlanModelByid(id);
    }

}
