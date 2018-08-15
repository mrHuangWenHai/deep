package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.BuildingColumn;
import com.deep.domain.model.BuildingColumnExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BuildingColumnMapper {
    int countByExample(BuildingColumnExample example);

    int deleteByExample(BuildingColumnExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuildingColumn record);

    int insertSelective(BuildingColumn record);

    List<BuildingColumn> selectByExampleWithRowbounds(BuildingColumnExample example, RowBounds rowBounds);

    List<BuildingColumn> selectByExample(BuildingColumnExample example);

    BuildingColumn selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuildingColumn record, @Param("example") BuildingColumnExample example);

    int updateByExample(@Param("record") BuildingColumn record, @Param("example") BuildingColumnExample example);

    int updateByPrimaryKeySelective(BuildingColumn record);

    int updateByPrimaryKey(BuildingColumn record);
}