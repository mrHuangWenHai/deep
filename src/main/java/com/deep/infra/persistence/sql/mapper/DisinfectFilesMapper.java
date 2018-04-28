package com.deep.infra.persistence.sql.mapper;

import com.deep.api.request.DisinfectRequest;
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


    List<DisinfectFilesModel> getDisinfectFilesModel(@Param("disinfectFilesModel") DisinfectRequest disinfectFilesModel,
                                                     RowBounds bounds);

    List<DisinfectFilesModel> getDisinfectFilesModelByProfessor(@Param("isPass") Integer isPass,
                                                                RowBounds bounds);

    List<DisinfectFilesModel> getDisinfectFilesModelBySupervisor(@Param("ispassSup") Integer ispassSup,
                                                                 RowBounds bounds);


    int updateDisinfectFilesModelByProfessor(@Param("disinfectFilesModel") DisinfectFilesModel disinfectFilesModel);

    int updateDisinfectFilesModelBySupervisor(@Param("disinfectFilesModel") DisinfectFilesModel disinfectFilesModel);

    int updateDisinfectFilesModelByOperatorName(@Param("disinfectFilesModel") DisinfectFilesModel disinfectFilesModel);



    int deleteDisinfectFilesModelByid(@Param("id") Long id);
}
