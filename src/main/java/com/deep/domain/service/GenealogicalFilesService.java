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

    private int total = -1;

    public int insertGenealogicalFilesModel(GenealogicalFilesModel genealogicalFilesModel) {
        int success = this.genealogicalFilesMapper.insertGenealogicalFilesModel(genealogicalFilesModel);
        if (success == 1) {
          total = (total == -1) ? 1:(total+1);
        }
        return success;
    }

    public List<GenealogicalFilesModel> getGenealogicalFilesModel(GenealogicalRequest genealogicalFilesModel) {
        return this.genealogicalFilesMapper.getGenealogicalFilesModel(genealogicalFilesModel);
    }

    public List<GenealogicalFilesModel> getGenealogicalFilesModelByFactoryNum(long factoryNum) {
        return this.genealogicalFilesMapper.getGenealogicalFilesModelByFactoryNum(factoryNum);
    }

    public int allGenealogicalFilesCounts() {

      if (total == -1) {
        total = this.genealogicalFilesMapper.allGenealogicalFilesCounts();
        return total;
      }

      return total;
    }


    public GenealogicalFilesModel getGenealogicalFilesModelById(long id) {
        return this.genealogicalFilesMapper.getGenealogicalFilesModelById(id);
    }


    public int deleteGenealogicalFilesModelById(long id) {
        int success = this.genealogicalFilesMapper.deleteGenealogicalFilesModelById(id);

        if (success == 1) {
          if (total != -1 && total > 0) {
            total --;
          }
        }
        return success;
    }

    public int updateGenealogicalFilesModel(GenealogicalFilesModel genealogicalFilesModel) {
        return this.genealogicalFilesMapper.updateGenealogicalFilesModel(genealogicalFilesModel);
    }
}