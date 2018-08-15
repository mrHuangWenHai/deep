package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.DeadSheep;
import com.deep.domain.model.DeadSheepExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DeadSheepMapper {
    int countByExample(DeadSheepExample example);

    int deleteByExample(DeadSheepExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeadSheep record);

    int insertSelective(DeadSheep record);

    List<DeadSheep> selectByExampleWithRowbounds(DeadSheepExample example, RowBounds rowBounds);

    List<DeadSheep> selectByExample(DeadSheepExample example);

    DeadSheep selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeadSheep record, @Param("example") DeadSheepExample example);

    int updateByExample(@Param("record") DeadSheep record, @Param("example") DeadSheepExample example);

    int updateByPrimaryKeySelective(DeadSheep record);

    int updateByPrimaryKey(DeadSheep record);
}