package com.deep.domain.service;

import com.deep.api.request.GenealogicalRequest;
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

    public int insertGenealogicalFilesModel(GenealogicalFilesModel genealogicalFilesModel) {
        return this.genealogicalFilesMapper.insertGenealogicalFilesModel(genealogicalFilesModel);
    }

    public List<GenealogicalFilesModel> getGenealogicalFilesModel(GenealogicalRequest genealogicalFilesModel,
                                                                  RowBounds bounds) {
        return this.genealogicalFilesMapper.getGenealogicalFilesModel(genealogicalFilesModel,bounds);
    }



    public GenealogicalFilesModel getGenealogicalFilesModelByid(int id) {
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


    public int deleteGenealogicalFilesModel(int id) {
        return this.genealogicalFilesMapper.deleteGenealogicalFilesModel(id);
    }

    public int updateGenealogicalFilesModel(GenealogicalFilesModel genealogicalFilesModel) {
        return this.genealogicalFilesMapper.updateGenealogicalFilesModel(genealogicalFilesModel);
    }
}