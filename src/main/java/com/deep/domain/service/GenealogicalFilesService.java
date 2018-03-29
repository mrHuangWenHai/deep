package com.deep.domain.service;

import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.infra.persistence.sql.mapper.GenealogicalFilesMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * Create by zhongrui on 2018/2/1
 */
@Service
public class  GenealogicalFilesService {

    @Resource
    private GenealogicalFilesMapper genealogicalFilesMapper;


    public void setGenealogicalFilesModel(GenealogicalFilesModel genealogicalFilesModel) {
        this.genealogicalFilesMapper.setGenealogicalFilesModel(genealogicalFilesModel);
    }

    public List<GenealogicalFilesModel> getGenealogicalFilesModel(GenealogicalFilesModel genealogicalFilesModel,
                                                                  RowBounds bounds) {
        List<GenealogicalFilesModel> model = this.genealogicalFilesMapper.getGenealogicalFilesModel(genealogicalFilesModel,bounds);
        return model;
    }



    public GenealogicalFilesModel getGenealogicalFilesModelByid(BigInteger id){
        GenealogicalFilesModel model = this.genealogicalFilesMapper.getGenealogicalFilesModelByid(id);
        return model;
    }

    public GenealogicalFilesModel getGenealogicalFilesModelByselfEartag(String selfEartag){
        GenealogicalFilesModel model = this.genealogicalFilesMapper.getGenealogicalFilesModelByselfEartag(selfEartag);
        return model;
    }
    public GenealogicalFilesModel getGenealogicalFilesModelByimmuneEartag(String immuneEartag) {
        GenealogicalFilesModel model = this.genealogicalFilesMapper.getGenealogicalFilesModelByimmuneEartag(immuneEartag);
        return model;
    }
    public GenealogicalFilesModel getGenealogicalFilesModelBytradeMarkEartag(String tradeMarkEartag){
        GenealogicalFilesModel model = this.genealogicalFilesMapper.getGenealogicalFilesModelBytradeMarkEartag(tradeMarkEartag);
        return model;
    }


    public int deleteGenealogicalFilesModel(BigInteger id){
        int row = this.genealogicalFilesMapper.deleteGenealogicalFilesModel(id);
        return row;
    }

    public int updateGenealogicalFilesModel(GenealogicalFilesModel genealogicalFilesModel){
        int row = this.genealogicalFilesMapper.updateGenealogicalFilesModel(genealogicalFilesModel);
        return row;
    }
}