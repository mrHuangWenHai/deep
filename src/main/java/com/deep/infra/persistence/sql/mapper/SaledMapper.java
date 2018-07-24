package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.Saled;
import com.deep.domain.model.SaledExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SaledMapper {
    int countByExample(SaledExample example);

    int deleteByExample(SaledExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Saled record);

    int insertSelective(Saled record);

    List<Saled> selectByExampleWithRowbounds(SaledExample example, RowBounds rowBounds);

    List<Saled> selectByExample(SaledExample example);

    Saled selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Saled record, @Param("example") SaledExample example);

    int updateByExample(@Param("record") Saled record, @Param("example") SaledExample example);

    int updateByPrimaryKeySelective(Saled record);

    int updateByPrimaryKey(Saled record);
}