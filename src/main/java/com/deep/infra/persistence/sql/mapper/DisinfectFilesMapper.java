package com.deep.infra.persistence.sql.mapper;

import com.deep.api.request.DisinfectRequest;
import com.deep.api.request.ModuleFindRequest;
import com.deep.api.request.ModuleUpdateRequest;
import com.deep.domain.model.DisinfectFilesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface DisinfectFilesMapper {
    void setDisinfectFilesModel(@Param("disinfectFilesModel") DisinfectFilesModel disinfectFilesModel);


    List<DisinfectFilesModel> getDisinfectFilesModel(@Param("disinfectFilesModel") DisinfectRequest disinfectFilesModel,
                                                     RowBounds bounds);

    DisinfectFilesModel getDisinfectFilesModelById(@Param("id") Long id);

    List<DisinfectFilesModel> getDisinfectFilesModelByFactoryNum(@Param("factoryNum")BigInteger factoryNum , RowBounds bounds);

    List<DisinfectFilesModel> getDisinfectFilesModelByIsPassCheck(@Param("request") ModuleFindRequest request , RowBounds bounds);

    List<DisinfectFilesModel> getDisinfectFilesModelByIsPassSup(@Param("request") ModuleFindRequest request , RowBounds bounds);


    int updateDisinfectFilesModelByProfessor(@Param("request") ModuleUpdateRequest request);

    int updateDisinfectFilesModelBySupervisor(@Param("request") ModuleUpdateRequest request);

    int updateDisinfectFilesModelByOperatorName(@Param("disinfectFilesModel") DisinfectFilesModel disinfectFilesModel);

    int deleteDisinfectFilesModelById(@Param("id") Long id);
}
