package com.deep.infra.persistence.sql.mapper;

import com.deep.api.request.GenealogicalRequest;
import com.deep.domain.model.GenealogicalFilesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.util.List;

/**int
 * create by zhongrui on 2018/2/1
 **/
@Mapper
public interface GenealogicalFilesMapper {

    int insertGenealogicalFilesModel(@Param("genealogicalFilesModel") GenealogicalFilesModel genealogicalFilesModel);


    List<GenealogicalFilesModel> getGenealogicalFilesModel(@Param("genealogicalFilesModel") GenealogicalRequest genealogicalFilesModel);

    List<GenealogicalFilesModel> getGenealogicalFilesModelByFactoryNum(@Param("factoryNum") Long factoryNum);

    GenealogicalFilesModel getGenealogicalFilesModelByImmuneEartag(@Param("immuneEartag") String immuneEartag);


    GenealogicalFilesModel getGenealogicalFilesModelByTradeMarkEartag(@Param("tradeMarkEartag")String tradeMarkEartag);

    GenealogicalFilesModel getGenealogicalFilesModelByNativeEartag(@Param("nativeEartag")String nativeEartag);

    int allGenealogicalFilesCounts();

    GenealogicalFilesModel getGenealogicalFilesModelById(@Param("id") Long id);


    //@Delete(FROM genealogical_files WHERE self_eartag = #{SelfEartag})
    int deleteGenealogicalFilesModelById(@Param("id") Long id);

    int updateGenealogicalFilesModel(@Param("genealogicalFilesModel") GenealogicalFilesModel genealogicalFilesModel);

}