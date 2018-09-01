package com.deep.infra.persistence.sql.mapper.sheepInfo;

import com.deep.domain.model.SheepInfo;
import com.deep.domain.model.SheepInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SheepInfoMapper {
    int countByExample(SheepInfoExample example);

    int deleteByExample(SheepInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SheepInfo record);

    int insertSelective(SheepInfo record);

    List<SheepInfo> selectByExampleWithRowbounds(SheepInfoExample example, RowBounds rowBounds);

    List<SheepInfo> selectByExample(SheepInfoExample example);

    SheepInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SheepInfo record, @Param("example") SheepInfoExample example);

    int updateByExample(@Param("record") SheepInfo record, @Param("example") SheepInfoExample example);

    int updateByPrimaryKeySelective(SheepInfo record);

    int updateByPrimaryKey(SheepInfo record);
}