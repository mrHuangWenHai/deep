package com.deep.domain.service;

import com.deep.api.request.DisinfectRequest;
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


    public List<DisinfectFilesModel> getDisinfectFilesModelByProfessor(Integer isPass,RowBounds bounds) {
        return this.disinfectFilesMapper.getDisinfectFilesModelByProfessor(isPass,bounds);
    }

    public List<DisinfectFilesModel> getDisinfectFilesModelBySupervisor(Integer ispassSup,RowBounds bounds) {
        return this.disinfectFilesMapper.getDisinfectFilesModelBySupervisor(ispassSup,bounds);
    }

    public int updateDisinfectFilesModelByProfessor(DisinfectFilesModel disinfectFilesModel) {
        return this.disinfectFilesMapper.updateDisinfectFilesModelByProfessor(disinfectFilesModel);
    }
    public int updateDisinfectFilesModelBySupervisor(DisinfectFilesModel disinfectFilesModel){
        return this.disinfectFilesMapper.updateDisinfectFilesModelBySupervisor(disinfectFilesModel);
    }

    public int updateDisinfectFilesModelByOperatorName(DisinfectFilesModel disinfectFilesModel){
        return this.disinfectFilesMapper.updateDisinfectFilesModelByOperatorName(disinfectFilesModel);
    }


    public int deleteDisinfectFilesModelByid(Long id){
        return this.disinfectFilesMapper.deleteDisinfectFilesModelByid(id);
    }

}
