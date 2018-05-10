package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.Pic;
import com.deep.domain.model.PicExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.List;

@Mapper
public interface PicMapper {
    int countByExample(PicExample example);

    int deleteByExample(PicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Pic record);

    int insertSelective(Pic record);

    List<Pic> selectByExampleWithRowbounds(PicExample example, RowBounds rowBounds);

    List<Pic> selectByExample(PicExample example);

    List<Pic> getAll();
//    List<Pic> selectByBrand(String brand);
//
//    List<Pic> selectByUdate(Date udate);
//
//    List<Pic> selectByExpert(String expert);
//
//    List<Pic> selectBySymptom(String symptom);
//
//    List<Pic> selectByUploader(String loader);
//
//    List<Pic> selectByFilename(String filename);

    Pic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Pic record, @Param("example") PicExample example);

    int updateByExample(@Param("record") Pic record, @Param("example") PicExample example);

    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKey(Pic record);
}