package com.deep.domain.service;

import com.deep.domain.model.RepellentPlanModel;
import com.deep.infra.persistence.sql.mapper.RepellentPlanMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class RepellentPlanService {
    @Resource
    private RepellentPlanMapper repellentPlanMapper;
    public void setRepellentPlanModel(RepellentPlanModel repellentPlanModel){
        this.repellentPlanMapper.setRepellentPlanModel(repellentPlanModel);
    }

    public List<RepellentPlanModel> getRepellentPlanModel(BigInteger factoryNum,
                                                          String crowdNum,
                                                          String repellentEartag,
                                                          String repellentTimeStart,
                                                          String repellentTimeEnd,
                                                          String repellentName,
                                                          String repellentWay,
                                                          String repellentQuality,
                                                          String operator,
                                                          String professor,
                                                          String supervisor,
                                                          String remark,
                                                          String  isPass1,
                                                          String unpassReason1,
                                                          String isPass2,
                                                          String unpassReason2,
                                                          RowBounds bounds){
        List<RepellentPlanModel> repellentPlanModels = this.repellentPlanMapper.getRepellentPlanModel(factoryNum,crowdNum,repellentEartag,
                                                                                            repellentTimeStart,repellentTimeEnd,repellentName,
                                                                                            repellentWay,repellentQuality,operator,
                                                                                            professor,supervisor,remark,isPass1,
                                                                                            unpassReason1,isPass2,unpassReason2,bounds);
        return repellentPlanModels;
    }

    public RepellentPlanModel getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(BigInteger factoryNum,String repellentTime,String repellentName){
        RepellentPlanModel repellentPlanModel = this.repellentPlanMapper.getRepellentPlanModelByfactoryNumAndrepellentTimeAndrepellentName(factoryNum, repellentTime, repellentName);
        return repellentPlanModel;
    }



    public int updateRepellentPlanModelByProfessor(String professor, String  isPass1, String unpassReason1, String gmtProfessor){
        int row = this.repellentPlanMapper.updateRepellentPlanModelByProfessor(professor, isPass1, unpassReason1, gmtProfessor);
        return row;
    }

    public int updateRepellentPlanModelBySupervisor(String supervisor, String  isPass2, String unpassReason2, String gmtSupervisor){
        int row = this.repellentPlanMapper.updateRepellentPlanModelBySupervisor(supervisor, isPass2, unpassReason2, gmtSupervisor);
        return row;
    }



    public int deleteRepellentPlanModelByfactoryNum(BigInteger factoryNum){
        int row = this.repellentPlanMapper.deleteRepellentPlanModelByfactoryNum(factoryNum);
        return row;
    }
    public int deleteRepellentPlanModelByfactoryNumAndrepellentTime(BigInteger factoryNum,String repellentTime){
        int row = this.repellentPlanMapper.deleteRepellentPlanModelByfactoryNumAndrepellentTime(factoryNum, repellentTime);
        return row;
    }

}
