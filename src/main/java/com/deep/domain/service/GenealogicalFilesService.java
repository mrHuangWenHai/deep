package com.deep.domain.service;

import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.infra.persistence.sql.mapper.GenealogicalFilesMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        return this.genealogicalFilesMapper.getGenealogicalFilesModel(genealogicalFilesModel,bounds);
    }



    public GenealogicalFilesModel getGenealogicalFilesModelByid(Long id) {
        return this.genealogicalFilesMapper.getGenealogicalFilesModelByid(id);
    }

    public GenealogicalFilesModel getGenealogicalFilesModelByNativeEartag(String nativeEartag) {
        return this.genealogicalFilesMapper.getGenealogicalFilesModelByNativeEartag(nativeEartag);
    }

    public GenealogicalFilesModel getGenealogicalFilesModelByimmuneEartag(String immuneEartag) {
        return this.genealogicalFilesMapper.getGenealogicalFilesModelByimmuneEartag(immuneEartag);
    }

    public GenealogicalFilesModel getGenealogicalFilesModelBytradeMarkEartag(String tradeMarkEartag) {
        return this.genealogicalFilesMapper.getGenealogicalFilesModelBytradeMarkEartag(tradeMarkEartag);
    }


    public int deleteGenealogicalFilesModel(Long id) {
        return this.genealogicalFilesMapper.deleteGenealogicalFilesModel(id);
    }

    public int updateGenealogicalFilesModel(GenealogicalFilesModel genealogicalFilesModel) {
        return this.genealogicalFilesMapper.updateGenealogicalFilesModel(genealogicalFilesModel);
    }
}