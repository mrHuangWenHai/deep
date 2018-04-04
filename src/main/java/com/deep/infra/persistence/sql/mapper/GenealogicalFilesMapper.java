package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.GenealogicalFilesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.util.List;

/**
 * create by zhongrui on 2018/2/1
 **/
@Mapper
public interface GenealogicalFilesMapper {

    void setGenealogicalFilesModel(@Param("genealogicalFilesModel") GenealogicalFilesModel genealogicalFilesModel);


    List<GenealogicalFilesModel> getGenealogicalFilesModel(@Param("genealogicalFilesModel") GenealogicalFilesModel genealogicalFilesModel,
                                                           RowBounds rowBounds
                                                           );
    GenealogicalFilesModel getGenealogicalFilesModelByid(@Param("id") Long id);

    GenealogicalFilesModel getGenealogicalFilesModelByselfEartag(@Param("selfEartag") String selfEartag);
    GenealogicalFilesModel getGenealogicalFilesModelByimmuneEartag(@Param("immuneEartag") String immuneEartag);
    GenealogicalFilesModel getGenealogicalFilesModelBytradeMarkEartag(@Param("tradeMarkEartag") String tradeMarkEartag);

    //@Delete(FROM genealogical_files WHERE self_eartag = #{SelfEartag})
    int deleteGenealogicalFilesModel(@Param("id") Long id);

    int updateGenealogicalFilesModel(@Param("genealogicalFilesModel") GenealogicalFilesModel genealogicalFilesModel);

}