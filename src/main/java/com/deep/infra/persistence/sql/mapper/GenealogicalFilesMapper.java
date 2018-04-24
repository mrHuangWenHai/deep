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


    List<GenealogicalFilesModel> getGenealogicalFilesModel(@Param("genealogicalFilesModel") GenealogicalRequest genealogicalFilesModel,
                                                           RowBounds rowBounds
                                                           );

    List<GenealogicalFilesModel> getGenealogicalFilesModelByFactoryNum (@Param("factoryNum") long factoryNum,
                                                                        RowBounds rowBounds);

    int allGenealogicalFilesCounts();

    GenealogicalFilesModel getGenealogicalFilesModelByid(@Param("id") int id);

    GenealogicalFilesModel getGenealogicalFilesModelByNativeEartag(@Param("nativeEartag") String nativeEartag);

    GenealogicalFilesModel getGenealogicalFilesModelByimmuneEartag(@Param("immuneEartag") String immuneEartag);

    GenealogicalFilesModel getGenealogicalFilesModelBytradeMarkEartag(@Param("tradeMarkEartag") String tradeMarkEartag);

    //@Delete(FROM genealogical_files WHERE self_eartag = #{SelfEartag})
    int deleteGenealogicalFilesModel(@Param("id") int id);

    int updateGenealogicalFilesModel(@Param("genealogicalFilesModel") GenealogicalFilesModel genealogicalFilesModel);

}