package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.DisinfectFilesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Mapper
public interface DisinfectFilesMapper {
    void setDisinfectFilesModel(@Param("disinfectFilesModel") DisinfectFilesModel disinfectFilesModel);


    List<DisinfectFilesModel> getDisinfectFilesModel(@Param("disinfectFilesModel") DisinfectFilesModel disinfectFilesModel,
                                                     RowBounds bounds);

    List<DisinfectFilesModel> getDisinfectFilesModelByfactoryNum(@Param("factoryNum")BigInteger factoryNum,
                                                                 RowBounds bounds);

    List<DisinfectFilesModel> getDisinfectFilesModelBydisinfectTime(@Param("disinfectTime")Date disinfectTime,
                                                                    RowBounds bounds);

    List<DisinfectFilesModel> getDisinfectFilesModelByfactoryNumAnddisinfectTime(@Param("factoryNum")BigInteger factoryNum,
                                                                                 @Param("gmtCreate") String gmtCreate,
                                                                                 @Param("gmtTrans") String gmtTrans,
                                                                                 RowBounds bounds);

    DisinfectFilesModel getDisinfectFilesModelByfactoryNumAnddisinfectTimeAnddisinfectName(@Param("factoryNum") BigInteger factoryNum,
                                                                                           @Param("disinfectTime") String disinfectTime,
                                                                                           @Param("disinfectName") String disinfectName);



    List<DisinfectFilesModel> getDisinfectFilesModelByProfessor(@Param("isPass1") Integer isPass1,
                                                                RowBounds bounds);

    List<DisinfectFilesModel> getDisinfectFilesModelBySupervisor(@Param("isPass2") Integer isPass2,
                                                                 RowBounds bounds);


    int updateDisinfectFilesModelByProfessor(@Param("disinfectFilesModel") DisinfectFilesModel disinfectFilesModel);

    int updateDisinfectFilesModelBySupervisor(@Param("disinfectFilesModel") DisinfectFilesModel disinfectFilesModel);



    int deleteDisinfectFilesModelByid(@Param("id") BigInteger id);
}
