package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.CertificationModel;
import com.deep.domain.model.CertificationModelExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface CertificationModelMapper {
    int countByExample(CertificationModelExample example);

    int deleteByExample(CertificationModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CertificationModel record);

    int insertSelective(CertificationModel record);

    List<CertificationModel> selectByExampleWithRowbounds(CertificationModelExample example, RowBounds rowBounds);

    List<CertificationModel> selectByExample(CertificationModelExample example);

    CertificationModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CertificationModel record, @Param("example") CertificationModelExample example);

    int updateByExample(@Param("record") CertificationModel record, @Param("example") CertificationModelExample example);

    int updateByPrimaryKeySelective(CertificationModel record);

    int updateByPrimaryKey(CertificationModel record);
}