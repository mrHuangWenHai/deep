package com.deep.domain.service;

import com.deep.domain.model.DisinfectFilesModel;
import com.deep.infra.persistence.sql.mapper.DisinfectFilesMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class DisinfectFilesService {
    @Resource
    private DisinfectFilesMapper disinfectFilesMapper;

    public void setDisinfectFilesModel(DisinfectFilesModel disinfectFilesModel){
        this.disinfectFilesMapper.setDisinfectFilesModel(disinfectFilesModel);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModel(BigInteger factoryNum,
                                                            String disinfectTimeStart,
                                                            String disinfectTimeEnd,
                                                            String disinfectName,
                                                            String disinfectQuality,
                                                            String disinfectWay,
                                                            String operator,
                                                            String professor,
                                                            String supervisor,
                                                            String remark,
                                                            String  isPass1,
                                                            String unpassReason1,
                                                            String  isPass2,
                                                            String unpassReason2,
                                                            RowBounds bounds){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModel(factoryNum,
                disinfectTimeStart,disinfectTimeEnd, disinfectName, disinfectQuality, disinfectWay, operator,
                professor, supervisor, remark, isPass1,unpassReason1,isPass2,unpassReason2,bounds);
        return disinfectFilesModels;
    }
    public List<DisinfectFilesModel> getDisinfectFilesModelByfactoryNum(BigInteger factoryNum){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModelByfactoryNum(factoryNum);
        return disinfectFilesModels;
    }
    public List<DisinfectFilesModel> getDisinfectFilesModelBydisinfectTime(Date disinfectTime){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModelBydisinfectTime(disinfectTime);
        return disinfectFilesModels;
    }
    public DisinfectFilesModel getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(BigInteger factoryNum, String disinfectTime, String disinfectName){
        DisinfectFilesModel disinfectFilesModel = this.disinfectFilesMapper.getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(factoryNum,disinfectTime,disinfectName);
        return disinfectFilesModel;
    }



    public int updateDisinfectFilesModelByProfessor(String professor, String isPass1, String unpassReason1, String gmtProfessor){
        int row = this.disinfectFilesMapper.updateDisinfectFilesModelByProfessor(professor, isPass1, unpassReason1, gmtProfessor);
        return row;
    }
    public int updateDisinfectFilesModelBySupervisor(String supervisor, String isPass2, String unpassReason2, String gmtSuperise){
        int row = this.disinfectFilesMapper.updateDisinfectFilesModelBySupervisor(supervisor, isPass2, unpassReason2, gmtSuperise);
        return row;
    }



    public int deleteDisinfectFilesModelByfactoryNum(BigInteger factoryNum){
        int row = this.disinfectFilesMapper.deleteDisinfectFilesModelByfactoryNum(factoryNum);
        return row;
    }
    public int deleteDisinfectFilesModelByfactoryNumAnddisinfectTime(BigInteger factoryNum, String disinfectTime){
        int row = this.disinfectFilesMapper.deleteDisinfectFilesModelByfactoryNumAnddisinfectTime(factoryNum,disinfectTime);
        return row;
    }

}
