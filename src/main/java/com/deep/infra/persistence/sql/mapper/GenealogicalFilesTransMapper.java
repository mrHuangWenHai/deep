package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.GenealogicalFilesTransModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by zhongrui on 18-3-19.
 */

@Mapper
public interface GenealogicalFilesTransMapper {

    void setGenealogicalFilesTransModel(@Param("genealogicalFilesTransModel") GenealogicalFilesTransModel genealogicalFilesTransModel);

    List<GenealogicalFilesTransModel> getGenealogicalFilesTransModelBytrademarkEartag(@Param("trademarkEartag") String trademarkEartag);
    List<GenealogicalFilesTransModel> getAllGenealogicalFilesTransModel();

}
