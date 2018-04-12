package com.deep.domain.service;

import com.deep.domain.model.DisinfectFilesModel;
import com.deep.infra.persistence.sql.mapper.DisinfectFilesMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

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

    public List<DisinfectFilesModel> getDisinfectFilesModel(DisinfectFilesModel disinfectFilesModel,
                                                            RowBounds bounds){
        return this.disinfectFilesMapper.getDisinfectFilesModel(disinfectFilesModel,bounds);
    }
    public List<DisinfectFilesModel> getDisinfectFilesModelByfactoryNum(BigInteger factoryNum, RowBounds bounds){
        return this.disinfectFilesMapper.getDisinfectFilesModelByfactoryNum(factoryNum,bounds);
    }
    public List<DisinfectFilesModel> getDisinfectFilesModelBydisinfectTime(Date disinfectTime, RowBounds bounds){
        return this.disinfectFilesMapper.getDisinfectFilesModelBydisinfectTime(disinfectTime,bounds);
    }
    public List<DisinfectFilesModel> getDisinfectFilesModelByfactoryNumAnddisinfectTime(BigInteger factoryNum, String gmtCreate, String gmtTrans, RowBounds bounds){
        return this.disinfectFilesMapper.getDisinfectFilesModelByfactoryNumAnddisinfectTime(factoryNum, gmtCreate, gmtTrans,bounds);
    }
    public DisinfectFilesModel getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(BigInteger factoryNum, String disinfectTime, String disinfectName){
        return this.disinfectFilesMapper.getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(factoryNum,disinfectTime,disinfectName);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelByProfessor(Integer isPass,RowBounds bounds){
        return this.disinfectFilesMapper.getDisinfectFilesModelByProfessor(isPass,bounds);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelBySupervisor(Integer isPass1,RowBounds bounds){
        return this.disinfectFilesMapper.getDisinfectFilesModelBySupervisor(isPass1,bounds);
    }

    public DisinfectFilesModel getDisinfectFilesModelByid(Long id){
        return this.disinfectFilesMapper.getDisinfectFilesModelByid(id);
    }

    public int updateDisinfectFilesModelByProfessor(DisinfectFilesModel disinfectFilesModel){
        return this.disinfectFilesMapper.updateDisinfectFilesModelByProfessor(disinfectFilesModel);
    }
    public int updateDisinfectFilesModelBySupervisor(DisinfectFilesModel disinfectFilesModel){
        return this.disinfectFilesMapper.updateDisinfectFilesModelBySupervisor(disinfectFilesModel);
    }

    public int updateDisinfectFilesModelByOperator(DisinfectFilesModel disinfectFilesModel){
        return this.disinfectFilesMapper.updateDisinfectFilesModelByOperator(disinfectFilesModel);
    }



    public int deleteDisinfectFilesModelByid(Long id){
        return this.disinfectFilesMapper.deleteDisinfectFilesModelByid(id);
    }

}
