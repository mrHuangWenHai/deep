package com.deep.domain.service;

import com.deep.api.request.DisinfectRequest;
import com.deep.domain.model.DisinfectFilesModel;
import com.deep.infra.persistence.sql.mapper.DisinfectFilesMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

import java.util.List;

@Service
public class DisinfectFilesService {
    @Resource
    private DisinfectFilesMapper disinfectFilesMapper;

    public void setDisinfectFilesModel(DisinfectFilesModel disinfectFilesModel){
        this.disinfectFilesMapper.setDisinfectFilesModel(disinfectFilesModel);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModel(DisinfectRequest disinfectRequest) {
        return this.disinfectFilesMapper.getDisinfectFilesModel(disinfectRequest);
    }

    public DisinfectFilesModel getDisinfectFilesModelById(long id) {
        return this.disinfectFilesMapper.getDisinfectFilesModelById(id);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelByTradeMarkEarTag(List<String[]> disinfectEartag, RowBounds bounds){
        return this.disinfectFilesMapper.getDisinfectFilesModelByTradeMarkEarTag(disinfectEartag,bounds);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelByFactoryNum(BigInteger factoryNum ) {
        return this.disinfectFilesMapper.getDisinfectFilesModelByFactoryNum(factoryNum);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelByFactoryNumAndIsPassCheck(BigInteger factoryNum, String ispassCheck, RowBounds bounds) {
        return this.disinfectFilesMapper.getDisinfectFilesModelByFactoryNumAndIsPassCheck(factoryNum, ispassCheck, bounds);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelByFactoryNumAndIsPassSup(BigInteger factoryNum, String ispassSup, RowBounds bounds) {
        return this.disinfectFilesMapper.getDisinfectFilesModelByFactoryNumAndIsPassSup(factoryNum, ispassSup, bounds);
    }

//    public  List<DisinfectFilesModel> getDisinfectFilesModelByFactoryList(@Param("disinfectFilesModel") DisinfectRequest disinfectFilesModel,
//                                                                          RowBounds bounds) {
//        return this.disinfectFilesMapper.getDisinfectFilesModelByFactoryList(disinfectFilesModel, bounds);
//    }


    public int updateDisinfectFilesModelByProfessor(DisinfectRequest disinfectRequest) {
        return this.disinfectFilesMapper.updateDisinfectFilesModelByProfessor(disinfectRequest);
    }


    public int updateDisinfectFilesModelBySupervisor(DisinfectRequest disinfectRequest) {
        return this.disinfectFilesMapper.updateDisinfectFilesModelBySupervisor(disinfectRequest);
    }


    public int updateDisinfectFilesModelByOperatorName(DisinfectFilesModel disinfectFilesModel) {
        return this.disinfectFilesMapper.updateDisinfectFilesModelByOperatorName(disinfectFilesModel);
    }

    public int deleteDisinfectFilesModelById(Long id) {
        return this.disinfectFilesMapper.deleteDisinfectFilesModelById(id);

    }

}
