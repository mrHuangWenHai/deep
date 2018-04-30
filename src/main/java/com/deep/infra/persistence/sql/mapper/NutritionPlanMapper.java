package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.NutritionPlan;
import com.deep.domain.model.NutritionPlanExample;
import com.deep.domain.model.NutritionPlanWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface NutritionPlanMapper {
    int countByExample(NutritionPlanExample example);

    int deleteByExample(NutritionPlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NutritionPlanWithBLOBs record);

    int insertSelective(NutritionPlanWithBLOBs record);

    List<NutritionPlanWithBLOBs> selectByExampleWithBLOBsWithRowbounds(NutritionPlanExample example, RowBounds rowBounds);

    List<NutritionPlanWithBLOBs> selectByExampleWithBLOBs(NutritionPlanExample example);

    List<NutritionPlan> selectByExampleWithRowbounds(NutritionPlanExample example, RowBounds rowBounds);

    List<NutritionPlan> selectByExample(NutritionPlanExample example);

    NutritionPlanWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NutritionPlanWithBLOBs record, @Param("example") NutritionPlanExample example);

    int updateByExampleWithBLOBs(@Param("record") NutritionPlanWithBLOBs record, @Param("example") NutritionPlanExample example);

    int updateByExample(@Param("record") NutritionPlan record, @Param("example") NutritionPlanExample example);

    int updateByPrimaryKeySelective(@Param("record") NutritionPlanWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(NutritionPlanWithBLOBs record);

    int updateByPrimaryKey(NutritionPlan record);
}