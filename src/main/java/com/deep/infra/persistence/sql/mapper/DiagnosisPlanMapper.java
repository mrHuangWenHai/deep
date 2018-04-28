package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.DiagnosisPlan;
import com.deep.domain.model.DiagnosisPlanExample;
import com.deep.domain.model.DiagnosisPlanWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DiagnosisPlanMapper {
    int countByExample(DiagnosisPlanExample example);

    int deleteByExample(DiagnosisPlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DiagnosisPlanWithBLOBs record);

    int insertSelective(DiagnosisPlanWithBLOBs record);

    List<DiagnosisPlanWithBLOBs> selectByExampleWithBLOBsWithRowbounds(DiagnosisPlanExample example, RowBounds rowBounds);

    List<DiagnosisPlanWithBLOBs> selectByExampleWithBLOBs(DiagnosisPlanExample example);

    List<DiagnosisPlan> selectByExampleWithRowbounds(DiagnosisPlanExample example, RowBounds rowBounds);

    List<DiagnosisPlan> selectByExample(DiagnosisPlanExample example);

    DiagnosisPlanWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DiagnosisPlanWithBLOBs record, @Param("example") DiagnosisPlanExample example);

    int updateByExampleWithBLOBs(@Param("record") DiagnosisPlanWithBLOBs record, @Param("example") DiagnosisPlanExample example);

    int updateByExample(@Param("record") DiagnosisPlan record, @Param("example") DiagnosisPlanExample example);

    int updateByPrimaryKeySelective(DiagnosisPlanWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DiagnosisPlanWithBLOBs record);

    int updateByPrimaryKey(DiagnosisPlan record);
}