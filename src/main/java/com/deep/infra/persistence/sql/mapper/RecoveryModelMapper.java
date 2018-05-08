package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.RecoveryModel;
import com.deep.domain.model.RecoveryModelExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface RecoveryModelMapper {
    int countByExample(RecoveryModelExample example);

    int deleteByExample(RecoveryModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RecoveryModel record);

    int insertSelective(RecoveryModel record);

    List<RecoveryModel> selectByExampleWithRowbounds(RecoveryModelExample example, RowBounds rowBounds);

    List<RecoveryModel> selectByExample(RecoveryModelExample example);

    RecoveryModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RecoveryModel record, @Param("example") RecoveryModelExample example);

    int updateByExample(@Param("record") RecoveryModel record, @Param("example") RecoveryModelExample example);

    int updateByPrimaryKeySelective(RecoveryModel record);

    int updateByPrimaryKey(RecoveryModel record);
}