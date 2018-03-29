package com.deep.domain.service;

import com.deep.domain.model.RepellentPlanModel;
import com.deep.infra.persistence.sql.mapper.RepellentPlanMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Service
public class RepellentPlanService {
    @Resource
    private RepellentPlanMapper repellentPlanMapper;
    public void setRepellentPlanModel(BigInteger factoryNum,
                                      String crowdNum,
                                      String repellentEartag,
                                      String repellentTime,
                                      String repellentName,
                                      String repellentWay,
                                      String repellentQuality,
                                      String operator,
                                      String remark,
                                      String isPass1,
                                      String isPass2,
                                      Timestamp gmtCreate){
        this.repellentPlanMapper.setRepellentPlanModel(factoryNum, crowdNum, repellentEartag,
                repellentTime, repellentName, repellentWay, repellentQuality, operator,
                remark, isPass1, isPass2, gmtCreate);
    }

    public List<RepellentPlanModel> getRepellentPlanModel(RepellentPlanModel repellentPlanModel,
                                                          RowBounds bounds){
        List<RepellentPlanModel> repellentPlanModels = this.repellentPlanMapper.getRepellentPlanModel(repellentPlanModel,bounds);
        return repellentPlanModels;
    }

    public RepellentPlanModel getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(BigInteger factoryNum,String repellentTime,String repellentName){
        RepellentPlanModel repellentPlanModel = this.repellentPlanMapper.getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(factoryNum, repellentTime, repellentName);
        return repellentPlanModel;
    }

    public List<RepellentPlanModel> getRepellentPlanModelByProfessor(Integer isPass1, RowBounds bounds){
        List<RepellentPlanModel> repellentPlanModels = this.repellentPlanMapper.getRepellentPlanModelByProfessor(isPass1,bounds);
        return repellentPlanModels;
    }

    public List<RepellentPlanModel> getRepellentPlanModelBySupervisor(Integer isPass2, RowBounds bounds){
        List<RepellentPlanModel> repellentPlanModels = this.repellentPlanMapper.getRepellentPlanModelBySupervisor(isPass2,bounds);
        return repellentPlanModels;
    }

    public RepellentPlanModel getRepellentModelByid(BigInteger id){
        RepellentPlanModel repellentPlanModels = this.repellentPlanMapper.getRepellentModelByid(id);
        return repellentPlanModels;
    }

    public int updateRepellentPlanModelByProfessor(RepellentPlanModel repellentPlanModel){
        int row = this.repellentPlanMapper.updateRepellentPlanModelByProfessor(repellentPlanModel);
        return row;
    }

    public int updateRepellentPlanModelBySupervisor(RepellentPlanModel repellentPlanModel){
        int row = this.repellentPlanMapper.updateRepellentPlanModelBySupervisor(repellentPlanModel);
        return row;
    }

    public int deleteRepellentPlanModelByid(BigInteger id){
        int row = this.repellentPlanMapper.deleteRepellentPlanModelByid(id);
        return row;
    }

}
