package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.ProcessReviewModel;
import com.deep.domain.model.ProcessReviewModelExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface ProcessReviewModelMapper {
    int countByExample(ProcessReviewModelExample example);

    int deleteByExample(ProcessReviewModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProcessReviewModel record);

    int insertSelective(ProcessReviewModel record);

    List<ProcessReviewModel> selectByExampleWithRowbounds(ProcessReviewModelExample example, RowBounds rowBounds);

    List<ProcessReviewModel> selectByExample(ProcessReviewModelExample example);

    ProcessReviewModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProcessReviewModel record, @Param("example") ProcessReviewModelExample example);

    int updateByExample(@Param("record") ProcessReviewModel record, @Param("example") ProcessReviewModelExample example);

    int updateByPrimaryKeySelective(ProcessReviewModel record);

    int updateByPrimaryKey(ProcessReviewModel record);
}