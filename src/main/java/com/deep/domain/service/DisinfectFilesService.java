package com.deep.domain.service;

import com.deep.api.request.DisinfectRequest;
import com.deep.api.request.ModuleFindRequest;
import com.deep.api.request.ModuleUpdateRequest;
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

    public List<DisinfectFilesModel> getDisinfectFilesModel(DisinfectRequest disinfectRequest,
                                                            RowBounds bounds){
        return this.disinfectFilesMapper.getDisinfectFilesModel(disinfectRequest,bounds);
    }

    public DisinfectFilesModel getDisinfectFilesModelById(long id){
        return this.disinfectFilesMapper.getDisinfectFilesModelById(id);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelByFactoryNum(BigInteger factoryNum , RowBounds bounds){
        return this.disinfectFilesMapper.getDisinfectFilesModelByFactoryNum(factoryNum,bounds);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelByIsPassCheck(ModuleFindRequest request , RowBounds bounds){
        return this.disinfectFilesMapper.getDisinfectFilesModelByIsPassCheck(request ,bounds);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelByIsPassSup(ModuleFindRequest  request , RowBounds bounds){
        return this.disinfectFilesMapper.getDisinfectFilesModelByIsPassSup(request ,bounds);
    }

    public int updateDisinfectFilesModelByProfessor(ModuleUpdateRequest request){
        return this.disinfectFilesMapper.updateDisinfectFilesModelByProfessor(request);
    }

    public int updateDisinfectFilesModelBySupervisor(ModuleUpdateRequest request){
        return this.disinfectFilesMapper.updateDisinfectFilesModelBySupervisor(request);
    }


    public int updateDisinfectFilesModelByOperatorName(DisinfectFilesModel disinfectFilesModel){
        return this.disinfectFilesMapper.updateDisinfectFilesModelByOperatorName(disinfectFilesModel);
    }

    public int deleteDisinfectFilesModelById(Long id){
        return this.disinfectFilesMapper.deleteDisinfectFilesModelById(id);
    }

}
