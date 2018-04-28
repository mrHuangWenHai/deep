package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.NoticePlan;
import com.deep.domain.model.NoticePlanExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface NoticePlanMapper {
    int countByExample(NoticePlanExample example);

    int deleteByExample(NoticePlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoticePlan record);

    int insertSelective(NoticePlan record);

    List<NoticePlan> selectByExampleWithBLOBsWithRowbounds(NoticePlanExample example, RowBounds rowBounds);

    List<NoticePlan> selectByExampleWithBLOBs(NoticePlanExample example);

    List<NoticePlan> selectByExampleWithRowbounds(NoticePlanExample example, RowBounds rowBounds);

    List<NoticePlan> selectByExample(NoticePlanExample example);

    NoticePlan selectByPrimaryKey(Integer id);

    List<NoticePlan> selectInSite(String string);

    int updateByExampleSelective(@Param("record") NoticePlan record, @Param("example") NoticePlanExample example);

    int updateByExampleWithBLOBs(@Param("record") NoticePlan record, @Param("example") NoticePlanExample example);

    int updateByExample(@Param("record") NoticePlan record, @Param("example") NoticePlanExample example);

    int updateByPrimaryKeySelective(NoticePlan record);

    int updateByPrimaryKeyWithBLOBs(NoticePlan record);

    int updateByPrimaryKey(NoticePlan record);
}