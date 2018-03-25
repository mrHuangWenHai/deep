package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.GenealogicalFilesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * create by zhongrui on 2018/2/1
 **/
@Mapper
public interface GenealogicalFilesMapper {


    List<GenealogicalFilesModel> getGenealogicalFilesModel(@Param("selfEartag") String selfEartag,
                                                           @Param("immuneEartag") String immuneEartag,
                                                           @Param("tradeMarkEartag") String tradeMarkEartag,
                                                           @Param("breedingSheepBase") String breedingSheepBase,
                                                           @Param("birthTimeStart") String birthTimeStart,
                                                           @Param("birthTimeEnd") String birthTimeEnd,
                                                           @Param("birthWeightStart") String birthWeightStart,
                                                           @Param("birthWeightEnd") String birthWeightEnd,
                                                           @Param("color") String color,
                                                           @Param("sex") String sex,
                                                           @Param("eartagOfFather") String eartagOfFather,
                                                           @Param("eartagOfMother") String eartagOfMother,
                                                           @Param("eartagOfFathersFather") String eartagOfFathersFather,
                                                           @Param("eartagOfFathersMother") String eartagOfFathersMother,
                                                           @Param("eartagOfMothersFather") String eartagOfMothersFather,
                                                           @Param("eartagOfMothersMother") String eartagOfMothersMother,
                                                           RowBounds rowBounds
                                                           );
    GenealogicalFilesModel getGenealogicalFilesModelByid(@Param("id") int id);

    GenealogicalFilesModel getGenealogicalFilesModelByimmuneEartag(@Param("immuneEartag") String immuneEartag);
    GenealogicalFilesModel getGenealogicalFilesModelBytradeMarkEartag(@Param("tradeMarkEartag") String tradeMarkEartag);

    //@Insert("INSERT INTO genealogical_files VALUES genealogicalFilesModel")
    void setGenealogicalFilesModel(@Param("genealogicalFilesModel") GenealogicalFilesModel genealogicalFilesModel);

    //@Delete(FROM genealogical_files WHERE self_eartag = #{SelfEartag})
    int deleteGenealogicalFilesModel(@Param("selfEartag") String selfEartag);

}