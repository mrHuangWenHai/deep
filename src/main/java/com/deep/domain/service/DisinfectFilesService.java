package com.deep.domain.service;

import com.deep.domain.model.DisinfectFilesModel;
import com.deep.infra.persistence.sql.mapper.DisinfectFilesMapper;
import org.apache.ibatis.annotations.Param;
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

    public List<DisinfectFilesModel> getDisinfectFilesModel(DisinfectFilesModel disinfectFilesModel,
                                                            RowBounds bounds){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModel(disinfectFilesModel,bounds);
        return disinfectFilesModels;
    }
    public List<DisinfectFilesModel> getDisinfectFilesModelByfactoryNum(BigInteger factoryNum, RowBounds bounds){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModelByfactoryNum(factoryNum,bounds);
        return disinfectFilesModels;
    }
    public List<DisinfectFilesModel> getDisinfectFilesModelBydisinfectTime(Date disinfectTime, RowBounds bounds){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModelBydisinfectTime(disinfectTime,bounds);
        return disinfectFilesModels;
    }
    public List<DisinfectFilesModel> getDisinfectFilesModelByfactoryNumAnddisinfectTime(BigInteger factoryNum, String gmtCreate, String gmtTrans, RowBounds bounds){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModelByfactoryNumAnddisinfectTime(factoryNum, gmtCreate, gmtTrans,bounds);
        return disinfectFilesModels;
    }
    public DisinfectFilesModel getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(BigInteger factoryNum, String disinfectTime, String disinfectName){
        DisinfectFilesModel disinfectFilesModel = this.disinfectFilesMapper.getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(factoryNum,disinfectTime,disinfectName);
        return disinfectFilesModel;
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelByProfessor(Integer isPass1,RowBounds bounds){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModelByProfessor(isPass1,bounds);
        return disinfectFilesModels;
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelBySupervisor(Integer isPass2,RowBounds bounds){
        List<DisinfectFilesModel> disinfectFilesModels = this.disinfectFilesMapper.getDisinfectFilesModelBySupervisor(isPass2,bounds);
        return disinfectFilesModels;
    }

    public DisinfectFilesModel getDisinfectFilesModelByid(BigInteger id){
        DisinfectFilesModel disinfectFilesModel = this.disinfectFilesMapper.getDisinfectFilesModelByid(id);
        return  disinfectFilesModel;
    }

    public int updateDisinfectFilesModelByProfessor(DisinfectFilesModel disinfectFilesModel){
        int row = this.disinfectFilesMapper.updateDisinfectFilesModelByProfessor(disinfectFilesModel);
        return row;
    }
    public int updateDisinfectFilesModelBySupervisor(DisinfectFilesModel disinfectFilesModel){
        int row = this.disinfectFilesMapper.updateDisinfectFilesModelBySupervisor(disinfectFilesModel);
        return row;
    }

    public int updateDisinfectFilesModelByOperator(DisinfectFilesModel disinfectFilesModel){
        int row = this.disinfectFilesMapper.updateDisinfectFilesModelByOperator(disinfectFilesModel);
        return row;
    }



    public int deleteDisinfectFilesModelByid(BigInteger id){
        int row = this.disinfectFilesMapper.deleteDisinfectFilesModelByid(id);
        return row;
    }

}
